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

    Customer cr = new Customer();

    int money = cr.gerOrder();

    List<List<Integer>> lottoNums = Employee.getLottoNums(money);

    cr.setNums(lottoNums);

    Lotto.getWins(cr.getNums());


    // 추첨

    // 추첨한다 -> 로또번호발급 -> 대조한다 -> 6개일치시 20억 , 5개 + 보너스 3천만원 , 5개 150만원 , 4개 5만원 , 3개 5천원 -> 수익률계산한다





  }




  private static int getCount(int money) {
    return Math.round(money / 1000);
  }









}