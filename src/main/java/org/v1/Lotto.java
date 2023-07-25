package org.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {

//  private final List<Integer> numbers;
//
//  public Lotto(List<Integer> numbers) {
//    validate(numbers);
//    this.numbers = numbers;
//  }
//
//  private void validate(List<Integer> numbers) {
//    if (numbers.size() != 6) {
//      throw new IllegalArgumentException();
//    }
//
//  }
  public static List<List<Integer>> getLottos(int lottoCount) {


  List<List<Integer>> collect2 = Stream.generate(() -> generateLottoNums()).limit(lottoCount)
      .collect(Collectors.toList());

//    System.out.println("로또발행 :" + collect2);

    return collect2;
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

  public static int getWins(List<List<Integer>> nums) {

    int sum = getSum(nums);
//    System.out.println("당첨금액: " + sum);

    return sum;


  }

  public static int getSum(List<List<Integer>> nums) {

    List<Integer> luckyNums = getLuckyNums();

//    System.out.println("당첨번호 : " + luckyNums);

    List<Integer> Calculator = new ArrayList<>();

    for (List<Integer> integerList : nums) {
      long count = luckyNums.stream().flatMap(
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


    //Map 은 안된다. flatMap 평면화후 비교한다.

//    List<Integer> collect3 = integers1.stream().flatMap(
//        i -> integers2.stream().filter(j -> i == j)
//    ).collect(Collectors.toList());






    return Calculator.stream().mapToInt(
        i -> {
          if(i < 3) return 0;
          if(i < 4) return 5000;
          if(i < 5) return 50000;
          if(i < 6) return 150000;
          if(i < 7) return 2000000000;
          return i;
        }
    ).sum();
  }


  private static List<Integer> getLuckyNums() {
    return Stream.generate(() -> generateLottoNums()).limit(1).toList()
        .stream().flatMap(List::stream).toList();
  }





}
