package org.v1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoV2 {


  public static List<List<Integer>> getLottos(int count) {

    return IntStream.range(0, count).mapToObj(i -> getNums())
        .collect(Collectors.toList());


  }

  public static List<Integer> getNums() {
    List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());

    Collections.shuffle(numbers);

    List<Integer> integers = numbers.subList(0, 6);

    Collections.sort(integers);

    return integers;

  }

  public static int getSum(List<List<Integer>> nums) {

    List<Integer> luck = getLuck();

    return nums.stream().mapToInt(
         ticket -> getMatch(ticket , luck)
     ).map(
         matchCount -> {
           if(matchCount < 3) return 0;
           if(matchCount < 4) return 5000;
           if(matchCount < 5) return 50000;
           if(matchCount < 6) return 150000;
           if(matchCount < 7) return 2000000000;
           return matchCount;
         }
     ).sum();



  }


  public static List<Integer> getLuck() {
    return getNums();
  }

  public static int getMatch(List<Integer> ticket ,List<Integer> luck) {



    return (int) ticket.stream().filter(i -> luck.contains(i)).count();



  }

}
