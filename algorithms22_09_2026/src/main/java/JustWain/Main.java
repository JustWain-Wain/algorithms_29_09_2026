package JustWain;

import java.util.Arrays;
import java.util.Random;

public class Main {

    // =========================
    // MERGE SORT
    // =========================

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length, new int[a.length]);
    }

    private static void mergeSort(int[] a, int left, int right, int[] buffer) {
        if (right - left <= 1) return;

        int middle = left + (right - left) / 2;

        mergeSort(a, left, middle, buffer);
        mergeSort(a, middle, right, buffer);

        int i = left;
        int j = middle;
        int k = left;

        while (i < middle && j < right) {
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
        }

        while (i < middle) {
            buffer[k++] = a[i++];
        }

        while (j < right) {
            buffer[k++] = a[j++];
        }

        System.arraycopy(buffer, left, a, left, right - left);
    }


    // =========================
    // BUBBLE SORT
    // =========================

    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < a.length - 1 - i; j++) {

                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    swapped = true;
                }
            }

            // Если обменов не было, массив уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }


    // =========================
    // ГЕНЕРАЦИЯ МАССИВА
    // =========================

    public static int[] generateArray(int size) {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }

        return array;
    }


    // =========================
    // ИЗМЕРЕНИЕ ВРЕМЕНИ
    // =========================

    public static double measureBubbleSort(int[] original, int repetitions) {

        long totalTime = 0;

        for (int i = 0; i < repetitions; i++) {

            // Создаем копию, чтобы каждый запуск
            // начинался с одинакового массива
            int[] array = Arrays.copyOf(original, original.length);

            long start = System.nanoTime();

            bubbleSort(array);

            long end = System.nanoTime();

            totalTime += end - start;

            // Проверяем, что сортировка действительно правильная
            if (!isSorted(array)) {
                throw new RuntimeException("Bubble Sort работает неправильно!");
            }
        }

        return totalTime / (double) repetitions;
    }


    public static double measureMergeSort(int[] original, int repetitions) {

        long totalTime = 0;

        for (int i = 0; i < repetitions; i++) {

            int[] array = Arrays.copyOf(original, original.length);

            long start = System.nanoTime();

            mergeSort(array);

            long end = System.nanoTime();

            totalTime += end - start;

            if (!isSorted(array)) {
                throw new RuntimeException("Merge Sort работает неправильно!");
            }
        }

        return totalTime / (double) repetitions;
    }

    public static double measureArraySort(int[] original, int repetitions) {

        long totalTime = 0;

        for (int i = 0; i < repetitions; i++) {

            int[] array = Arrays.copyOf(original, original.length);

            long start = System.nanoTime();

            Arrays.sort(array);

            long end = System.nanoTime();

            totalTime += end - start;

            if (!isSorted(array)) {
                throw new RuntimeException("Array Sort работает неправильно!");
            }
        }

        return totalTime / (double) repetitions;
    }


    // =========================
    // ПРОВЕРКА СОРТИРОВКИ
    // =========================

    public static boolean isSorted(int[] array) {

        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }

        return true;
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        int[] warmupArray = generateArray(1000);

        for (int i = 0; i < 100; i++) {
            int[] copy1 = Arrays.copyOf(warmupArray, warmupArray.length);
            int[] copy2 = Arrays.copyOf(warmupArray, warmupArray.length);

            bubbleSort(copy1);
            mergeSort(copy2);
        }


        int[] sizes = {
                1280,
                2560,
                5120,
                10240,
                20480,
                40960,
                81920
        };

        int repetitions = 5;

        System.out.println("Сравнение алгоритмов сортировки");
        System.out.println();

        System.out.printf(
                "%-12s %-20s %-20s%n",
                "Размер", "Array Sort (ns)", "Merge Sort (ns)"
        );

        System.out.println("----------------------------------------------------------");

        for (int size : sizes) {

            int[] originalArray = generateArray(size);

            double bubbleTime =
                    measureBubbleSort(originalArray, repetitions);

            double mergeTime =
                    measureMergeSort(originalArray, repetitions);

            double arrayTime =
                    measureArraySort(originalArray, repetitions);

            System.out.printf(
                    "%-12d %-20.0f %-20.0f%n",
                    size,
                    arrayTime,
                    mergeTime
            );
        }
    }
}