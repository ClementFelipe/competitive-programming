package intro.sup.easy;

import java.io.*;

// https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2113
class Main {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        final int cases = Integer.parseInt(reader.readLine());

        String currentLine;
        String[] currentLineNumbers;

        int a, b;

        String relation;

        String output = "";

        for (int i = 0; i < cases; i++) {
            currentLine = reader.readLine();
            currentLineNumbers = currentLine.split(" ");

            a = Integer.parseInt(currentLineNumbers[0]);
            b = Integer.parseInt(currentLineNumbers[1]);

            if (a > b) {
                relation = ">";
            }
            else if (a < b) {
                relation = "<";
            }
            else {
                relation = "=";
            }

            output += relation + "\n";
        }

        writer.write(output);

        writer.close();
        reader.close();
    }
}
