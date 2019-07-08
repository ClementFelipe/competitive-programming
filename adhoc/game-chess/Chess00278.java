import java.io.*;

class Chess00278 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    int numberOfCases = Integer.parseInt(reader.readLine());

    while ((numberOfCases > 0)) {
      int caseResult = handleCase(reader.readLine());

      if (caseResult != -1) {
        writer.write(caseResult + "\n");
        numberOfCases--;
      }
    }

    writer.close();
    reader.close();
  }

  private static int handleCase(String caseLine) {
    if (caseLine.trim().isEmpty()) {
      return -1;
    }

    String[] caseElements = caseLine.split(" ");

    String piece = caseElements[0];
    int m = Integer.parseInt(caseElements[1]);
    int n = Integer.parseInt(caseElements[2]);

    switch (piece) {
      case "r":
        return Math.min(m, n);
      case "k":
        return (int) (Math.ceil((m * n) / 2.0));
      case "Q":
        return Math.min(m, n);
      case "K":
        return (int) (Math.ceil(Math.max(m, n) / 2.0) * Math.ceil(Math.min(m, n) / 2.0));
      default:
        return -1;
    }
  }
}
