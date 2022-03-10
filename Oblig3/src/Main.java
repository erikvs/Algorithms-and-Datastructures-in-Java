import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Define how many numbers to generate as n:\n");

            int n = userInput.nextInt();
            int[] toBeSorted = new int[n];
            double time = 0;
            int i;
            double sumTimeComplexity = 0;

            System.out.println("Define which sort method to test or quit the program: ");
            System.out.println("1 - Insertion sort");
            System.out.println("2 - Quick sort");
            System.out.println("3 - Merge sort");
            System.out.println("4 - Radix sort");
            System.out.println("5 - Quit\n ");
            int sortMethod = userInput.nextInt();

            switch (sortMethod) {
                case 1:
                    System.out.println("Perform sort (1) or calculate Average Time Complexity (2):\n");
                    int insertionSortTest = userInput.nextInt();

                    switch (insertionSortTest) {
                        case 1:
                            Randomize.randomize(toBeSorted);
                            time = System.currentTimeMillis();
                            Sorting.insertionSort(toBeSorted);
                            time = System.currentTimeMillis() - time;
                            System.out.printf("Runtime for Insertion Sort\t: %6.3f seconds\n", time / 1000.0);
                            System.out.println();
                            break;
                        case 2:
                            for (i = 0; i < 15; i++) {
                                Randomize.randomize(toBeSorted);
                                time = System.currentTimeMillis();
                                Sorting.insertionSort(toBeSorted);
                                time = System.currentTimeMillis() - time;

                                double c = time / Math.pow(n, 2);
                                sumTimeComplexity = sumTimeComplexity + c;
                            }
                            System.out.printf("Average Time Complexity for Insertion Sort\t: %6.10f \n", sumTimeComplexity / 15);
                            System.out.println();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Perform sort (1) or calculate Average Time Complexity (2):\n");
                    int quickSortTest = userInput.nextInt();
                    switch (quickSortTest) {
                        case 1:
                            Randomize.randomize(toBeSorted);
                            time = System.currentTimeMillis();
                            Sorting.quickSort(toBeSorted, 0, toBeSorted.length - 1);
                            time = System.currentTimeMillis() - time;
                            System.out.printf("Runtime for Quick Sort\t: %6.3f seconds\n", time / 1000.0);
                            System.out.println();
                            break;
                        case 2:
                            for (i = 0; i < 15; i++) {
                                Randomize.randomize(toBeSorted);
                                time = System.currentTimeMillis();
                                Sorting.quickSort(toBeSorted, 0, toBeSorted.length - 1);
                                time = System.currentTimeMillis() - time;
                                double c = time / (n * Math.log(n));
                                sumTimeComplexity = sumTimeComplexity + c;
                            }
                            System.out.printf("Average Time Complexity for Quick Sort\t: %6.6f \n", sumTimeComplexity / 15);
                            System.out.println();
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Perform sort (1) or calculate Average Time Complexity (2):\n");
                    int mergeSortTest = userInput.nextInt();
                    switch (mergeSortTest) {
                        case 1:
                            Randomize.randomize(toBeSorted);
                            time = System.currentTimeMillis();
                            Sorting.mergeSort(toBeSorted, 0, toBeSorted.length - 1);
                            time = System.currentTimeMillis() - time;
                            System.out.printf("Runtime for Merge Sort\t: %6.3f seconds\n", time / 1000.0);
                            System.out.println();
                            break;
                        case 2:
                            for (i = 0; i < 15; i++) {
                                Randomize.randomize(toBeSorted);
                                time = System.currentTimeMillis();
                                Sorting.mergeSort(toBeSorted, 0, toBeSorted.length - 1);
                                time = System.currentTimeMillis() - time;
                                double c = time / (n * Math.log(n));
                                sumTimeComplexity = sumTimeComplexity + c;
                            }
                            System.out.printf("Average Time Complexity for Merge Sort\t: %6.6f \n", sumTimeComplexity / 15);
                            System.out.println();
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Perform sort (1) or calculate Average Time Complexity (2):\n");
                    int radixSortTest = userInput.nextInt();
                    switch (radixSortTest) {
                        case 1:
                            Randomize.randomize(toBeSorted);
                            time = System.currentTimeMillis();
                            Sorting.radixSort(toBeSorted, 10); // 10 because 0-9 is 10 digits
                            time = System.currentTimeMillis() - time;
                            System.out.printf("Runtime for Radix Sort\t: %6.3f seconds\n", time / 1000.0);
                            System.out.println();
                            break;

                        case 2:
                            for (i = 0; i < 15; i++) {
                                Randomize.randomize(toBeSorted);
                                time = System.currentTimeMillis();
                                Sorting.radixSort(toBeSorted, 10);
                                time = System.currentTimeMillis() - time;
                                double c = time / (n * 10); //10 because 0-9 is 10 digits
                                sumTimeComplexity = sumTimeComplexity + c;
                            }
                            System.out.printf("Average Time Complexity for Radix Sort\t: %6.10f \n", sumTimeComplexity / 15);
                            System.out.println();
                            break;
                    }
                    break;

                case 5:
                    System.exit(1);
            }
        }
    }
}
