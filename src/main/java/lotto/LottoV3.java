package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoV3 {


  public static List<List<Integer>> getLottos(int lottoCount) {

    List<List<Integer>> collect2 = Stream.generate(() -> generateLottoNums()).limit(lottoCount)
        .collect(Collectors.toList());

//    System.out.println("로또발행 :" + collect2);

    return collect2;
  }


  public static List<Integer> generateLottoNums() {
    List<Integer> collect1 = Stream.generate(() -> getRandom()).distinct().limit(6).sorted()
        .collect(Collectors.toList());
    return collect1;
  }


  private static int getRandom() {
    int random = (int) Math.round(Math.random() * (45 - 1) + 1);
    return random;
  }


  public static int getSum(List<List<Integer>> nums) {

    List<Integer> luck = Lotto.getLuckyNums();

    return nums.stream().mapToInt(
        ticket -> getMatch(ticket, luck)
    ).map(
        matchCount -> {
          if (matchCount < 3) return 0;
          if (matchCount < 4) return 5000;
          if (matchCount < 5) return 50000;
          if (matchCount < 6) return 150000;
          if (matchCount < 7) return 2000000000;
          return matchCount;
        }
    ).sum();


  }

  public static int getMatch(List<Integer> ticket, List<Integer> luck) {

    return (int) ticket.stream().filter(i -> luck.contains(i)).count();


  }

  public static List<Integer> getLuckyNums() {
    return Stream.generate(() -> generateLottoNums()).limit(1).toList()
        .stream().flatMap(List::stream).toList();
  }

}

/**
 * 이구조에서는 List 가 Hash 보다 빠르다
 * 계산은 병렬스트림이 빠르다.
 * 번호생성은 1~45 만들고 셔플돌리는것보단 , Random 메서드 쓰는게 더 낫다.
 */