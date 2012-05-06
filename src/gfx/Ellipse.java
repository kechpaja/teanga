package gfx;

//This class allows the instantiator to pass the new ellipse a reference of any JPanel and then a new Ellipse with the capabilites of Shape.gfx will be formed.

public class Ellipse extends Shape {

	public Ellipse(javax.swing.JPanel container) {
		super(container, new java.awt.geom.Ellipse2D.Double());
	}

}