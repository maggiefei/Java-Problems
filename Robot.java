import java.awt.* ;
import java.awt.event.* ;
import java.awt.image.* ;


public class Robot extends Panel implements MouseListener, ActionListener, WindowListener
{

    private final 	int 	PAUSE			= 200;

    private final 	int 	CELL_SIZE		= 15;

    private final	int		CELL_WALL		=  -2 ;

    private final	int		CELL_OBSTACLE	=  -1 ;

    private final	int		CELL_EMPTY		=  0 ;

    private final	int		CELL_RED		=  1 ;

    private final	int		CELL_BLUE		=  2 ;

    private final	int		CELL_GREEN 		=  3 ;

    private final 	int		SET_OBSTACLES	= 0 ;

    private final 	int		SET_ROBOT		= 1 ;

    private final	int		ON_THE_RUN		= 2 ;

    private final	int		FATAL_ERROR		= 3 ;

    private int		stage	= SET_OBSTACLES ;

    private String[] message = new String[] { 	"Add Obstacles and Press Here to Set the Robot",
            "Set the Robot and Press Here to Start",
            "The Robot is Following Instructions",
            "Fatal Error: The Robot Has Been Broken Down !"
    } ;


    // directions relative to the robot
    private final 	int 	DIR_FRONT = 0 ;

    private final 	int 	DIR_RIGHT = 1 ;

    private final 	int 	DIR_BACK  = 2 ;

    private final 	int 	DIR_LEFT  = 3 ;


    // directions relative to the position of the robot on the field
    private final	int		DIR_NONE		= -1 ;

    private final	int		DIR_NORTH		= 0 ;

    private final	int		DIR_WEST		= 1 ;

    private final 	int 	DIR_SOUTH		= 2 ;

    private final	int		DIR_EAST		= 3 ;

    private int		direction = DIR_NONE ;


    private BufferedImage  	osi = null ;		// memory that holds the off-screen image

    private Graphics  		osg = null;

    private Frame 	frame 	= null ;

    private Panel 	panel	= null ;

    private volatile boolean flag 	= true ;

    private Button 	button 	= null ;

    private Label  	label = null ;

    private Dimension d = null ;

    private Point  	robot   = null ;


    private volatile int[][] grid	= null ;


    private Point   corner  = new Point ( 0, 0 ) ;



    public Robot ()
    {

        setBackground( Color.LIGHT_GRAY ) ;

        addMouseListener( this ) ;


        button = new Button( message[stage] ) ;

        button.addActionListener( this ) ;


        panel = new Panel() ;

        panel.setBackground( Color.LIGHT_GRAY ) ;

        panel.setLayout( new BorderLayout() ) ;

        panel.add( button, BorderLayout.NORTH ) ;

        panel.add( this, BorderLayout.CENTER ) ;



        frame = new Frame() ;

        frame.addWindowListener (this ) ;

        frame.add( panel ) ;

        frame.setTitle ( "Robot in the Grid" ) ;

        frame.setSize( 800, 600) ;

        frame.setLocation( 400, 0) ;

        frame.setResizable( false ) ;

        frame.setVisible(true) ;

        while ( flag ) ;

    }


    // public method the control the motion of the robot
    public void paint( int color )
    {
        if ( stage == FATAL_ERROR || robot == null )
        {
            return ;
        }

        if( color >= CELL_EMPTY && color <= CELL_GREEN )
            grid[robot.y][robot.x] = color ;
        else
            System.out.println("An unknown command: color( " + color + " ) " ) ;

        repaint() ;

    }

    public void turn( int dir )
    {
        if ( stage == FATAL_ERROR || robot == null )
        {
            return ;
        }

        switch (dir )
        {
            case DIR_RIGHT	:	direction = ( direction + 3 ) % 4 ;
                break ;

            case DIR_LEFT 	:	direction = ( direction + 1 ) % 4 ;
                break ;

            default			: 	System.out.println("An unknown command: turn( " + dir + " ) " ) ;

        }

        repaint() ;

    }


    public void move( int dir )
    {

        if ( stage == FATAL_ERROR || robot == null )
        {
            return ;
        }

        try
        {
            Thread.currentThread().sleep( PAUSE ) ;
        }
        catch( Exception e ) {} ;


        switch( dir )
        {
            case 	DIR_FRONT: 	{
                if	( check( DIR_FRONT ) == CELL_WALL || check( DIR_FRONT ) == CELL_OBSTACLE )
                {
                    stage = FATAL_ERROR ;

                    System.out.println ( message[stage] ) ;
                }

                switch ( direction )
                {
                    case DIR_NORTH	: 	robot.y-- ;	break ;

                    case DIR_SOUTH	: 	robot.y++ ;	break ;

                    case DIR_EAST	: 	robot.x++ ;	break ;

                    case DIR_WEST	: 	robot.x-- ;	break ;
                }
            }
            break ;

            case 	DIR_BACK:	{
                if	( check( DIR_BACK ) == CELL_WALL || check( DIR_BACK ) == CELL_OBSTACLE )
                {
                    stage = FATAL_ERROR ;

                    System.out.println ( message[stage] ) ;
                }

                switch ( direction )
                {
                    case DIR_NORTH	: 	robot.y++ ;	break ;

                    case DIR_SOUTH	: 	robot.y-- ;	break ;

                    case DIR_EAST	: 	robot.x-- ;	break ;

                    case DIR_WEST	: 	robot.x++ ;	break ;
                }
            }
            break ;

            default			:	System.out.println("An unknown command: move( " + dir + " ) " ) ;
        }

        repaint() ;

    }


    public int check( int dir )
    {
        int rc = CELL_EMPTY ; // next cell is free

        if ( stage == FATAL_ERROR || robot == null )
        {
            return FATAL_ERROR ;
        }

        Point p = new Point ( robot.x, robot.y )  ;

        switch ( dir )
        {
            case	DIR_FRONT:	switch ( direction )
            {
                case DIR_NORTH	: 	p.y-- ;	break ;
                case DIR_SOUTH	: 	p.y++ ;	break ;
                case DIR_EAST	: 	p.x++ ;	break ;
                case DIR_WEST	: 	p.x-- ;	break ;
            }
                break ;

            case 	DIR_LEFT:	switch ( direction )
            {
                case DIR_NORTH	: 	p.x-- ;	break ;
                case DIR_SOUTH	: 	p.x++ ;	break ;
                case DIR_EAST	: 	p.y-- ;	break ;
                case DIR_WEST	: 	p.y++ ;	break ;
            }
                break ;

            case 	DIR_RIGHT:	switch ( direction )
            {
                case DIR_NORTH	: 	p.x++ ;	break ;
                case DIR_SOUTH	: 	p.x-- ;	break ;
                case DIR_EAST	: 	p.y++ ;	break ;
                case DIR_WEST	: 	p.y-- ;	break ;
            }
                break ;

            case 	DIR_BACK:	switch ( direction )
            {
                case DIR_NORTH	: 	p.y++ ;	break ;
                case DIR_SOUTH	: 	p.y-- ;	break ;
                case DIR_EAST	: 	p.x-- ;	break ;
                case DIR_WEST	: 	p.x++ ;	break ;
            }
                break ;

        }

        if	( 	p.x < 0 || p.x >= grid[0].length || p.y < 0 || p.y >= grid.length )
        {
            rc = CELL_WALL ; // wall
        }
        else
        {
            rc = grid[p.y][p.x] ;
        }

        return rc ;
    }


    // public methods to control the process
    public void paint ( Graphics g )
    {
        d	= getSize() ;

        grid 	= new int[ d.height / CELL_SIZE ][ d.width / CELL_SIZE ] ;

        corner.x = ( d.width - grid[0].length * CELL_SIZE ) / 2 ;

        corner.y = ( d.height - grid.length * CELL_SIZE ) / 2 ;

        osi = new BufferedImage( d.width, d.height, BufferedImage.TYPE_INT_RGB ) ;

        osg = osi.getGraphics() ;

        update( g ) ;

    }

    public void update( Graphics g)
    {

        // set the background color of the off-screen image
        osg.setColor( 	Color.LIGHT_GRAY ) ;

        osg.fillRect(	0, 0, d.width, d.height ) ;

        // draw the grid lines
        osg.setColor( Color.DARK_GRAY ) ;

        for( int row = 0 ; row <= grid.length; row++ )
        {
            osg.drawLine( corner.x, corner.y + row*CELL_SIZE, corner.x + grid[0].length * CELL_SIZE, corner.y + row*CELL_SIZE) ;
        }

        for( int col = 0 ; col <= grid[0].length; col++ )
        {
            osg.drawLine( corner.x + col*CELL_SIZE, corner.y, corner.x + col*CELL_SIZE, corner.y + grid.length * CELL_SIZE ) ;
        }

        // draw the walls
        osg.setColor( Color.RED ) ;

        osg.drawRect ( corner.x , corner.y, grid[0].length * CELL_SIZE, grid.length * CELL_SIZE) ;


        // draw the cells
        for( int row = 0 ; row < grid.length; row++ )
        {
            for( int col = 0 ; col < grid[0].length; col++ )
            {
                switch ( grid[row][col] )
                {
                    case CELL_OBSTACLE 	: 	osg.setColor( Color.BLACK ) ;
                        osg.fillRect( corner.x + col*CELL_SIZE, corner.y + row*CELL_SIZE, CELL_SIZE, CELL_SIZE ) ;
                        break ;

                    case CELL_RED		:	osg.setColor( Color.RED ) ;
                        osg.fillOval( corner.x + col*CELL_SIZE + CELL_SIZE/4 , corner.y + row*CELL_SIZE + CELL_SIZE/4 , CELL_SIZE / 2, CELL_SIZE / 2 ) ;
                        break ;

                    case CELL_BLUE		:	osg.setColor( Color.BLUE ) ;
                        osg.fillOval( corner.x + col*CELL_SIZE + CELL_SIZE/4 , corner.y + row*CELL_SIZE + CELL_SIZE/4 , CELL_SIZE / 2, CELL_SIZE / 2 ) ;
                        break ;

                    case CELL_GREEN		:	osg.setColor( Color.GREEN ) ;
                        osg.fillOval( corner.x + col*CELL_SIZE + CELL_SIZE/4 , corner.y + row*CELL_SIZE + CELL_SIZE/4 , CELL_SIZE / 2, CELL_SIZE / 2 ) ;
                        break ;


                    case CELL_EMPTY		:	break ;

                    default				:	break ;

                }
            }
        }

        if ( robot != null )
        {
            // calculate the robot's position
            Point center = new Point ( corner.x + robot.x*CELL_SIZE + CELL_SIZE / 2, corner.y + robot.y*CELL_SIZE + CELL_SIZE / 2 ) ;

            int size = 3 * CELL_SIZE / 4 ;

            osg.setColor( Color.GREEN ) ;

            // draw the robot's body
            osg.setColor( Color.GREEN ) ;
            osg.fillRect(center.x - size / 2 , center.y - size / 2 , size, size ) ;

            osg.setColor ( Color.BLACK ) ;
            osg.drawRect(center.x - size / 2 , center.y - size / 2 , size, size ) ;


            // calculate the position of the robot's radar
            switch( direction )
            {
                case DIR_SOUTH: 	center.y += size/2 ;	break ;

                case DIR_NORTH: 	center.y -= size/2 ;	break ;

                case DIR_EAST:	 	center.x += size/2 ;	break ;

                case DIR_WEST:	 	center.x -= size/2 ;	break ;
            }

            size = 3 * size / 4 ;

            // draw the robot's radar
            osg.setColor( Color.RED ) ;

            osg.fillOval( center.x - size / 2  , center.y - size / 2 , size , size ) ;

            osg.setColor ( Color.BLACK ) ;

            osg.drawOval( center.x - size / 2  , center.y - size / 2 , size , size ) ;
        }

        g.drawImage( osi, 0, 0, this ) ;
    } // end of the method paint()


    public void mouseExited( MouseEvent me )
    {}

    public void mouseEntered( MouseEvent me )
    {}

    public void mousePressed( MouseEvent me )
    {
        if ( stage != FATAL_ERROR )
        {
            Point p = me.getPoint() ;

            if ( 	p.x > corner.x && p.x < corner.x + grid[0].length*CELL_SIZE
                    &&	p.y > corner.y && p.y < corner.y + grid.length*CELL_SIZE )
            {
                if ( stage == SET_ROBOT )
                {
                    robot 		= new Point ((p.x - corner.x ) / CELL_SIZE , (p.y - corner.y)/CELL_SIZE ) ;

                    direction 	= (int) ( Math.random() * 4 ) ;
                }

                if ( stage == SET_OBSTACLES )
                {
                    grid[ (p.y - corner.y)/CELL_SIZE ][ (p.x - corner.x ) / CELL_SIZE ] = CELL_OBSTACLE ;
                }

                repaint() ;

            }
        }

    }

    public void mouseClicked( MouseEvent me )
    {}

    public void mouseReleased( MouseEvent me )
    {}


    public void actionPerformed( ActionEvent ae )
    {

        switch ( stage )
        {

            case SET_OBSTACLES	:		stage = SET_ROBOT ;
                button.setLabel( message[stage] ) ;
                break ;


            case SET_ROBOT		:		if ( robot != null )
            {
                flag = false ;
                stage = ON_THE_RUN ;
                button.setLabel( message[stage] ) ;
            }
                break ;

            case ON_THE_RUN		:


            default 			:		break ;

        }

    } ;


    public void windowDeactivated( WindowEvent we )
    {}

    public void windowActivated( WindowEvent we )
    {}

    public void windowDeiconified( WindowEvent we )
    {}

    public void windowIconified( WindowEvent we )
    {}

    public void windowClosed( WindowEvent we )
    {}

    public void windowClosing( WindowEvent we )
    {
        flag = false ;

        frame.dispose() ;

        System.exit(0) ;
    }

    public void windowOpened( WindowEvent we )
    {}

} //end of the class CircleInCenter
