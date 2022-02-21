import java.util.Random;
import java.util.Scanner;

// Rekursiv labyrint
//
// Med hint til hva som må skrives om for å lage en løsning på
// SPRINGERPROBLEMET

public class labyrint_2
{
    // Verdiene som kan lagres i labyrinten
    //
    // Hint til SPRINGERPROBLEMET: Trenger bare å lagre om en rute er brukt
    // eller ledig

    int STENGT = 0, FRI = 1, BRUKT = 2, VEI = 3;
    
    // Størrelse på kvadratisk labyrint og 2D-tabell som lagrer
    // labyrinten
    //
    // Den samme datastrukturen kan lagre sjakkbrettet i SPRINGERPROBLEMET
    int n;
    int L[][];

    
    // Konstruktør som oppretter en tilfeldig labyrint med en gitt
    // prosentandel blokkerte ruter.
    //
    // I SPRINGERPROBLEMET lages det her et n X n sjakkbrett der alle
    // rutene til å begynne med er FRI
    public labyrint_2(int n, int prosentBlokkert)
    {
	this.n = n;
	L = new int[n][n];
	Random R = new Random();

	for (int i = 0; i < n; i++)
	    for (int j = 0; j < n; j++)
		if (R.nextInt(100) < prosentBlokkert)
		    L[i][j] = STENGT;
		else
		    L[i][j] = FRI;
    }

    
    // Funksjonen finnVei leter rekursivt etter en vei gjennom
    // labyrinten fra rute (i,j) til rute (n-1,n-1).
    // Returnerer true hvis vi fant en slik vei, false ellers
    boolean finnVei(int i, int j)
    {
	// Bunn i rekursjonen: Ferdig hvis vi er i nedre høyre hjørne
	//
	// SPRINGERPROBLEMET må her ha et annet stoppkriterium
	if (i == n-1 && j == n-1)
	{
	    // Markerer at siste rute i labyrinten ligger på veien som
	    // er funnet, og returnerer true
	    L[i][j] = VEI;
	    return true;
	}
	
	// Markerer at rute (i,j) nå er oppsøkt
	//
	// I SPRINGERPROBLEMET må vi her i tillegg lagre både antall
	// flytt som er gjort frem til nå, og hvike flytt som er gjort
	// for å komme hit.
	L[i][j] = BRUKT;

	// Prøver alle fire mulige lovlige veier videre fra rute (i,j)
	// i denne rekkefølgen: 1. Høyre, 2. Ned , 3. Venstre, 4. Opp
	//
	// For å løse SPRINGERPROBLEMET, må koden her utvides til å
	// håndtere alle de ÅTTE mulige lovlige stegene som en
	// springer kan ta fra rute (i,j)
	
	int dI[] = {  0,  1,  0, -1};
	int dJ[] = {  1,  0, -1,  0};

	for (int k = 0; k < 4; k++)
	{
	    int nyI = i + dI[k];
	    int nyJ = j + dJ[k];

	    // Sjekker om det er lovlig å gå til ny posisjon
	    if (nyI >=0 && nyI < n && nyJ >=0 && nyJ < n && L[nyI][nyJ] == FRI)
	    {	    
		// Prøver å finne vei videre rekursivt
		if (finnVei(nyI, nyJ))
		{
		    // Her vet vi at det ble funnet en vei gjennom
		    // labyrinten fra rute (i,j). Merker av at (i,j)
		    // ligger på denne veien og stopper deretter videre
		    // leting etter vei ved å returnere true
		    L[i][j] = VEI;
		    return true;
		}
	    }
	}
	// Hvis vi kommer hit i koden, fantes det ingen vei gjennom
	// labyrinten fra rute (i,j), returnerer false
	//
	// I SPRINGERPROBLEMET må dette siste tilfellet, der vi ikke
	// finner noen lovlig løsning med start i rute (i,j),
	// behandles litt anderledes. I labyrinten er det ingen vits i
	// å gå tilbake til en rute der vi har vært før. I
	// springerproblemet er det ikke slik, der må vi nå markere at
	// ruten er blitt ledig igjen, slik at den kan brukes på nytt
	// i senere forsøk på å bygge ut en løsning. Øvrig
	// datastruktur som brukes til å lagre løsningen må også
	// oppdateres slik at dette steget som ikke ledet til løsning
	// blir fjernet.
	
	return false;
    }

    
    // Gjør om labyrinten til en tekststreng for utskrift, markerer
    // evt. funnet vei med stjerner ('*')
    public String toString()
    {
        String result = "\n";
        for (int i = 0; i < n; i++)
        {
	    for (int j = 0; j < n; j++)
		if (L[i][j] == VEI)
		    result += "* ";
		else
		    result += L[i][j] + " ";
            result += "\n";
        }
        return result;
    }

    
    // Testprogram
    public static void main(String argv[])
    {
	// Leser størrelsen på labyrinten og prosentandel blokkerte ruter
	Scanner scanner = new Scanner(System.in);
	System.out.print("n?: ");
	int n = scanner.nextInt();
	System.out.print("% blokkerte ruter?: ");
	int prosentBlokkert = scanner.nextInt();

	// Oppretter ny labyrint
	labyrint lab = new labyrint(n, prosentBlokkert);
	
	// Leter etter vei fra øvre venstre hjørne
	boolean funnetVei = lab.finnVei(0, 0);
	
	// Skriver ut labyrinten (og evt. funnet vei)
	System.out.println(lab);
	if (!funnetVei)
	    System.out.println("Fant ingen vei gjennom labyrinten");
    }
}
