import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class BridgeHandEvaluator00462 {

  public static void main(String[] args) throws Exception {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    String currentLine;

    while((currentLine = reader.readLine()) != null) {
      writer.write(handleHand(currentLine));
    }

    writer.close();
    reader.close();
  }

  private static String handleHand(String hand) {
    String[] cards = hand.split(" ");

    int case1 = countCase1(cards);
    int case2 = countCase2(cards);
    int case3 = countCase3(cards);
    int case4 = countCase4(cards);
    int case5 = countCase5(cards);
    int case6 = countCase6(cards);
    int case7 = countCase7(cards);

    int total = case1 + case2 + case3 + case4 + case5 + case6 + case7;
    int trumpify = case1 + case2 + case3 + case4;
    boolean allStopped = allStopped(cards);

    if (total < 14) {
      return "PASS\n";
    } else if (trumpify >= 16 && allStopped) {
      return "BID NO-TRUMP\n";
    } else {
      return findBid(cards);
    }
  }

  private static boolean allStopped(String[] cards) {
    int stoppedCount = 0;

    final TreeMap<Character, HashSet<Character>> suitCards = mapSuitCounts(cards);

    for (HashSet<Character> sCards : suitCards.values()) {
      if (sCards.contains('A')) {
        stoppedCount++;
        continue;
      }
      if (sCards.contains('K') && sCards.size() >= 2) {
        stoppedCount++;
        continue;
      }
      if (sCards.contains('Q') && sCards.size() >= 3) {
        stoppedCount++;
        continue;
      }
    }

    return stoppedCount == 4;
  }

  private static TreeMap<Character, HashSet<Character>> mapSuitCounts(String[] cards) {
    final TreeMap<Character, HashSet<Character>> suitCounts = new TreeMap() {{
      put('H', new HashSet<Character>());
      put('S', new HashSet<Character>());
      put('C', new HashSet<Character>());
      put('D', new HashSet<Character>());
    }};

    for (String card : cards) {
      char suit = card.charAt(1);

      suitCounts.get(suit).add(card.charAt(0));
    }

    return suitCounts;
  }

  private static String findBid(String[] cards) {
    final TreeMap<Character, HashSet<Character>> suitCounts = mapSuitCounts(cards);

    char maxSuit = 'x';
    int maxSuitCount = 0;

    for (Entry<Character, HashSet<Character>> suit : suitCounts.entrySet()) {
      if (suit.getValue().size() >= maxSuitCount){
        maxSuit = suit.getKey();
        maxSuitCount = suit.getValue().size();
      }
    }

    return "BID " + maxSuit + "\n";
  }

  private static int countCase1(String[] cards) {
    int count = 0;

    Arrays.toString(cards);

    for (String card : cards) {
      char rank = card.charAt(0);

      if (rank == 'A') {
        count += 4;
      } else if (rank == 'K') {
        count += 3;
      } else if (rank == 'Q') {
        count += 2;
      } else if (rank == 'J') {
        count += 1;
      }
    }

    return count;
  }

  private static int countCase2(String[] cards) {
    ArrayList<String> kings = new ArrayList<>();

    for (String card : cards) {
      if (card.charAt(0) == 'K') {
        kings.add(card);
      }
    }

    int count = 0;

    for (String king : kings) {
      char suit = king.charAt(1);

      int suitCount = countSuit(cards, suit, king);

      if (suitCount == 0) {
        count++;
      }
    }

    return -count;
  }

  private static int countCase3(String[] cards) {
    ArrayList<String> queens = new ArrayList<>();

    for (String card : cards) {
      if (card.charAt(0) == 'Q') {
        queens.add(card);
      }
    }

    int count = 0;

    for (String queen : queens) {
      char suit = queen.charAt(1);

      int suitCount = countSuit(cards, suit, queen);

      if (suitCount < 2) {
        count++;
      }
    }

    return -count;
  }

  private static int countCase4(String[] cards) {
    ArrayList<String> jacks = new ArrayList<>();

    for (String card : cards) {
      if (card.charAt(0) == 'J') {
        jacks.add(card);
      }
    }

    int count = 0;

    for (String jack : jacks) {
      char suit = jack.charAt(1);

      int suitCount = countSuit(cards, suit, jack);

      if (suitCount < 3) {
        count++;
      }
    }

    return -count;
  }

  private static int countCase5(String[] cards) {
    final TreeMap<Character, HashSet<Character>> suitCounts = mapSuitCounts(cards);

    int count = 0;

    for (HashSet<Character> suitCards : suitCounts.values()) {
      if (suitCards.size() == 2) {
        count++;
      }
    }

    return count;
  }

  private static int countCase6(String[] cards) {
    final TreeMap<Character, HashSet<Character>> suitCounts = mapSuitCounts(cards);

    int count = 0;

    for (HashSet<Character> suitCards : suitCounts.values()) {
      if (suitCards.size() == 1) {
        count += 2;
      }
    }

    return count;
  }

  private static int countCase7(String[] cards) {
    final TreeMap<Character, HashSet<Character>> suitCounts = mapSuitCounts(cards);

    return 2 * suitCounts.values().stream()
      .filter(set -> set.size() == 0)
      .collect(Collectors.toList())
      .size();
  }

  private static int countSuit(String[] cards, char suit, String exclude) {
    int count = 0;

    for (String card : cards) {
      if (!card.equals(exclude) && card.charAt(1) == suit) {
        count++;
      }
    }

    return count;
  }
}
