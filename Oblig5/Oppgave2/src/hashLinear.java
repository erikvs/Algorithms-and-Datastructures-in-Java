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
public class hashLinear {
    // Hashlengde
    private int hashLengde;

    // Hashtabell
    private String hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    /**
     * MY CODE STARTS HERE
     **/

    // Values used to calculate how far insert(String S) and index element, T, checked against have moved from original hashed index.
    int sMove;
    int tMove;

    // Starting index of string S and T.
    int hashT;
    int hashS;

    // The index we are currently working with.
    int tIndex;

    /**
     * MY CODE ENDS HERE
     **/

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashLinear(int lengde) {
        hashLengde = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    // Returnerer load factor
    public float loadFactor() {
        return ((float) n) / hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData() {
        return n;
    }

    // Returnerer antall probes ved innsetting
    public int antProbes() {
        return antProbes;
    }

    // Hashfunksjon
    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    /**
     * MY CODE STARTS HERE
     * Method has been changed to compare inserted String S to string T at current index.
     * Look for English comments to see my implementation.
     **/

    // Function to determine how far T has moved.
    int tMoveFinder() {
        // If starting index of T < current index of T, T has moved, but not wrapped
        if (hashT < tIndex) {
            return tMove = tIndex - hashT;
        }
        // If starting index of T > current index of T, T has moved and wrapped:
        if (hashT > tIndex) {
            return tMove = (hashTabell.length - hashT) + tIndex;
        }
        // If staring index of T == current index of T, T has not moved
        return 0;
    }

    // Function to determine how far S has moved
    int sMoveFinder() {
        // If starting index of S < current index of T, S has moved, but not wrapped
        if (hashS < tIndex) {
            return sMove = 0 + tIndex;
        }
        // If starting index of S > current index of T, S has moved and wrapped:
        if (hashS > tIndex) {
            return sMove = (hashTabell.length - hashS) + tIndex;
        }
        // As a base, sMove is 0
        return 0;
    }

    void insert(String S) {

        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        // While the index(from hash) we want to use is not null.
        while (hashTabell[neste] != null) {
            // Ny probe - each time [neste] is not null, we probe for a potential move of T or check next
            antProbes++;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden på hashtabellen og gjort en rehashing)
            if (n == hashTabell.length) {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }

            // Determine string T starting index.
            hashT = hash(hashTabell[neste]);

            // Determine current index.
            tIndex = Arrays.asList(hashTabell).indexOf(hashTabell[neste]);

            // Determine how far current T has moved
            int tMove = tMoveFinder();

            // sMove will be 0 the first time into the while, and is iterated when we iterate neste (move one to the right). While we are working with S, sMove increases
            int sMove = sMoveFinder();

            // If S has moved further than T: we need to move rotate the array one space to the right until we have a free space
            if (sMove > tMove) {
                // Rotates the array 1 space to the right one space at a time, until we find a free space. This also covers wraparound
                while (hashTabell[neste] != null) {
                    Collections.rotate(Arrays.asList(hashTabell), 1);
                }
            }
            // If T has moved further than S or if S and T have moved the same distance: try the next index in the array
            else {
                neste++;
                //Wrap-around
                if (neste >= hashLengde) {
                    neste = 0;
                }
            }
        }
        // Lagrer tekststrengen på funnet indeks
        hashTabell[neste] = S;

        // Øker antall elementer som er lagret
        n++;
    }
    /**
     * MY CODE ENDS HERE
     * **/
    // Søking etter tekststreng med lineær probing
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S) {
        // Beregner hashverdien
        int h = hash(S);

        // Lineær probing
        int neste = h;

        while (hashTabell[neste] != null) {
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
    /**
     * Reads the 600 words from .txt submitted with code and hashes them into an array
     **/
    public static void main(String[] args) {
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
        while (read.hasNext()) {
            String word = read.nextLine();
            hL.insert(word);
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf("Load factor : %5.3f\n", hL.loadFactor());
        System.out.println("Probes      : " + hL.antProbes());
        System.out.println();
        System.out.println("RESULT:");
        System.out.println();
        // ALSO PRINTS THE FINAL LIST!
        for (int i = 0; i < hL.hashLengde; i++) {
            System.out.println("word at index " + i +
                    " : " + hL.hashTabell[i]);
        }
    }
}

