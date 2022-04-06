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


    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    /** MY CODE STARTS HERE
     * Method has been changed to compare inserted String S to string T at current index.
     * Look for English comments to see my implementation.
     * **/
    void insert(String S) {

        // Beregner hashverdien
        int h = hash(S);

        // Print to keep track of what index the program is working towards before loop starts.
        System.out.println("Current value being hashed to index:" + h);

        // Lineær probing
        int neste = h;

        // Values used to calculate how far insert(String) and index element checked against have moved from original hashed index.
        int sMove = 0;
        int tMove = 0;

        // Variable to store T in IF we need to move it from current index.
       // String T = "";

        // Hashed index of string T.
        int hashT = 0;

        // The index we are currently working with.
        int tIndex = 0;

        /**Robin Hood
         *
         * when placing an element S one of two alternatives exist:
         * 1. element in structure at desired index, T, is moved one index to the right to free up the desired index.
         * 2. place S in the next index (as in linear probing)
         * Alternative 1 is chosen if S has moved further away from its original hash index than T
         * Else, choose alternative 2
         *
         * When I store an element, I need to store it with a reference value that I can iterate
         * - this could be a hashmap where key value is stored, key is string to be hashed, value is the PSL (probe sequence length) which can be increased as the key moves.
         *
         * Attempt
         *
         * insert value S, for each move count a temp variable (sMove) which is used to determine how far it has moved from original position
         * for each T value, hash T to get the original index T was supposed to go to.
         *
         * if hash(T) > T index, calculate tMove by (T index) - hash(T)
         * else (that means T index is greater than hash(T), so a wraparound has occured), calculate tMove by (table.length - hash(T))+(T index)
         *
         * compare S to T by comparing sMove > tMove
         * if sMove > tMove store T in temp variable, assign S as new T, assign temp T as new S, assign tMove as sMove, iterate sMove once, Continue (meaning T (now current S) is used to check against other values and movement is stored on the original movement of T)
         *else (when S is smaller than T) assign tMove as O and iterate sMove once, continue  (S is used to check against other values)
         *
         * **/

        // While the index(from hash) we want to use is not null.
        while (hashTabell[neste] != null) {

            // Ny probe - every time we do a probe, we move one index to the right in the array.
            antProbes++;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden på hashtabellen og gjort en rehashing)
            if (n == hashTabell.length) {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }

            // For each ++ of antProbes, we also increase the movement of the element we are currently working with.
            sMove++;

            // Determine string T starting index.
            hashT = hash(hashTabell[neste]);

            // Determine current index.
            // We are currently at where we started + how far we have moved. (sMove accounts for wraparound already)
            tIndex = hash(S) + sMove;

            //System.out.println("Currently working with index:" + tIndex);

            // If statement to determine how far T has moved.
            // If staring index of T == current index of T, T has not moved
            if (hashT == tIndex) {
                tMove = 0;
            }

            // If starting index of T < current index of T, T has moved, but not wrapped
            if (hashT < tIndex) {
                tMove = tIndex - hashT;
            }

            // If starting index of T > current index of T, T has moved and wrapped:
            // (hashTabell.length-hashT) determines movement out the lenght of the array, + (tIndex) determines movement from index 0 to starting index
            if (hashT > tIndex) {
                tMove = (hashTabell.length - hashT) + tIndex;
            }

            // If S has moved further than T: we need to move rotate the array one space to the right until we have a free space
            if (sMove > tMove) {
                // Rotates the array 1 space to the right. This also covers wraparound
                System.out.println("Rotating Array!");
                Collections.rotate(Arrays.asList(hashTabell), 1);
            }

            // If T has moved further than S or if S and T have moved the same distance: try the next index in the array
            if (sMove <= tMove) {
                sMove++;
                neste++;
            }

            //Wrap-around
            if (neste >= hashLengde) {
                neste = 0;
            }

            // IF we get to this point in the while, S has either moved further
            neste++;

        }
        // Lagrer tekststrengen på funnet indeks
        hashTabell[neste] = S;

        // Øker antall elementer som er lagret
        n++;
    }

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
    public static void main(String[] args){
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
        for (int i = 0; i < hL.hashLengde; i++) {
            System.out.println("word at index " + i +
                    " : " + hL.hashTabell[i]);
        }
    }
}

