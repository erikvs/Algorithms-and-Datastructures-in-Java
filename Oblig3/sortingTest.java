import java.io.*;
import java.util.*;

public class sortingTest
{
    // Simple performance comparison of sorting algorithms

    public static void main(String args[])
    {
	int  MAX_SEQUENTIAL = 100000, MAX_N = 100000000;
    
	int A[];
	int n = 0;
	long time = 0;

	// Read number of elements to be sorted from command line
	n = Integer.parseInt(args[0]);
	if (n < 0 || n > MAX_N)
	{
	    System.out.println("Use  1 <= n <= " + MAX_N);
	    System.exit(1);
       	}
	A = new int[n];

	sequentialSorting sS = new sequentialSorting();
	logarithmicSorting lS = new logarithmicSorting();
	
	if (n <= MAX_SEQUENTIAL)
	{
	    // Timing of selection sort
	    randomize(A);
	    time = System.currentTimeMillis();
	    sS.selectionSort(A);
	    time = System.currentTimeMillis() - time;
	    System.out.printf("Selection sort\t: %6.3f s\n", time/1000.0);

	    // Timing of insertion sort
	    randomize(A);
	    time = System.currentTimeMillis();
	    sS.insertionSort(A);
	    time = System.currentTimeMillis() - time;
	    System.out.printf("Insertion sort\t: %6.3f s\n", time/1000.0);

	    // Timing of bubble sort
	    randomize(A);
	    time = System.currentTimeMillis();
	    sS.bubbleSort(A);
	    time = System.currentTimeMillis() - time;
	    System.out.printf("Bubble sort\t: %6.3f s\n", time/1000.0);
	}
	else
	    System.out.println("O(n^2) sorting too slow for large n");

	// Timing of shell sort
	randomize(A);
	time = System.currentTimeMillis();
	sS.shellSort(A);
	time = System.currentTimeMillis() - time;
	System.out.printf("Shell sort\t: %6.3f s\n", time/1000.0);

	// Timing of quicksort
	randomize(A);
	time = System.currentTimeMillis();
	lS.quickSort(A, 0, n-1);
    	time = System.currentTimeMillis() - time;
	System.out.printf("Quicksort\t: %6.3f s\n", time/1000.0);

	// Timing of merge sort
	randomize(A);
	time = System.currentTimeMillis();
	lS.mergeSort(A, 0, n-1);
	time = System.currentTimeMillis() - time;
	System.out.printf("Merge sort\t: %6.3f s\n", time/1000.0);
    }

    // Fills array with unsorted random numbers
    public static void randomize(int A[])
    {
	Random r = new Random();
	int n =  A.length;
	int n2 = 2 * n;
	for (int i = 0; i < n; i++)
	    A[i] = r.nextInt(n2);
    }
}
