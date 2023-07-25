package lotto;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
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

  public static List<Set<Integer>> getLottosV2(int lottoCount) {


    List<Set<Integer>> collect2 = Stream.generate(() -> generateLottoNumsV2()).limit(lottoCount)
        .collect(Collectors.toList());

//    System.out.println("로또발행 :" + collect2);

    return collect2;
  }


  public static List<Integer> generateLottoNums() {
    List<Integer> collect1 = Stream.generate(() -> getRandom()).distinct().limit(6).sorted()
        .collect(Collectors.toList());
    return collect1;
  }

  //LinkedHashSet을 사용한다해서 , 자동으로 정렬이되는것이아닌 , sortedr기능을  사용할수있는것.

  public static Set<Integer> generateLottoNumsV2() {
    Set<Integer> collect1 = Stream.generate(() -> getRandom()).distinct().limit(6).sorted()
        .collect(Collectors.toCollection(LinkedHashSet::new));




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


  public static List<Integer> getLuckyNums() {
    return Stream.generate(() -> generateLottoNums()).limit(1).toList()
        .stream().flatMap(List::stream).toList();
  }

  public static Set<Integer> getLuckyNumsV2() {
    return Stream.generate(() -> generateLottoNums()).limit(1)
        .collect(Collectors.toCollection(LinkedHashSet::new))
        .stream().flatMap(List::stream).collect(Collectors.toCollection(LinkedHashSet::new));
  }





}
