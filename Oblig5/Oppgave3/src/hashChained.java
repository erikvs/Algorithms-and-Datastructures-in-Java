import java.io.*;
import java.util.Scanner;

// Hashing av tekststrenger med kjeding i lenket liste
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell/lange lister
// - Tilbyr bare innsetting og søking
//
public class hashChained
{
    // Indre klasse:
    // Node med data, kjedes sammen i lenkede lister
    //
    private class hashNode
    {
        // Data, en tekststreng
        String data;
        // Neste node i listen
        hashNode neste;

        // Konstruktør for listenoder
        public hashNode(String S, hashNode hN)
        {
            data = S;
            neste = hN;
        }
    }

    // Hashlengde
    private int hashLengde;

    // Hashtabell, pekere til lister
    private hashNode hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall kollisjoner ved innsetting
    private int antKollisjoner;

    // Konstruktør
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public hashChained(int lengde)
    {
        hashLengde = lengde;
        hashTabell = new hashNode[lengde];
        n = 0;
        antKollisjoner = 0;
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

    // Returnerer antall kollisjoner ved innsetting
    public int antKollisjoner()
    {
        return antKollisjoner;
    }

    // Hashfunksjon
    int hash(String S)
    {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    // Innsetting av tekststreng med kjeding
    //
    void insert(String S)
    {
        // Beregner hashverdien
        int h = hash(S);

        // Øker antall elementer som er lagret
        n++;

        // Sjekker om kollisjon
        if (hashTabell[h] != null)
            antKollisjoner++;

        // Setter inn ny node først i listen
        hashTabell[h] = new hashNode(S, hashTabell[h]);
    }

    // Søking etter tekststreng i hashtabell med kjeding
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S)
    {
        // Finner listen som S skal ligge i
        hashNode hN = hashTabell[hash(S)];

        // Leter gjennom listen
        while (hN != null)
        {
            // Har vi funnet tekststrengen?
            if (hN.data.compareTo(S) == 0)
                return true;
            // Prøver neste
            hN = hN.neste;
        }
        // Finner ikke strengen, har kommet til slutten av listen
        return false;
    }

    /**
     * MY CODE STARTS HERE
     * Method for removing String S
     **/
    void remove(String S) {

        // Finner listen som S skal ligge i
        hashNode hN = hashTabell[hash(S)];

        // Checking each index the length of hashTabell
        for (int i = 0; i < hashTabell.length; i++) {

            // If node is null, we go to next node.
            if (hN.data == null) {
                continue;}
            // If node has the word we want, set the data in it to null (this means the pointer to the following element in the chain still exists).
            // When we found the element we wanted to delete, and have done so we break the loop.
            if (hN.data.compareTo(S) == 0){
                hN.data = null;
                break;}
            }
        }


    /** MAIN HAS BEEN REWORKED PARTS OF ORIGINAL MAIN REMAINS (the parts where comments are in Norwegian)**/
    public static void main(String[] args) {
        //Takes user input from console and assigns it as hashLength.
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter length of storage array (words.txt currently has 3 words used for testing purposes): \n");

        int hashLengde = userInput.nextInt();

        // Lager ny hashTabell
        hashChained hC = new hashChained(hashLengde);

        // Words to be hashed.
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
            hC.insert(word);
            System.out.println(word + " has been added");
        }

        // Removing some of the added elements.
        hC.remove("DeleteMe");
        System.out.println("DeleteMe has been removed");
        hC.remove("DeleteMe500");
        System.out.println("DeleteMe500 has been removed");

        // Test to see if I can add DeleteMe back to the node I just set the datavalue of to null
        //hC.insert("DeleteMe");
        //System.out.println("DeleteMe has been added AGAIN!");

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hC.antData());
        System.out.printf( "Load factor : %5.3f\n",  hC.loadFactor());
        System.out.println("Kollisjoner : " + hC.antKollisjoner());
        System.out.println();
        System.out.println("RESULT:");
        System.out.println();
        // ALSO PRINTS THE FINAL LIST!
        for (int i = 0; i < hC.hashLengde; i++) {
            if (hC.hashTabell[i] == null){
                System.out.println("Index " + i + " is null.");}
            if (hC.hashTabell[i] != null && hC.hashTabell[i].data == null){
                System.out.println("Word that was stored at index " + i + " has been deleted.");}
            if (hC.hashTabell[i] != null && hC.hashTabell[i].data != null)
            {
                System.out.println("Word at index " + i + " : " + hC.hashTabell[i].data);
            }
            }
    }
}

