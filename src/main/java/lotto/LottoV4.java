package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoV4 {


  public static List<List<Integer>> getLottos(int lottoCount) {
    return Stream.generate(LottoV3::generateLottoNums)
        .limit(lottoCount)
        .collect(Collectors.toList());
  }

  public static List<Integer> generateLottoNums() {
    List<Integer> numbers = IntStream.rangeClosed(1, 45)
        .boxed()
        .collect(Collectors.toList());
    Collections.shuffle(numbers);
    return numbers.subList(0, 6).stream()
        .sorted()
        .collect(Collectors.toList());
  }

  public static int calculateTotalPrize(List<List<Integer>> tickets, List<Integer> luckyNumbers) {
    return tickets.stream()
        .mapToInt(ticket -> getMatch(ticket, luckyNumbers))
        .map(matchCount -> {
          if (matchCount < 3) return 0;
          if (matchCount < 4) return 5000;
          if (matchCount < 5) return 50000;
          if (matchCount < 6) return 150000;
          if (matchCount < 7) return 2000000000;
          return matchCount;
        })
        .sum();
  }

  public static int getMatch(List<Integer> ticket, List<Integer> luckyNumbers) {
    return (int) ticket.stream()
        .filter(luckyNumbers::contains)
        .count();
  }

  public static List<Integer> getLuckyNumbers() {
    return generateLottoNums();
  }

}
