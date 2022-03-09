public class sequentialSorting
{
    public static void selectionSort(int A[])
    {
	// Utplukksortering av array med heltall

	int n = A.length;
	int min, tmp;

	for (int i = 0; i < n-1; i++)
	{
	    // A er riktig sortert t.o.m. indeks i-1
	    // Finner det minste av de usorterte elementene
	    min = i;
	    for (int j = i+1; j < n; j++)
		if (A[j] < A[min])
		    min = j;
	    // Swapper det minste elementet og element nummer i
	    tmp = A[min];
	    A[min] = A[i];
	    A[i] = tmp;
	}
    }

    public static void insertionSort(int A[])
    {
	// Innstikksortering av array med heltall

	int n = A.length;
	int key;

	for (int i = 1; i < n; i++)
	{
	    // A er sortert t.o.m. indeks i-1
	    key = A[i];
	    int j = i;
	    // Setter element nummer i på riktig plass
	    // blant de i-1 første elementene
	    while (j > 0 && A[j-1] > key)
	    {
		A[j] = A[j-1];
		j--;
	    }
	    A[j] = key;
	}
    }

    public static void bubbleSort(int A[])
    {
	// Bubble sort av array med heltall
	//
	// Sorterer anderledes enn lærebokas bubble sort, ved at
	// små elementer her flyttes fremover i arrayen, i stedet for
	// som i læreboka å flytte store elementer bakover

	int n = A.length;
	int tmp;

	for (int i = 0; i < n; i++)
	{
	    // A er riktig sortert t.o.m. indeks i-1
	    // Flytter det minste av de gjenværende elementene
	    // til indeks i ved å swappe små elementer fremover
	    for (int j = n-1; j > i; j--)
		if (A[j] < A[j-1])
		{
		    tmp = A[j];
		    A[j] = A[j-1];
		    A[j-1] = tmp;
		}
	}
    }

    public static void shellSort(int A[])
    {
	// Shell sort av array med heltall
	// Gap-sekvens: n/2, (n/2)/2.2, (n/2)/(2.2)^2, (n/2)/2.2^3...

	int n = A.length;
	int gap = n/2;

	while (gap > 0)
	{
	    for (int i = gap; i < n; i++)
	    {
		int j = i;
		int temp = A[i];

		while (j >= gap && A[j - gap] > temp)
		{
		    A[j] = A[j - gap];
		    j = j - gap;
		}
		A[j] = temp;
	    }
	    if (gap == 2)
		gap = 1;
	    else
		gap *= (5.0 / 11);
	}
    }
}
