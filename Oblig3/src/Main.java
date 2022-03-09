public class Main {
    public static void main(String[] args) {

        //TODO: quicksort only works for 21 000 or lower.
        int n = 21000; //TODO: make this store user input
        int[] toBeSorted = new int[n];
        long time = 0;

        //fills array with n random ints
        Randomize.randomize(toBeSorted);

        int sortMethod = 4; //TODO: make this store user input

        switch (sortMethod) {
            case 1:

                int insertionSortTest = 2; //TODO: make this store user input

                switch (insertionSortTest) {
                    case 1:
                        time = System.currentTimeMillis();
                        Sorting.insertionSort(toBeSorted);
                        time = System.currentTimeMillis() - time;
                        System.out.printf("Runtime for Insertion Sort\t: %6.3f s\n", time / 1000.0);
                        break;
                    case 2:
                        int i;
                        double sumTimeComplexity = 0;
                        for (i = 0; i < 15; i++) {
                            time = System.currentTimeMillis();
                            Sorting.insertionSort(toBeSorted);
                            time = System.currentTimeMillis() - time;

                            double c = time / Math.pow(n, 2); //TODO Think math here is fine, need to get that confirmed.
                            sumTimeComplexity = sumTimeComplexity + c;
                        }
                        System.out.printf("Average Time Complexity for Insertion Sort\t: %6.10f \n", sumTimeComplexity / 15); //TODO: what is this value, milliseconds?
                        break;
                }
                break;

            case 2:
                int quickSortTest = 2; //TODO: make this store user input

                switch (quickSortTest) {
                    case 1:
                        time = System.currentTimeMillis();
                        Sorting.quickSort(toBeSorted, 0, toBeSorted.length - 1);
                        time = System.currentTimeMillis() - time;
                        System.out.printf("Runtime for Quick Sort\t: %6.3f s\n", time / 1000.0);
                        break;
                    case 2:
                        int i;
                        double sumTimeComplexity = 0;
                        for (i = 0; i < 15; i++) {
                            time = System.currentTimeMillis();
                            Sorting.quickSort(toBeSorted, 0, toBeSorted.length - 1);
                            time = System.currentTimeMillis() - time;

                            double c = time / (n * Math.log(n));
                            sumTimeComplexity = sumTimeComplexity + c;
                        }
                        System.out.printf("Average Time Complexity for Quick Sort\t: %6.6f \n", sumTimeComplexity / 15); //TODO: what is this value, milliseconds?
                        break;
                }
                break;

            case 3:
                int mergeSortTest = 2; //TODO: make this store user input
                switch (mergeSortTest) {
                    case 1:
                        time = System.currentTimeMillis();
                        Sorting.mergeSort(toBeSorted, 0, toBeSorted.length - 1);
                        time = System.currentTimeMillis() - time;
                        System.out.printf("Runtime for Merge Sort\t: %6.3f s\n", time / 1000.0);
                        break;
                    case 2:
                        int i;
                        double sumTimeComplexity = 0;
                        for (i = 0; i < 15; i++) {
                            time = System.currentTimeMillis();
                            Sorting.mergeSort(toBeSorted, 0, toBeSorted.length - 1);
                            time = System.currentTimeMillis() - time;

                            double c = time /  (n * Math.log(n));
                            sumTimeComplexity = sumTimeComplexity + c;
                        }
                        System.out.printf("Average Time Complexity for Merge Sort\t: %6.6f \n", sumTimeComplexity / 15); //TODO: what is this value, milliseconds?
                        break;
                }
                break;

            case 4:
                int radixSortTest = 2; //TODO: make this store user input
                switch (radixSortTest) {
                    case 1:
                        time = System.currentTimeMillis();
                        Sorting.radixSort(toBeSorted, 10); // 10 because 0-9 is 10 digits
                        time = System.currentTimeMillis() - time;
                        System.out.printf("Runtime for Radix Sort\t: %6.3f s\n", time / 1000.0);
                        break;

                    case 2:
                        int i;
                        double sumTimeComplexity = 0;
                        for (i = 0; i < 15; i++) {
                            time = System.currentTimeMillis();
                            Sorting.radixSort(toBeSorted, 10);
                            time = System.currentTimeMillis() - time;
                            //TODO: casting to double correct? or redundant?
                            double c = time / (double) (n * 10); //10 because 0-9 is 10 digits
                            sumTimeComplexity = sumTimeComplexity + c;
                        }
                        System.out.printf("Average Time Complexity for Radix Sort\t: %6.10f \n", sumTimeComplexity / 15); //TODO: what is this value, milliseconds?
                        break;
                }
                break;
        }
    }
}
