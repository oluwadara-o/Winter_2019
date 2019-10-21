package solar_system_test;

import java.awt.geom.Ellipse2D;
/**
 * A class that contains all the information about a planetary body
 */
public class PlanetaryBody {
	private static int width, height; //width and height of panel
	
//	private double distanceFromCentre;
	double speed;
	private double mass; //in earth masses
	private double vx, vy; //velocity in x and y directions
	private Ellipse2D shape;
	private double radius; //radius of the body
	//PlanetaryBody orbitingBody;
	
	
	
	
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the velocity in the x direction
	 */
	public double getVx() {
		return vx;
	}

	/**
	 * @return the velocity in the y direction
	 */
	public double getVy() {
		return vy;
	}

	/**
	 * @return the shape
	 */
	Ellipse2D getShape() {
		return shape;
	}
	/**
	 * Set panel width and height
	 * @param Panelwidth 
	 * @param Panelheight
	 */
	public static void setFrameDimensions(int Panelwidth, int Panelheight) {
		width = Panelwidth;
		height = Panelheight;
	}
	
	/**
	 * 
	 * @return the mass of the planet
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * A constructor that sets the intial parameters for any planetary body
	 * with an intial motion
	 * @param radius the size of the planet
	 * @param dFC the (initial vertical) distance of the planet from the centre of the frame
	 * @param mass the mass of the planet
	 * @param vx the initial velocity in the x direction
	 */
	public PlanetaryBody(double radius, double dFC, double mass, double vx) {
		super();
		this.radius = radius;
//		this.distanceFromCentre = dFC;
		this.mass = mass;
		this.vx = vx;
		this.vy=0;
		shape = new Ellipse2D.Double(width/2 - radius, height/2 -dFC - radius, 
				2*radius, 2*radius);
	}
	
	
	/**
	 * A constructor for a planetary body initally at the centre with no motion
	 * @param radius
	 * @param mass
	 */
	public PlanetaryBody(double radius, double mass) {
		super();
		this.radius = radius;
		this.mass = mass;
		shape = new Ellipse2D.Double(width/2 - radius, height/2 - radius, 2*radius, 2*radius);
	}
	
	/**
	 * Updates the position and speed of the planetary motion object
	 * @param newx the new x co-ordinate of the particle
	 * @param newy the new y co-ordinate of the particle
	 * @param newvx the new velocity in the x directions
	 * @param newvy the new velocity in the y directions
	 */
	public void updateMotion(double newx, double newy, double newvx, double newvy) {
		shape = new Ellipse2D.Double();
		shape.setFrameFromCenter(newx, newy, newx+radius, newy+radius);
		this.vx = newvx;
		this.vy = newvy;
	}
	
	/**
	 * Updates the position of the particle
	 * @param newx the new x co-ordinate of the particle
	 * @param newy the new y co-ordinate of the particle
	 */
	public void updatePosition(double newx, double newy) {
		shape = new Ellipse2D.Double();
		shape.setFrameFromCenter(newx, newy, newx+radius, newy+radius);
	}
	
	
	
//	public void setVelocity(double vx, double vy) {
//		this.vx = vx;
//		this.vy = vy;
//	}
	
//	public void setOrbittingBody(PlanetaryBody orbittingBody) {
//		this.orbitingBody = orbittingBody;
//	}
//	
//	public PlanetaryBody getOrbittingBody() {
//		return orbitingBody;
//	}
	


}
