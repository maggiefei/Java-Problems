import java.util.* ;
public class MaggieFei_St2
{
    public static Scanner kbd = new Scanner (System.in);

    public static void main (String [] args)
    {
        Calculator ();
    }

    public static void Calculator ()
    {
        boolean exit = false ;

        while (exit == false)
        {
            boolean correctInput = false;

            System.out.print ("Command     -> ");
            String string = kbd.next();

            if (string.equals ("exit"))
            {
                correctInput = true;
                exit = true ;
            }

            if (string.equals ("+"))
            {
                correctInput = true;
                sum ();
            }

            if (string.equals ("*"))
            {
                correctInput = true;
                multiplication ();
            }

            if (string.equals ("/"))
            {
                correctInput = true;
                System.out.println ("Result     -> " + divide (kbd.nextInt(), kbd.nextInt()));
            }

            if (string.equals ("%"))
            {
                correctInput = true;
                System.out.println ("Result     -> " + remainder (kbd.nextInt(), kbd.nextInt()));
            }

            if (string.equals ("fact"))
            {
                correctInput = true;
                factorial ();
            }

            if (string.equals ("fib"))
            {
                correctInput = true;
                Fibonacci();
            }

            if (string.equals ("gcf"))
            {
                correctInput = true;
                System.out.println ("Result     -> " + gcf (kbd.nextInt(), kbd.nextInt()));
            }

            if (string.equals ("pf"))
            {
                correctInput = true;
                System.out.println ("Result     -> " + primeFactor (kbd.nextInt()));
            }

            if (string.equals ("conv"))
            {
                correctInput = true;
                convertBase ();
            }

            if (correctInput == false)
            {
                String line = kbd.nextLine();
                System.out.println ("Incorrect Input");
            }
        }

    }

    public static void sum ()
    {
        int next = 1;
        int sum = 0;

        while (next != 0)
        {
            next = kbd.nextInt();
            sum = sum + next;
        }

        System.out.println ("Result     -> " + sum);
    }

    public static void multiplication ()
    {
        int curValue = kbd.nextInt();
        int nextValue = kbd.nextInt();
        int counter = 1;
        int multiply = 0;

        while (nextValue != 0)
        {
            while (counter <= nextValue)
            {
                multiply = multiply + curValue;
                counter ++ ;
            }

            curValue = multiply;
            nextValue = kbd.nextInt();
            counter = 2;
        }

        System.out.println ("Result     -> " + multiply);
    }

    public static int divide (int n, int m)
    {
        int remainder = n;
        int counter = 0;

        if (n == m)
        {
            return 1;
        }

        while (remainder >= m)
        {
            remainder = remainder - m;
            counter ++;
        }

        return counter;
    }

    public static int remainder (int n, int m)
    {
        int remainder = n;
        int counter = 0;

        if (n == m)
        {
            return 0;
        }
        if (n > m)
        {
            while (remainder >= m)
            {
                remainder = remainder - m;
                counter ++;
            }

            return remainder;
        }

        else
        {
            return n;
        }
    }

    public static void factorial ()
    {
        int n = kbd.nextInt();
        int next = n - 1;
        int counter = 1;
        int multiply = 0;

        if (n == 1 || n == 0)
        {
            System.out.println ("Result     -> " + n);
        }

        else
        {
            while (next != 0)
            {
                while (counter <= next)
                {
                    multiply = multiply + n;
                    counter ++ ;
                }

                n = multiply;
                next --;
                counter = 2;
            }

            System.out.println ("Result     -> " + multiply);
        }
    }
    public static void Fibonacci ()
    {
        int n = kbd.nextInt();
        int prevValue = kbd.nextInt();
        int curValue = kbd.nextInt();
        int nextValue = 0;

        for (int counter = 3; counter <= n; counter ++)
        {
            nextValue = curValue + prevValue;
            prevValue = curValue;
            curValue = nextValue;
        }

        System.out.println ("Result     -> " + nextValue);
    }

    public static int gcf( int a, int b )
    {
        if (b > a)
        {
            int temp = a;
            a = b;
            b = temp;
        }

        int remainder = a;

        while (remainder >= b)
        {
            remainder = remainder - b;
        }

        if (remainder == 0)
        {
            return b;
        }

        else
        {
            return gcf (b, remainder);
        }
    }

    public static boolean isPrime (int x)
    {
        int root = 0;
        int counter = 2;
        int remainder = 0;
        int half = x;
        int halfCounter = 0;
        boolean prime = true;

        while (half >= 2)
        {
            half = half - 2;
            halfCounter ++;
        }

        while ((halfCounter > counter) && prime == true)
        {
            remainder = remainder (x, counter);
            counter ++;

            if (remainder == 0)
            {
                prime = false;
            }

        }

        return prime ;
    }

    public static String primeFactor (int x)
    {
        String primeFactors = new String ();

        for (int i = 2; i <= x; i++)
        {
            if (isPrime(i) == true && remainder (x, i) == 0)
            {
                while (remainder (x, i) == 0)
                {
                    primeFactors = primeFactors + i + " * ";
                    x = divide (x, i);
                }
            }
        }

        primeFactors = primeFactors.substring (0, primeFactors.length()- 2);
        return primeFactors;
    }

    public static void convertBase ()
    {
        int n = kbd.nextInt();
        int b = kbd.nextInt();

        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String conv = "" ;

        while (n != 0)
        {
            int index = remainder (n, b);
            conv = digits.charAt (index) + conv;
            n = divide (n, b);
        }

        System.out.println ("Result     -> " + conv);

    }
}
