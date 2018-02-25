import java.util.* ;
public class MaggieFei_St3
{
    public static Scanner kbd = new Scanner (System.in);

    public static void main (String [] args)
    {
        matrix ();
    }

    public static void matrix ()
    {
        System.out.print ("Size of the matrix -> ");
        int n = kbd.nextInt();

        int [] [] arr = new int [n][n];

        int counter = 1;

        for (int row = 0 ; row < n ; row ++)
        {
            for (int col = 0 ; col < n ; col ++ )
            {
                arr[row][col] = counter ;
                counter ++;
            }
        }

        print (arr);

        System.out.print ("Degrees -> ");
        int degree = kbd.nextInt();

        while (degree != 0)
        {
            int degrees = degrees(degree);

            if (degrees == 90)
            {
                horizontalFlip (arr);
                diagonalFlip (arr);
                print (arr);
            }

            if (degrees == 180)
            {
                verticalFlip (arr);
                horizontalFlip (arr);
                print (arr);
            }

            if (degrees == 270)
            {
                verticalFlip (arr);
                diagonalFlip (arr);
                print (arr);
            }

            if (degrees == 0)
            {
                print (arr);
            }

            System.out.print ("Degrees -> ");
            degree = kbd.nextInt();
        }
    }

    public static void print(int [][] a)
    {

        for ( int row = 0 ; row < a.length ; row ++ )
        {

            for ( int col = 0 ; col < a[ row ].length ; col ++ )
            {
                System.out.printf ("%4d " , a[row][col] );
            }

            System.out.println ();

        }

    }

    public static int degrees (int d)
    {
        while (d > 360)
        {
            d = d - 360;
        }

        while (d < -360)
        {
            d = d + 360;
        }

        if (d >= 0 && d < 45)
        {
            return 0;
        }

        else if ((d >= 45 && d <= 134) || (d <= -225 && d >= -314))
        {
            return 90;
        }

        else if ((d >= 135 && d <= 224) || (d <= -135 && d >= -224))
        {
            return 180;
        }

        else if ((d >= 225 && d <= 314) || (d <= -45 && d >= -134))
        {
            return 270;
        }

        else
        {
            return 0;
        }
    }

    public static void verticalFlip (int arr [][])
    {
        for (int row = 0 ; row < arr.length ; row ++)
        {
            for (int col = 0 ; col < (arr[row].length / 2) ; col ++ )
            {
                int temp = arr [row][col];
                arr [row][col] = arr [row][arr[row].length - col - 1];
                arr [row][arr[row].length - col - 1] = temp;
            }
        }
    }

    public static void horizontalFlip (int arr [][])
    {
        for (int row = 0 ; row < (arr.length / 2) ; row ++)
        {
            for (int col = 0 ; col < arr[row].length ; col ++ )
            {
                int temp = arr [row][col];
                arr [row][col] = arr [arr.length - row - 1][col];
                arr [arr.length - row - 1][col] = temp;
            }
        }
    }

    public static void diagonalFlip (int arr [][])
    {
        for (int row = 0 ; row < arr.length ; row ++)
        {
            for (int col = row + 1 ; col < arr[row].length ; col ++ )
            {
                int temp = arr [row][col];
                arr [row][col] = arr [col][row];
                arr [col][row] = temp;
            }
        }
    }

}

