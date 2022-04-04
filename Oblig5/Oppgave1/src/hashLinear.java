import java.io.*;

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
    //TODO: need to add hash lenght as user input

    // Hashlengde
    private int hashLengde;

    // Hashtabell
    private String hashTabell[];  //TODO: this is a one dimensional array

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

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
        return h % hashLengde;            //hashlength 4 and h 40 returns 0 (element is placed at index 0), hashlength 4 and h 65 returns 1 (element is placed at index 1)
    }

    // Innsetting av tekststreng med lineær probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    //

    /** MY CODE STARTS HERE **/
    //TODO: CHANGE THIS
    void insert(String S)
    {
        // Beregner hashverdien
        int h = hash(S);  //determines where current element should be placed

        // Lineær probing
        int neste = h;

        // while index(which we get from hashing the input) of hashtable has a value
        while (hashTabell[neste] != null)
        {
            // Ny probe
            antProbes++;

            // for each i length of hashtabell, start at the END of hashtable
            // if i == null continue
            // if i =! null
            // move element one position to the right in the array
            // if new pos is last pos >= hashLengde, set it to 0
            // here we gotta check if 0 has an element, and if so, we gotta move that

            //alternatively, if the element of the hashtable is != null
            //make new empty hashtable, move each element one position into a new array
            // stick the working element



            //TODO: need to change this to something like, move element in that spot +1 in line


            // Denne indeksen er opptatt, prøver neste - meaning place it at hash value +1 etc
            neste++;

            // hashTabell[neste] = hashTabell[neste+1];

            //TODO: This bottom part is fine

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden på hashtabellen og gjort en rehashing)
            if (neste == h)
            {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }

        // Lagrer tekststrengen på funnet indeks - in this case the code has determined hashTabell[neste] == null -- being empty, it adds the element
        hashTabell[neste] = S;

        // Øker antall elementer som er lagret
        n++;
    }

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

    // Enkelt testprogram:
    //
    // * Hashlengde gis som input på kommandolinjen
    //
    // * Leser tekststrenger linje for linje fra standard input
    //   og lagrer dem i hashtabellen
    //
    // * Skriver ut litt statistikk etter innsetting
    //
    // * Tester om søk fungerer for et par konstante verdier
    //

    /** MAIN HAS BEEN REWORKED **/
    public static void main(String[] args)
    {
        //Takes user input from console and assigns it as hashLength.
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter hashLength: \n");

        int hashLengde = userInput.nextInt();

        // Lager ny hashTabell
        hashLinear hL = new hashLinear(hashLengde); //TODO: creates a new one-dimensional array called hL with a lenght of hashLengde

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
            hL.insert(read.nextLine());
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
        System.out.println("Probes      : " + hL.antProbes());

        for (int i=0; i<hL.hashLengde; i++)
        {
            System.out.println("word at index " + i +
                    " : "+ hL.hashTabell[i]);
        }


        /*// Et par enkle søk
        String S = "Volkswagen Karmann Ghia";
        if (hL.search(S))
            System.out.println("\"" + S + "\"" + " finnes i hashtabellen");
        S = "Il Tempo Gigante";
        if (!hL.search(S))
            System.out.println("\"" + S + "\"" + " finnes ikke i hashtabellen");
*/
    }
}

