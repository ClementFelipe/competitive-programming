import java.io.*;
import java.util.Arrays;

class DivisionOfNlogonia11498 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    String currentLine;

    while(!(currentLine = reader.readLine()).equals("0")) {
      int numberOfQueries = Integer.parseInt(currentLine);

      int[] center = readPoint(reader);
      int[][] queries = readQueries(reader, numberOfQueries);

      writer.write(handleQueries(center, queries));
    }

    writer.close();
    reader.close();
  }

  private static int[] readPoint(BufferedReader reader) throws Exception {
    String centerLine = reader.readLine();

    String[] centerPoints = centerLine.split(" ");

    return new int[]{ Integer.parseInt(centerPoints[0]), Integer.parseInt(centerPoints[1]) };
  }

  private static int[][] readQueries(BufferedReader reader, int numberOfQueries) throws Exception {
    int[][] queries = new int[numberOfQueries][2];

    for (int i = 0; i < numberOfQueries; i++) {
      queries[i] = readPoint(reader);
    }

    return queries;
  }

  private static String handleQueries(int[] center, int[][] queries) {
    String result = "";

    for (int[] query : queries) {
      result += handleQuery(center, query);
    }

    return result;
  }

  private static String handleQuery(int[] center, int[] query) {
    if (center[0] == query[0] || center[1] == query[1]) {
      return "divisa\n";
    } else if (query[0] > center[0] && query[1] > center[1]) {
      return "NE\n";
    } else if (query[0] > center[0] && query[1] < center[1]) {
      return "SE\n";
    } else if (query[0] < center[0] && query[1] < center[1]) {
      return "SO\n";
    } else {
      return "NO\n";
    }
  }
}
