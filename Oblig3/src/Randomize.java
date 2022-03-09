import java.util.Random;

public class Randomize {

    // Fills array with unsorted random numbers
    public static void randomize(int toBeSorted[])
    {
        Random r = new Random();
        int x =  toBeSorted.length;
        int x2 = 2 * x;
        for (int i = 0; i < x; i++)
            toBeSorted[i] = r.nextInt(x2);
    }
}
