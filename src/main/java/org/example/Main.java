package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {

    //구매자  <-> 판매자

    //현금을 낸다  ->  로또발급개수를 계산한다  -> 개수만큼 발행요청 -> 로또번호 발급

    // 추첨

    // 추첨한다 -> 로또번호발급 -> 대조한다 -> 6개일치시 20억 , 5개 + 보너스 3천만원 , 5개 150만원 , 4개 5만원 , 3개 5천원 -> 수익률계산한다

    int money = 100000;

    int lottoCount = getCount(money);

    System.out.println("구매개수 = " + lottoCount);

    List<List<Integer>> collect2 = getLottos(lottoCount);

    System.out.println("발행로또번호 = " + collect2);

    List<Integer> integers = getLuckyNums();

    System.out.println("당첨번호 = " + integers);

    List<Integer> Calculator = new ArrayList<>();

    for (List<Integer> integerList : collect2) {
      long count = integers.stream().flatMap(
          i -> integerList.stream().filter(
              j -> i == j
          ).map(ArrayList::new)
      ).count();

      Calculator.add((int) count);

    }

    // vs ??

//    List<Integer> collect = integers.stream().flatMap(
//        i -> collect2.get(0).stream().filter(
//            j -> j == i
//        )
//    ).collect(Collectors.toList());
//
//    System.out.println("collect = " + collect);


    int sum =Calculator.stream().mapToInt(
        i -> {
          if(i < 3) return 0;
          if(i < 4) return 5000;
          if(i < 5) return 50000;
          if(i < 6) return 150000;
          if(i < 7) return 2000000000;
          return i;
        }
    ).sum();

    System.out.println("총당첨금 = " + sum);





    // 총 당첨금액 합계 반환.

    List<Integer> integers1 = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> integers2 = Arrays.asList(2, 4, 6, 8, 9, 10);


    //Map 은 안된다. 평면화후 비교한다.

    List<Integer> collect3 = integers1.stream().flatMap(
        i -> integers2.stream().filter(j -> i == j)
    ).collect(Collectors.toList());


  }

  private static List<Integer> getLuckyNums() {
    return Stream.generate(() -> generateLottoNums()).limit(1).toList()
        .stream().flatMap(List::stream).toList();
  }

  private static List<List<Integer>> getLottos(int lottoCount) {
    List<List<Integer>> collect2 = Stream.generate(() -> generateLottoNums()).limit(lottoCount)
        .collect(Collectors.toList());
    return collect2;
  }

  private static int getCount(int money) {
    return Math.round(money / 1000);
  }

  private static List<Integer> generateLottoNums() {
    List<Integer> collect1 = Stream.generate(() -> getRandom()).distinct().limit(6).sorted()
        .collect(Collectors.toList());
    return collect1;
  }

  private static int getRandom() {
    int random = (int) Math.round(Math.random() * (45 - 1) + 1);
    return random;
  }




}