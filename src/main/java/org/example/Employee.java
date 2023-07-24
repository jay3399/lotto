package org.example;

import java.util.ArrayList;
import java.util.List;

public class Employee {



  public static List<List<Integer>> getLottoNums(int money) {

    int count = getCount(money);

    List<List<Integer>> lottos = Lotto.getLottos(count);

    System.out.println("판매자 : 로또 "+count+" 개입니다." );

    return lottos;
  }

  private static int getCount(int money) {
    return Math.round(money / 1000);
  }


}
