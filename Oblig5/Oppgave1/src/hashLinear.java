import java.io.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

// Hashing av tekststrenger med lineær probing
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og søking
//
public class hashLinear
{

    // Hashlengde
    private int hashLengde;

    // Hashtabell
    private String hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    public boolean keepRunning;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashLinear(int lengde)
    {
        hashLengde = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
        return ((float) n)/hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData()
    {
        return n;
    }

    // Returnerer antall probes ved innsetting
    public int antProbes()
    {
        return antProbes;
    }

    // Hashfunksjon
    int hash(String S)
    {
        int h = Math.abs(S.hashCode());
        // Hashlength 4 and h 40 returns 0 (element is placed at index 0), hashlength 4 and h 65 returns 1 (element is placed at index 1)
        return h % hashLengde;
    }

    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes

    /** MY CODE STARTS HERE**/
    // Reworked the exit check and kept the parts of the supplied code that made sense to keep.
    void insert(String S)
    {
        // Beregner hashverdien
        int h = hash(S);  //determines where current element should be placed

        // Print to keep track of what index the program is working towards freeing before loop starts.
        System.out.println("Current value being hashed to index:" + h);

        // Lineær probing
        int neste = h;

        // While the index(from hash) we want to use is not null.
        while (hashTabell[neste] != null) {

            // Checks to see if array is full and if so exits program.
            if (n == hashTabell.length) {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }

            // Ny probe - this is just a counter for how many times a sort is being performed
            antProbes++;

            // Rotates the array 1 space to the right. This also covers wraparound
            Collections.rotate(Arrays.asList(hashTabell), 1);
        }
        // Loop is broken meaning array has been rotated 1 index at a time until desired index is free.

        // Lagrer tekststrengen på funnet indeks
        hashTabell[neste] = S;

        // Øker antall elementer som er lagret
        n++;
    }
    /** MY CODE STOPS HERE**/


    // Søking etter tekststreng med lineær probing
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S)
    {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        while (hashTabell[neste] != null)
        {
            // Har vi funnet tekststrengen?
            if (hashTabell[neste].compareTo(S) == 0)
                return true;

            // Prøver neste mulige
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi,
            // finnes ikke strengen i tabellen
            if (neste == h)
                return false;
        }

        // Finner ikke strengen, har kommet til en probe som er null
        return false;
    }


    /** MAIN HAS BEEN REWORKED PARTS OF ORIGINAL MAIN REMAINS (the parts where comments are in Norwegian)**/
    /** Reads the 600 words from .txt submitted with code and hashes them into an array**/
    public static void main(String[] args)
    {
        //Takes user input from console and assigns it as hashLength.
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter length of storage array (words.txt currently contains 600 words): \n");

        int hashLengde = userInput.nextInt();

        // Lager ny hashTabell - array hL med lengde hashLengde
        hashLinear hL = new hashLinear(hashLengde);

        //words to be hashed
        File toHash = new File("words.txt");
        Scanner read = null;
        try {
            read = new Scanner(toHash);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Leser input og hasher alle linjer
        while(read.hasNext()){
            String word = read.nextLine();
            hL.insert(word);
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
        System.out.println("Probes      : " + hL.antProbes());
        System.out.println();
        System.out.println("RESULT:");
        System.out.println();
        for (int i=0; i<hL.hashLengde; i++)
        {
            System.out.println("word at index " + i +
                    " : "+ hL.hashTabell[i]);
        }
    }
}

