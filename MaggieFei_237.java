
public class MaggieFei_237
{
    public static void main (String [] args)
    {

        // Declare variables
        final int FORWARD = 0;
        final int BACKWARD = 2;
        final int EMPTY = 0;
        final int RED = 1;
        final int GREEN = 3;
        final int RIGHT = 1;
        final int LEFT = 3;
        final int FRONT = 0;
        final int BACK = 2;
        final int OBSTACLE = -1;

        Robot robot = new Robot();

        int terminus = 0;
        int perimetercount = 0;
        int stepcount = 0;
        int redreset=0;
        int redovercount=0;

        //  Make the robot move around the obstacle and return to its original cell
        // Determine the perimeter while moving the robot
        while (robot.check(FRONT)==OBSTACLE || robot.check(LEFT)!=OBSTACLE)
        {
            robot.turn(LEFT);
        }

        if (robot.check(RIGHT)==EMPTY && robot.check(BACK)==EMPTY)
        {
            robot.paint(RED);

            robot.move (FORWARD);
            if (robot.check(RIGHT)==OBSTACLE)
            {
                redovercount++;
            }
            robot.move (BACKWARD);
            robot.move (BACKWARD);
            if (robot.check (RIGHT)==OBSTACLE)
            {
                redovercount++;
            }
            robot.move (FORWARD);
            robot.move(FORWARD);
            stepcount++;
            perimetercount++;

        }

        if (robot.check(RIGHT)==OBSTACLE && robot.check (BACK)==EMPTY )
        {
            robot.paint(RED);
            robot.move(FORWARD);
            stepcount++;
            perimetercount +=2;
            redovercount++;
        }

        if (robot.check (RIGHT) == EMPTY && robot.check (BACK)==OBSTACLE)
        {
            robot.paint(RED);

            robot.move(FORWARD);

            if(robot.check(RIGHT)==OBSTACLE)
            {
                redovercount++;
            }
            robot.move (BACKWARD);

            robot.move(FORWARD);
            stepcount++;
            perimetercount +=2;


        }

        if (robot.check (BACK)==OBSTACLE && robot.check (RIGHT)==OBSTACLE)
        {
            robot.paint(RED);
            robot.move(FORWARD);
            stepcount++;
            perimetercount +=3;
        }

        //Turn the robot in the right direction

        while (terminus!=1)
        {
            while (robot.check(LEFT)== OBSTACLE && terminus!=1)
            {
                if (robot.check (LEFT) == OBSTACLE && robot.check(FORWARD)!=OBSTACLE)
                {
                    perimetercount++;
                }

                if (robot.check (LEFT) == OBSTACLE && robot.check(FORWARD)==OBSTACLE
                        && robot.check (RIGHT)!=OBSTACLE)
                {
                    robot.turn(RIGHT);
                    perimetercount +=2;

                }

                if (robot.check (LEFT) == OBSTACLE && robot.check(FORWARD)==OBSTACLE
                        && robot.check (RIGHT)==OBSTACLE)
                {
                    robot.turn(LEFT);
                    robot.turn (LEFT);
                    perimetercount +=3;
                }



                if (robot.check(FRONT)!=RED || redovercount!=0)
                {
                    robot.paint(GREEN);

                    if (redreset==1 )
                    {
                        robot.paint(RED);
                        robot.move(FORWARD);
                        redreset--;
                    }

                    if (robot.check(FRONT)==RED)
                    {
                        redovercount--;
                        redreset++;
                    }

                    robot.move(FORWARD);

                    stepcount++;
                }

                else
                {
                    terminus=1;
                }
            }

            if (robot.check(LEFT)!= OBSTACLE)
            {
                robot.turn(LEFT);

                if (robot.check(FRONT)==RED)
                {
                    redreset++;
                    terminus=1;
                }

                if (redreset==1)
                {
                    robot.paint(RED);
                    redreset--;
                }

                robot.move (FORWARD);
                stepcount++;
                robot.paint(EMPTY);


            }
        }

        // Print out the perimeter of the obstacle
        System.out.print ("The perimeter of the obstacle is " + perimetercount + ".\n");
    }
}

