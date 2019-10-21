package solar_system_test;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
/**
 * The panel that shows all the displayed planets and sun
 * @author Dara Oyedepo
 *
 */
public class SkyPanel extends JPanel implements ActionListener{
	private Timer animationTimer; // timer controlling frame rate
	//declare all planetary bodys needed
	public static PlanetaryBody sun;
	public static PlanetaryBody earth;
	public static PlanetaryBody moon;
	public static PlanetaryBody mars;
	public static PlanetaryBody mercury;
	public static PlanetaryBody venus;
	
	public static double G = 0.001; //Gravitational Constant
	static public double time; //arbitary units measures overall time ellapsed
	private static double timeStep = 1; //arbitary units determines how much time passes
							//between each repaint
	static boolean debug = false;
	//
	
	
	
	
	/** Constructor just sets size of panel, initializes parameters for planetary bodys
	 * and starts the animation timer. */
	public SkyPanel(int width, int height) {
		setPreferredSize(new Dimension(width,height));


		//Set planetary body properties
		PlanetaryBody.setFrameDimensions(width, height);
		sun = new PlanetaryBody(50,330000);
		moon =  new PlanetaryBody(5, 0.0002);
		mercury = new PlanetaryBody(10, -100, 0.055,1.816);
		//sets different parameters based on screen size
		//		if (SolarSystem.bigscreen == true) {
		venus = new PlanetaryBody(15, -175, 0.815,1.373);
		earth = new PlanetaryBody(15, -250, 1,1.149);
		mars = new PlanetaryBody(12, -400, 0.151,0.908);
		//		}else {
		//			venus = new PlanetaryBody(10, -175, 0.815,1.30);
		//			earth = new PlanetaryBody(10, -225, 1,1.140);
		//			mars = new PlanetaryBody(10, -300, 0.151,0.999);
		//		}

		animationTimer = new Timer(10,this);
		animationTimer.start();
	}

	/**
	 * Must override this method, which is called
	 * LinesPanel(1,3)
	 * by the Swing framework whenever the display
	 * needs updating.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // call superclass method first
		Graphics2D g2d = (Graphics2D)g;
		int width = getWidth();
		int height = getHeight();
		
		//draw horizontal and vertical line that pass through the midile
		if (debug == true) {
			System.out.println(height);
			g.drawLine(0,height/2,  width, height/2);
			g.drawLine( width/2, 0,  width/2, height);
		}
		
		//update motion as needed
		earth = physicalOrbit(earth,sun);
		mars = physicalOrbit(mars,sun);
		mercury = physicalOrbit(mercury,sun);
		venus = physicalOrbit(venus,sun);
		moon = unphysicalOrbit(moon,earth,30,110);

	    //draw sun
	    g.setColor(Color.YELLOW);
	    g2d.fill(sun.getShape());
	    //draw mercury
	    g.setColor(Color.orange);
	    g2d.fill(mercury.getShape());
	    //draw venus
	    g.setColor(Color.magenta);
	    g2d.fill(venus.getShape());
	    //draw earth
	    g.setColor(Color.blue);
	    g2d.fill(earth.getShape());
	    //draw mars
	    g.setColor(Color.red);
	    g2d.fill(mars.getShape());
	    //draw moon
	    g.setColor(Color.gray);
	    g2d.fill(moon.getShape());
	    
	    //Only displays time on larger screens
	    if (SolarSystem.bigscreen==true) {
	    	//Display and update the time
	    	Font f = new Font("TimesRoman",Font.BOLD,20);
	    	g.setFont(f);
	    	double timeInYears = time/1370; //change time from arbitary unit to years
	    	String timeString = String.valueOf(timeInYears);
	    	g.drawString("Earth year:" +timeString,width-150,20);//display time
	    }
	      
	}

	/**
	 * This calcultes the motion of a planetaryBody under the gravitational pull
	 * @param planet the planet that moves
	 * @param focus the body exerts a gravitional force on the planet 
	 */
	private static PlanetaryBody physicalOrbit(PlanetaryBody planet, PlanetaryBody focus) {
		
		//find current x and y positions
		double xpos= planet.getShape().getCenterX();
		double ypos = planet.getShape().getCenterY();
		
		//find current z and y velocities
		double vx = planet.getVx();
		double vy = planet.getVy();
		
		//find the x and y position of the focus body
		double xfocusPos = focus.getShape().getCenterX();
		double yfocusPos = focus.getShape().getCenterY();
		
		//Get distance between the planet and thefouce
		double planetaryd = Math.pow(Math.pow((xpos-xfocusPos), 2) +  Math.pow((ypos-yfocusPos), 2), 0.5);
		
		//Calculate forces in the x and y direction on the planet due 
		//the newtonin gravitional pull caused by the focus 
		double Fx = -G*focus.getMass()*planet.getMass()*(xpos- xfocusPos)/Math.pow(planetaryd, 3);
		double Fy = -G*focus.getMass()*planet.getMass()*(ypos - yfocusPos)/Math.pow(planetaryd, 3);
		
		if (debug ==true) {
			System.out.println(planet.getShape().getCenterX()+"vs"+planet.getShape().getX());
			System.out.println("distance "+planetaryd);
			double speed = Math.pow(vx*vx +vy*vy,0.5);
			System.out.println("speed is "+ speed);
			double period = (2*Math.PI*planetaryd)/speed;
			System.out.println("Orbit should be: " + period);
			System.out.println("force x is "+Fx+"force y is "+Fy);
			System.out.println("x velocity is "+ vx +"y volocity is" + vy );
			System.out.println("speed should be "+ Math.pow(G*focus.getMass()/planetaryd, 0.5));
			System.out.println("Time is " + time);
			System.out.println();
		}
		
		//calculate the updated velocity of the planets
		double vx2 = vx + Fx*timeStep/planet.getMass();
		double vy2 = vy + Fy*timeStep/planet.getMass();
		
		//calculate the new position of the planet
		double newxpos = xpos + vx2*timeStep;
		double newypos = ypos + vy2*timeStep;
		
		//update the motion of the planet
		planet.updateMotion(newxpos, newypos, vx2, vy2);
		return planet;
	  }

	/**
	 * This method makes a PlaneteryBody orbit in a circle around a specified focus PlanetaryBody
	 * @param body the planetaryBody that moves
	 * @param focus
	 * @param distance the distance between the body and the focus
	 * @param period the time taken to complete one orbit
	 * @return
	 */
	private static PlanetaryBody unphysicalOrbit(PlanetaryBody body, PlanetaryBody focus, double distance, double period) {
		
		//find the x and y position of the focus body
		double xfocusPos = focus.getShape().getCenterX();
		double yfocusPos = focus.getShape().getCenterY();
		
		
		double planetaryd = distance; //set the distance between the body and the focus
		double frequency = 1/period; //set the frequency of rotation around body

		//find the new position for the body
		double newxpos = xfocusPos- planetaryd*Math.cos(2*Math.PI*frequency*time);
		double newypos = yfocusPos - planetaryd*Math.sin(2*Math.PI*frequency*time);
		
		//print-out useful parameter
		if (debug == true) {
			System.out.println("distance is" +planetaryd);
			System.out.println("Time is " + time);
		}
		
		//update and return the moved body
		body.updatePosition(newxpos, newypos);
		return body;
	  }

	/** Updates and repaints the panel according to the timer*/
	public void actionPerformed(ActionEvent event) {
		//step++;
		time+=timeStep;
	    repaint();
	  }
	/**
	 * Resizes all planets and the moon for a larger screen size
	 */
	public void biganimation() {
		SolarSystem.bigscreen = true;
		int width = getWidth();
		int height = getHeight();
		time =0;
		
		//Set planetary body properties
		PlanetaryBody.setFrameDimensions(width, height);
		sun = new PlanetaryBody(50,330000);
		moon =  new PlanetaryBody(5, 0.0002);
		mercury = new PlanetaryBody(10, -100, 0.055,1.816);
		venus = new PlanetaryBody(15, -175, 0.815,1.373);
		earth = new PlanetaryBody(15, -250, 1,1.149);
		mars = new PlanetaryBody(12, -400, 0.151,0.908);
		animationTimer.start();
		
	}
	/**
	 * Resizes all planets and removes moon for smaller screen
	 */
	public void smallanimation() {
		SolarSystem.bigscreen = false;
		int width = getWidth();
		int height = getHeight();
		time =0;
		//Set planetary body properties with smaller sizes and distance
		PlanetaryBody.setFrameDimensions(width, height);
		sun = new PlanetaryBody(15,330000);
		moon =  new PlanetaryBody(0, 0.0002); //remove moon
		mercury = new PlanetaryBody(5, -50, 0.055,2);
		venus = new PlanetaryBody(5, -87.5, 0.815,1.30);
		earth = new PlanetaryBody(5, -125, 1,1.140);
		mars = new PlanetaryBody(5, -200, 0.151,0.999);
		animationTimer.start();
		
	}

}