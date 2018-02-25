
public class MaggieFei_236
{

    public static void main (String [] args)
    {

        // Declare variables
        final int EMPTY = 0 ;
        final int FRONT = 0 ;
        final int FORWARD = 0 ;
        final int LEFT = 3 ;
        final int RIGHT = 1 ;

        Robot robot = new Robot () ;

        // Make the robot face the obstacle
        while (robot.check( FRONT ) == EMPTY)
        {
            robot.turn (LEFT);
        }

        // Once the robot is facing the obstacle, turn right
        while (robot.check( FRONT ) != EMPTY)
        {
            robot.turn( RIGHT ) ;
        }

        // While there is an obstacle to the left of the robot, move forward along it
        while (robot.check( LEFT ) != EMPTY)
        {
            robot.move( FORWARD );

            // Once the robot reaches a corner, its left will be empty, so turn left around the corner
            if (robot.check( LEFT ) == EMPTY)
            {
                robot.turn( LEFT );
                robot.move( FORWARD );
            }

            // If the robot is surrounded by left and front, it is in a corner and must turn right
            if ((robot.check( LEFT ) != EMPTY) && (robot.check( FORWARD ) != EMPTY))
            {
                robot.turn( RIGHT );
            }

            // If the robot is in a tunnel situation (surrounded on all three sides)
            if ((robot.check( LEFT ) != EMPTY) && (robot.check( FORWARD ) != EMPTY) && (robot.check( RIGHT ) != EMPTY))
            {
                robot.turn( RIGHT );
            }

            // Once the robot has turned right once in a tunnel, it needs to turn right again to get out of the tunnel
            if ((robot.check( LEFT ) != EMPTY) && (robot.check( FORWARD ) != EMPTY))
            {
                robot.turn( RIGHT );
            }
        }

    }

}