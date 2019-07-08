import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Jollo12247 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    String currentLine;

    while(!(currentLine = reader.readLine()).equals("0 0 0 0 0")) {
      writer.write(handleGame(currentLine));
    }

    writer.close();
    reader.close();
  }

  private static String handleGame(String game) {
    String[] cards = game.split(" ");
    List<Integer> cardValues = Arrays.stream(cards)
      .map(card -> Integer.parseInt(card))
      .collect(Collectors.toList());

    List<Integer> princessCards = cardValues.subList(0, 3);
    List<Integer> princeCards = cardValues.subList(3, 5);

    int card = -1;

    for (int i = 1; i <= 52; i++) {
      if (princessCards.contains(i) || princeCards.contains(i)) {
        continue;
      }

      List<Integer> princeCardsPrime = (List<Integer>) new ArrayList<>(princeCards).clone();
      princeCardsPrime.add(i);

      List<Integer> princessCardsPrime = (List<Integer>) new ArrayList<>(princessCards).clone();

      boolean meetsCondition = checkCondition(princessCardsPrime, princeCardsPrime);

      if(meetsCondition) {
        card = i;
        break;
      }
    }

    return card + "\n";
  }

  private static boolean checkCondition(List<Integer> princessCards, List<Integer> princeCards) {
    int minimumPrince = Collections.min(princeCards);

    Collections.sort(princeCards);
    Collections.sort(princessCards);

    int princessWinner = -1;

    for (int card : princessCards) {
      if (card > minimumPrince) {
        princessWinner = card;
        break;
      }
    }

    if (princessWinner == -1) {
      return true;
    }

    boolean r2 = princeCards.remove((Object) minimumPrince);
    boolean r1 = princessCards.remove((Object) princessWinner);

    return Collections.min(princeCards) > Collections.max(princessCards);
  }
}
