import java.util.*;

public class MaggieFei_336
{
    public static void main (String [] args)
    {

        // Declare variables
        int n = 0;

        Scanner kbd = new Scanner (System.in);
        System.out.print ("Input the number of rows and columns of the table; enter only one number -> ");
        n = kbd.nextInt();

        // Define the array
        int [] [] arr = new int [n] [n] ;

        chess (arr);
        print (arr);

    }

    public static void print (int [][] a)
    {
        for ( int row = 0 ; row < a.length ; row ++ )
        {

            for ( int col = 0 ; col < a[ row ].length ; col ++ )
            {
                System.out.printf ("%6d " , a[row][col] );
            }

            System.out.println ();

        }

    }

    public static void chess (int [][] a)
    {

        for (int row = 0; row < a.length ; row ++)
        {
            for (int col = 0; col < a[row].length; col ++)
            {

                if (a.length % 2 == 0)
                {
                    if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0))
                    {
                        a [row][col] = 0;
                    }

                    else
                    {
                        a [row][col] = 1;
                    }
                }

                if (a.length % 2 != 0)
                {
                    if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0))
                    {
                        a [row][col] = 1;
                    }

                    else
                    {
                        a [row][col] = 0;
                    }

                }
            }
        }

    }





}