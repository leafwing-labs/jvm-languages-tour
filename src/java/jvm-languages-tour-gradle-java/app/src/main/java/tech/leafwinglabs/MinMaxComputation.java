package tech.leafwinglabs;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;

public class MinMaxComputation {



  // classic for-if
  public static int[] minMax1(int[] arr) {
    int[] result = new int[2];
    int min = arr[0];
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
        result[0] = arr[i];
      }
      if (arr[i] > max) {
        result[1] = arr[i];
      }
    }
    return result;
  }

  // classic for iteration
  public static int[] minMax2(int[] arr) {
    int min = arr[0];
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      min = Math.min(min, arr[i]);
      max = Math.max(max, arr[i]);
    }
    return new int[]{min, max};
  }

  // APIs
  public static int[] minMax5(int[] arr) {
    return new int[]{
        Arrays.stream(arr).min().getAsInt(),
        Arrays.stream(arr).max().getAsInt()};
  }

  public static int[] minMax6(int[] arr) {
    IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
    return new int[]{stats.getMin(), stats.getMax()};
  }

  public static int[] minMax7(int[] arr) {
    return new int[]{
        Collections.min(Arrays.stream(arr).boxed().toList()),
        Collections.max(Arrays.stream(arr).boxed().toList())
    };
  }

  public static int[] minMax8(int[] arr) {
    return Arrays.stream(arr).collect(
        () -> new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE},
        (acc, x) -> {
          acc[0] = Math.min(acc[0], x);
          acc[1] = Math.max(acc[1], x);
        },
        (acc, x) -> {
          acc[0] = Math.min(acc[0], x[0]);
          acc[1] = Math.max(acc[1], x[1]);
        }
    );
  }

  // functional style
  public static int[] minMax3(int[] arr) {
    return new int[]{
        Arrays.stream(arr).reduce(Integer.MAX_VALUE, Math::min),
        Arrays.stream(arr).reduce(Integer.MIN_VALUE, Math::max)
    };
  }


  // stream processing
  public static int[] minMax4(int[] arr) {
    return Arrays.stream(arr)
                 .boxed()
                 .reduce(
                     List.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
                     (List<Integer> acc, Integer x) -> List.of(
                         Math.min(acc.getFirst(), x),
                         Math.max(acc.get(1), x)
                     ),
                     (List<Integer> acc1, List<Integer> acc2) -> List.of(
                         Math.min(acc1.getFirst(), acc2.getFirst()),
                         Math.max(acc1.get(1), acc2.get(1))
                     )
                 ).stream().mapToInt(i -> i).toArray();
  }

}

