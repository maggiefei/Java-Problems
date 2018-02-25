import java.util.* ;
public class MaggieFei_St4_2
{
    public static Scanner kbd = new Scanner (System.in);

    public static void main (String [] args)
    {
        histogram ();
    }

    public static void histogram ()
    {
        System.out.print("Enter the number of values -> ");
        int number = kbd.nextInt();

        int [] arr = new int [number];

        for (int i = 0; i < number; i ++)
        {
            arr [i] = (int)(number * Math.random ());
        }

        System.out.println ("The data  -> ");
        for (int i = 0; i < number; i ++)
        {
            System.out.print (arr [i]);
            System.out.print (" ");
        }

        System.out.println("\nThe histogram");

        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int max = arr[0];

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > max)
            {
                max = arr[i];
            }
        }

        int cutoff = max;

        for (int i = 0; i < arr.length; i ++)
        {

            for (int j = 0; j < arr.length; j ++)
            {
                if (arr[j] >= cutoff)
                {
                    char curChar = abc.charAt(j);

                    if ((j+1) == number)
                    {
                        System.out.print ("" + curChar + "\n");
                    }
                    else
                    {
                        System.out.print ("" + curChar);
                    }
                }

                else
                {
                    if ((j+1) == number)
                    {
                        System.out.print (" " + "\n");
                    }
                    else
                    {
                        System.out.print (" ");
                    }
                }
            }

            cutoff --;

            if (cutoff == 0)
            {
                break;
            }

        }

        System.out.print ("The scale factor -> ");
        int scale = kbd.nextInt();

        while (scale != 0)
        {
            scaleHistogram (arr, max, scale, abc);
            System.out.print ("The scale factor -> ");
            scale = kbd.nextInt();
        }

    }

    public static void scaleHistogram (int arr [], int max, int scale, String abc)
    {

        for (int i = 0; i < arr.length; i ++)
        {
            arr [i] = arr [i] * scale;
        }

        for (max = max*scale; max > 0; max --)
        {
            for (int j = 0; j < arr.length; j ++)
            {
                for (int x = 0; x < scale; x ++)
                {
                    if (arr [j] >= max)
                    {
                        char curChar = abc.charAt(j);
                        System.out.print (curChar);
                    }
                    else
                    {
                        System.out.print (" ");
                    }
                }

            }
            System.out.print ("\n");
        }

        if (scale != 0)
        {
            for (int i = 0; i < arr.length; i ++)
            {
                arr [i] = arr [i] / scale;
            }
            max = max / scale;
        }

    }

}