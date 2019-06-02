import java.io.*;
import java.util.Arrays;

class Minesweeper10189 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    String currentLine;

    char[][] caseResult;

    String parsedResult;

    int caseNumber = 0;

    while (!(currentLine = reader.readLine()).equals("0 0")) {
      caseResult = handleMinesweeper(reader, currentLine);

      caseNumber++;

      parsedResult = parseResult(caseResult, caseNumber);

      writer.write(parsedResult);
    }

    writer.close();
    reader.close();
  }

  private static char[][] handleMinesweeper(BufferedReader reader, String matrixSize) throws Exception {
    final String[] matrixSizeString = matrixSize.split(" ");

    final int m = Integer.parseInt(matrixSizeString[0]);
    final int n = Integer.parseInt(matrixSizeString[1]);

    final char[][] minesweeperBoard = initializeBoard(reader, m, n);

    return tallyBoard(minesweeperBoard);
  }

  private static char[][] initializeBoard(BufferedReader reader, int m, int n) throws Exception {
    final char[][] minesweeperBoard = new char[m + 2][n + 2];

    String currentRow = "";

    for (int i = 0; i < m; i++) {
      currentRow = reader.readLine();

      fillBoardRow(minesweeperBoard, i + 1, currentRow);
    }

    return minesweeperBoard;
  }

  private static void fillBoardRow(char[][] board, int row, String currentRow) {
    char[] items = currentRow.toCharArray();

    for (int i = 0; i < items.length; i++) {
      board[row][i + 1] = items[i];
    }
  }

  private static char[][] tallyBoard(char[][] board) {
    final int height = board.length;
    final int width = board[0].length;

    final char[][] talliedBoard = new char[height - 2][width - 2];

    for (int i = 1; i < board.length - 1; i++) {
      for (int j = 1; j < board[0].length - 1; j++) {
        talliedBoard[i - 1][j - 1] = tallyCell(board, i, j);
      }
    }

    return talliedBoard;
  }

  private static char tallyCell(char[][] board, int i, int j) {
    final char currentCellValue = board[i][j];

    return currentCellValue == '*' ? '*'
        : Character.forDigit(check(board[i][j - 1]) + check(board[i - 1][j]) + check(board[i - 1][j - 1])
            + check(board[i][j + 1]) + check(board[i + 1][j]) + check(board[i + 1][j + 1]) + check(board[i - 1][j + 1])
            + check(board[i + 1][j - 1]), 10);
  }

  private static int check(char c) {
    return c == '*' ? 1 : 0;
  }

  private static String parseResult(char[][] board, int caseNumber) {
    String field = "";

    for (int i = 0; i < board.length; i++) {
      field += new String(board[i]) + "\n";
    }

    final String fieldNumber = caseNumber == 1 ? "Field #" : "\nField #";

    return fieldNumber + caseNumber + ":\n" + field;
  }
}
