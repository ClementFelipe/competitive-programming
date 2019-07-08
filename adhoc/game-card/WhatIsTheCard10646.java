import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WhatIsTheCard10646 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    int numberOfCases = Integer.parseInt(reader.readLine());

    for (int i = 1; i <= numberOfCases; i++) {
      writer.write("Case " + i + ": " + handleCase(reader.readLine()));
    }

    writer.close();
    reader.close();
  }

  private static String handleCase(String input) {
    String[] cards = input.split(" ");
    String[] gameCards = Arrays.copyOfRange(cards, 0, cards.length - 25);
    String[] handCards = Arrays.copyOfRange(cards, cards.length - 25, cards.length);

    Object[] cardsY = getCardIndex(gameCards);

    String[] finalCards = Stream.of((String[]) cardsY[0], handCards)
      .flatMap(Stream::of)
      .toArray(String[]::new);

    return finalCards[((int) cardsY[1]) - 1] + "\n";
  }

  static Set<Character> ranks10 = new HashSet<Character>(Arrays.asList('A', 'K', 'Q', 'J', 'T'));

  private static int getCardValue(String card) {
    char rank = card.charAt(0);

    if(ranks10.contains(rank)) {
      return 10;
    }

    return rank - '0';
  }

  private static Object[] getCardIndex(String[] cards) {
    int y = 0;

    for (int i = 0; i < 3; i++) {
      int x = getCardValue(cards[cards.length - 1]);

      y += x;

      cards = Arrays.copyOfRange(cards, 0, cards.length - (11 - x));
    }

    Object[] pair = new Object[2];
    pair[0] = cards;
    pair[1] = y;

    return pair;
  }
}
