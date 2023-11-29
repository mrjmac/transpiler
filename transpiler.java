import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class transpiler {

    public static void main(String args[]) throws IOException
    {   
        StringTokenizer st = new StringTokenizer(Files.readString(Path.of("code.in")));
        PrintWriter pw = new PrintWriter("code.java");
        TreeMap<String, Integer> vars = new TreeMap<>();

        pw.print(Files.readString(Path.of("template.txt"))); // setup basic java syntax
        
        // CHANGE TO HANDLEBRACKET SYSTEM
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();

            if (token.equals("if") || token.equals("while") || token.equals("elif") || token.equals("else"))
            {
                // everything before the brackets should be the same
                String parse = "";

                while (!token.equals("{"))
                {
                    parse += token + " ";
                    token = st.nextToken();
                }

                pw.print(parse);

                // after the bracket, we just have to sanitize for new variables and for print

            }

        }

        pw.print("}\n}"); // finish file
        pw.close();
    }
}