package gfx;

//this is simply creating a rectangle and passing it along with the container to the super class Shape.

public class Rectangle extends gfx.Shape {

	public Rectangle(javax.swing.JPanel container) {
		super(container, new java.awt.geom.Rectangle2D.Double());
	}

}