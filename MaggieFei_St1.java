import java.util.* ;
public class MaggieFei_St1
{
    public static Scanner kbd = new Scanner (System.in);

    public static void main (String [] args)
    {
        coinGame1 ();

    }

    public static void coinGame1 ()
    {
        Scanner kbd = new Scanner (System.in);
        boolean exit = false;

        System.out.print ("Enter the number of coins on the table -> ");
        int N = kbd.nextInt();

        System.out.print ("Enter the maximum number of coins that can be taken each turn -> ");
        int M = kbd.nextInt();

        int strategy = M + 1;
        int userTakes = 0;
        int compTakes = 0;
        int option = 0;
        boolean user = true;

        if (M > N)
        {
            System.out.println ("You can't take more coins than there are! Try again.");
            coinGame1 ();
        }

        if (N % strategy == 0)
        {
            System.out.println ("You go first!");
            user = true;
        }
        else
        {
            System.out.println ("The computer will go first.");
            user = false;
        }

        while (N > 0)
        {
            if (user == true)
            {
                if (N == 1)
                {
                    System.out.print("There is 1 coin.");
                }

                else
                {
                    System.out.print("There are " + N + " coins.");
                }
                System.out.print ("     User takes -> ");
                userTakes = kbd.nextInt();


                if (userTakes > M)
                {
                    System.out.println ("You can't take more than " + M + " coins! Try again.");
                    coinGame1 ();
                }

                N = N - userTakes;
                user = false;
            }

            if (user == false)
            {
                if (N == 1)
                {
                    System.out.print("There is 1 coin.");
                }

                else
                {
                    System.out.print("There are " + N + " coins.");
                }
                compTakes = N % strategy;
                System.out.print("     Program takes -> " + compTakes);
                System.out.print("\n") ;
                N = N - compTakes;
                user = true;

                if (N == 0)
                {
                    System.out.print ("There are " + N + " coins.");
                    System.out.println ("     Computer won!");
                }
            }
        }

        System.out.println ("Enter 1 to repeat, 0 to exit.");
        option = kbd.nextInt();


        if (option == 1)
        {
            coinGame1 ();
        }
        else if (option == 0)
        {
            exit = true;
        }


    }

}