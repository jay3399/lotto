package lotto;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoV5 {




  public static List<Set<Integer>> getLottosV2(int lottoCount) {
    return Stream.generate(LottoV5::generateLottoNumsV2)
        .limit(lottoCount)
        .collect(Collectors.toList());
  }


  // LinkedHastSet 은 자동정렬이아닌  , 입력한 순서대로! 정렬이다.
  // TreeSet 이 디폴트가 오름차순정렬이다 !! vs HashSet
  // 중복제거 , + 위는 정렬을위해 스트림변환을 한번더 해야하지만 트리를쓰면 그럴필요가 없어진다.
  public static Set<Integer> generateLottoNumsV2() {
    Set<Integer> numbers = new TreeSet<>();
    Random random = new Random();

    while (numbers.size() < 6) {
      int randomNumber = random.nextInt(45) + 1; // 1부터 45까지의 랜덤한 숫자 생성
      numbers.add(randomNumber);
    }

    return numbers;
  }

  public static int calculateTotalPrizeV2(List<Set<Integer>> tickets, Set<Integer> luckyNumbers) {
    return tickets.stream()
        .mapToInt(ticket -> getMatchV2(ticket, luckyNumbers))
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


  public static int getMatchV2(Set<Integer> ticket, Set<Integer> luckyNumbers) {
    return (int) ticket.stream()
        .filter(luckyNumbers::contains)
        .count();
  }


  public static Set<Integer> getLuckyNumbersV2() {
    return generateLottoNumsV2();
  }


//  public static List<List<Integer>> getLottos(int lottoCount) {
//    return Stream.generate(LottoV5::generateLottoNums)
//        .limit(lottoCount)
//        .collect(Collectors.toList());
//  }
//
//  public static List<Integer> generateLottoNums() {
//    Set<Integer> numbers = new HashSet<>();
//    Random random = new Random();
//
//    while (numbers.size() < 6) {
//      int randomNumber = random.nextInt(45) + 1; // 1부터 45까지의 랜덤한 숫자 생성
//      numbers.add(randomNumber);
//    }
//
//    return numbers.stream()
//        .sorted()
//        .collect(Collectors.toList());
//  }
//
//
//  public static int calculateTotalPrize(List<List<Integer>> tickets, List<Integer> luckyNumbers) {
//    return tickets.stream()
//        .mapToInt(ticket -> getMatch(ticket, luckyNumbers))
//        .map(matchCount -> {
//          if (matchCount < 3) return 0;
//          if (matchCount < 4) return 5000;
//          if (matchCount < 5) return 50000;
//          if (matchCount < 6) return 150000;
//          if (matchCount < 7) return 2000000000;
//          return matchCount;
//        })
//        .sum();
//  }
//
//
//  public static int getMatch(List<Integer> ticket, List<Integer> luckyNumbers) {
//    return (int) ticket.stream()
//        .filter(luckyNumbers::contains)
//        .count();
//  }
//
//
//
//
//  public static List<Integer> getLuckyNumbers() {
//    return generateLottoNums();
//  }


  }


