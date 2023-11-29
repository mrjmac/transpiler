import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class transpiler {

    public static void main(String args[]) throws IOException
    {   
        StringTokenizer st = new StringTokenizer(Files.readString(Path.of("code.in")));
        PrintWriter pw = new PrintWriter("code.java");
        TreeSet<String> approvedAscii = new TreeSet<>();

        approvedAscii.add("if");
        approvedAscii.add("else");
        approvedAscii.add("while");

        pw.print(Files.readString(Path.of("template.txt"))); // setup basic java syntax
        
        // CHANGE TO HANDLEBRACKET SYSTEM
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();
            char start = token.charAt(0);

            if ((start >= 65 && start <= 90) || (start >= 97 && start <= 122) && !approvedAscii.contains(token))
            {
                if (token.equals("elif"))
                {
                    pw.print("else if ");
                }
                else if (token.equals("print"))
                {
                    pw.print("System.out.println(");

                    token = st.nextToken();
                    while (!token.equals(";"))
                    {
                        pw.print(token + " ");
                        token = st.nextToken();
                    }

                    pw.print(");");
                }
                else
                {
                    pw.print("int " + token + " ");
                    approvedAscii.add(token);
                }
            }
            else
            {
                pw.print(token + " ");
            }

        }

        pw.print("}\n}"); // finish file
        pw.close();
    }
}