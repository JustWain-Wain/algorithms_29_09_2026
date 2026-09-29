package JustWain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provide.MethodSource;
import org.junit.jupiter.params.provide.Arguments;

import java.util.stream.Stream;

public class MainTest {

    @ParameterizedTest
    @MethodSource("testBubbleSortData")
    void testMergeSort(int N) {
        //Random random = new Random(1000);
        int[] arr = new int[N];
        //for (int i = 0; i < N; i++) arr[i] = random.nextInt(100);
        for (int i = 0; i < N; i++) arr[i] = N - i + 1;

        long start = System.nanoTime();
        Main.mergeSort(arr);
        long diff = System.nanoTime() - start;
        System.out.println(diff);
        for (int i = 0; i < arr.length - 1; i++) {
            Assertions.assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    static Stream<Arguments> testMergeSortData() {
        return Stream.of(
                Arguments.of(10),
                Arguments.of(10),
                Arguments.of(100),
                Arguments.of(200),
                Arguments.of(400),
                Arguments.of(800),
                Arguments.of(1600),
                Arguments.of(3200),
                Arguments.of(6400),
                Arguments.of(12800),
                Arguments.of(25600),
                Arguments.of(51200),
                Arguments.of(102400)
        );
    }

}
