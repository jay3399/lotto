package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {

//  private int money = 10000;
  private List<List<Integer>> lottoNums = new ArrayList<>();

  private int money = 100000000;

  public int gerOrder(
  ) {
    System.out.println("구메자 :" + money + "원치 로또 주세요");
    return money;
  }

  public List<List<Integer>> getNums() {
    return lottoNums;
  }

  public void setNums(List<List<Integer>> nums) {
    this.lottoNums = nums;
  }

}
