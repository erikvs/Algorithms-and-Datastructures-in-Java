import java.util.Random;
import java.util.LinkedList; 
import java.util.Queue;

// Radixsortering med Javas egen køimplementasjon, som implementerer
// køen som en lenket liste. Merk at enqueue-metoden heter "add" og
// dequeue-metoden heter "remove" i Java-implementasjonen av kø-

public class radixSort2
{
    void sort(int a[], int maksAntSiffer)
    {
	// Radixsortering av en array a med desimale heltall
	// maksAntSiffer: Maksimalt antall siffer i tallene

	int ti_i_m = 1; // Lagrer 10^m 
	int n = a.length;

	// Oppretter 10 tomme køer 
	Queue<Integer>[] Q = (Queue<Integer>[])(new Queue[10]);

	for (int i = 0; i < 10; i++)
	    Q[i] = new LinkedList(); 
            
	// Sorterer på et og et siffer, fra høyre mot venstre

	for (int m = 0; m < maksAntSiffer; m++)
	{
	    // Fordeler tallene i 10 køer
	    for (int i = 0; i < n; i++)
	    {
		int siffer = (a[i] / ti_i_m) % 10;
		Q[siffer].add(new Integer(a[i]));
	    }

	    // Tømmer køene og legger tallene fortløpende tilbake i a
	    int j = 0;
	    for (int i = 0; i < 10; i++)
		while (!Q[i].isEmpty())
		    a[j++] = (int) Q[i].remove();

	    // Beregner 10^m for neste iterasjon
	    ti_i_m *= 10;
	}
    }
        
    public static void main(String[] args)
    {
	// Leser antall tall og antall siffer fra kommandolinja
	int n = Integer.parseInt(args[0]);
	int m = Integer.parseInt(args[1]);

        // Beregner maks.verdi for tallene som skal sorteres
	int ti_i_m = (int) java.lang.Math.pow(10,m);
	int a[] = new int[n];
        Random R = new Random(); 

	// Fyller array med tilfeldige tall mellom 0 og 10^m - 1 
	for (int i = 0; i < n; i++)
	    a[i] = R.nextInt(ti_i_m); 

	// Sorterer
	radixSort2 rS = new radixSort2();
	rS.sort(a, m);
	
	// Skriver ut sortert array formatert i kolonner
	int linjebredde = 80;
	int tall_pr_linje = linjebredde/(m + 1);
	for (int i = 0; i < n; i++)
	{
	    String format = "%" + m + "d ";
	    System.out.printf(format, a[i]);
	    if (((i+1) % tall_pr_linje == 0) || (i == n - 1))
		System.out.println();
	}
    }

}
