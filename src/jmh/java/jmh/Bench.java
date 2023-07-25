package jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.v1.Employee;
import org.v1.Lotto;
import org.v1.LottoV2;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms8G", "-Xms8G"})
public class Bench {


//  @Benchmark
  public void lottoV1() {
    Lotto.getLottos(100000);

  }

//  @Benchmark
  public void lottoV2() {

    LottoV2.getLottos(100000);

  }


  @Benchmark
  public void lottoV1Sum() {
    List<List<Integer>> lottos = Lotto.getLottos(100000);
    Lotto.getSum(lottos);

  }

  @Benchmark
  public void lottoV2Sum() {
    List<List<Integer>> lottos = Lotto.getLottos(100000);
    LottoV2.getSum(lottos);
  }


//  @Benchmark
  public void matchV1() {

    List<List<Integer>> lottos = Lotto.getLottos(100000);

    List<Integer> luck = LottoV2.getLuck();


    List<Integer> Calculator = new ArrayList<>();

    for (List<Integer> integerList : lottos) {
      long count = luck.stream().flatMap(
          i -> integerList.stream().filter(
              j -> i == j
          ).map(ArrayList::new)
      ).count();

      Calculator.add((int) count);


      Calculator.stream().mapToInt(
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
  }


//  @Benchmark
  public void matchV2() {


    List<List<Integer>> lottos = Lotto.getLottos(100000);

    List<Integer> luck = LottoV2.getLuck();

    lottos.stream().mapToInt(
        ticket -> getMatch(ticket, luck)
    ).map(matchCount -> {
      if(matchCount < 3) return 0;
      if(matchCount < 4) return 5000;
      if(matchCount < 5) return 50000;
      if(matchCount < 6) return 150000;
      if(matchCount < 7) return 2000000000;
      return matchCount;}
      ).sum();


    }




  public static int getMatch(List<Integer> ticket , List<Integer> luck) {

    return (int) ticket.stream().filter(i -> luck.contains(i)).count();



  }




  @TearDown(Level.Invocation)
  public void tearDown() {
    System.gc();
  }
}


