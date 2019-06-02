import java.io.*;
import java.util.Arrays;

public class TheTrip10137 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    String currentLine;

    String result;

    while (!(currentLine = reader.readLine().trim()).equals("0")) {
      result = handleTrip(reader, currentLine);

      writer.write(result);
    }

    writer.close();
    reader.close();
  }

  private static String handleTrip(BufferedReader reader, String currentLine) throws Exception {
    double[] expenses = initializeExpenses(reader, currentLine);

    double money = calculateMoney(expenses);

    return "$" + String.format("%.2f" , money) + "\n";
  }

  private static double[] initializeExpenses(BufferedReader reader, String currentLine) throws Exception {
    int numberOfExpenses = Integer.parseInt(currentLine);

    double[] expenses = new double[numberOfExpenses];

    for (int i = 0; i < numberOfExpenses; i++) {
      expenses[i] = Double.parseDouble(reader.readLine().trim()) * 100.0;
    }

    return expenses;
  }

  private static double calculateMoney(double[] expenses) {
    double totalExpenses = 0.0;

    for (int i = 0; i < expenses.length; i++) {
      totalExpenses += expenses[i];
    }

    double average = totalExpenses / ((double) expenses.length);
    double lowAverage = Math.floor(average);
    double highAverage = Math.ceil(average);

    double diffLow = 0.0;
    double diffHigh = 0.0;

    for (int i = 0; i < expenses.length; i++) {
      if (expenses[i] > highAverage) {
        diffHigh += expenses[i] - highAverage;
      }
      if (expenses[i] < lowAverage) {
        diffLow += lowAverage - expenses[i];
      }
    }

    return Math.max(diffHigh, diffLow) / 100.0;
  }
}
