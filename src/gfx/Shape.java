package gfx;



/**
 * Welcome to the beginning of your very own graphics package!
 * This graphics package will be used in most of your assignments from 
 * now on.
 *
 * This should look A LOT like the code you've seen in lecture (HINT HINT).
 * At first glance this class looks really dense, but don't worry most of
 * the methods you have to fill in wont be very long.
 *
 * REMEMBER most of the code you will write here will be code you've seen 
 * before (WINK WINK).
 *
 * All of the accessor methods return a dummy value so that this file can 
 * be compiled from the start. All of the non-optional accessor methods
 * will need to be modified.
 *
 * Feel free to add other functionality, but keep in mind this is a shape 
 * and shouldn't have capabilities that are more specific to say 
 * bees or something.
 *
 * Some bells and whistles you might want to add:
 *  - set transparency (look at java.awt.Color in the docs)
 *  - anti aliasing (getting rid of jagged edges..)
 *  - changing border width
 *
 * @author drotherm
 */
public abstract class Shape {

	/** Used to store some geometric data for this shape. */
	private java.awt.geom.RectangularShape _shape;

	/** Reference to containing subclass of JPanel. */
	private javax.swing.JPanel _panel;

	/** Border and Fill Colors. */
	private java.awt.Color _borderColor, _fillColor;

	/** Rotation (must be in radians). */
	private double _rotationAngle, _rotationX, _rotationY;
	private int _cWidth, _cHeight, _borderWidth;

	/** Indicates whether or not the shape should wrap. */
	private boolean _wrapping;

	/** Whether or not the shape should paint itself. */
	private boolean _visibility;

	/** 
	 * Initialize all instance variables here.  You'll need to store the
	 * containing subclass of JPanel to deal with wrapping and some of the
	 * extra credit stuff.
	 */
	public Shape(javax.swing.JPanel container, java.awt.geom.RectangularShape s) {
	
	_shape = s;
	_panel = container;
	_cWidth = _panel.getWidth();
	_cHeight = _panel.getHeight();

	this.setBorderColor(java.awt.Color.BLACK);
	this.setFillColor(java.awt.Color.GREEN);
	this.setLocation(15, 15);
	this.setSize(200, 200);
	this.setBorderWidth(5);
	this.setVisible(true);
	this.setWrapping(false);
	this.setRotation(0);

	}

	/**
	 * Should return the x location of the top left corner of 
	 * shape's bounding box.
	 */
	public double getX() {
		return _shape.getX();
	}

	/** 
	 * Should return the y location of the top left corner of 
	 * shape's bounding box.
	 */
	public double getY() {
		return _shape.getY();
	}

	/** Should return height of shape's bounding box. */
	public double getHeight() {
		return _shape.getHeight();
	}

	/** Should return width of shape's bounding box. */
	public double getWidth() {
		return _shape.getWidth();
	}
	
	public double getCenterX() {
		return _shape.getX()+(_shape.getWidth()/2);
	}

	public double getCenterY() {
		return _shape.getY() +(_shape.getHeight()/2);
	}

	/** Should return the border color you are storing. */
	public java.awt.Color getBorderColor() {
		return _borderColor;
	}

	/** Should return the fill color you are storing. */
	public java.awt.Color getFillColor() {
		return _fillColor;
	}

	/** Should return the rotation you are storing. */
	public double getRotation() {
		return _rotationAngle*180/java.lang.Math.PI;
	}

	public double getRotationX() {
		return _rotationX;
	}

	public double getRotationY() {
		return _rotationY;
	}

	/** 
	 * Optional.  Should return the width of the brush stroke for 
	 * the outline of your shape.
	 */
	public int getBorderWidth() {
		return _borderWidth;
	}

	/** Should return whether or not the shape is wrapping. */
	public boolean getWrapping() {
		return _wrapping;
	}

	/** Should return whether or not the shape is visible. */
	public boolean getVisible() {
		return _visibility;
	}

	/** 
	 * Set the location of shape. Make sure to wrap if the wrap 
	 * boolean is true.
	 */
	public void setLocation(double x, double y) {
		if (this.getWrapping()) {
			_shape.setFrame(((x % _cWidth) + _cWidth) % _cWidth, ((y % _cHeight) + _cHeight) % _cHeight, _shape.getWidth(),
			_shape.getHeight());
			}

		else {
			_shape.setFrame(x, y, _shape.getWidth(), _shape.getHeight());
			}	

	}

	/** Set the size of shape. */
	public void setSize(double width, double height) {
		_shape.setFrame(_shape.getX(), _shape.getY(), width, height);
	}

	/** Set the border color. */
	public void setBorderColor(java.awt.Color c) {
		_borderColor = c;
	}

	/** Set the fill color. */
	public void setFillColor(java.awt.Color c) {
		_fillColor = c;
	}

	/** Set the color of the whole shape. */
	public void setColor(java.awt.Color c) {
		_borderColor = c;
		_fillColor = c;
	}

	/**
	 * Set the rotation of the shape. Refer to the lecture to see
	 * how this should be done
	 */
	public void setRotation(double degrees) {
		_rotationAngle = degrees*java.lang.Math.PI/180;
		this.setRotationPoint(_shape.getCenterX(), _shape.getCenterY());
	}

	public void setRotationPoint(double x, double y) {
		_rotationX = x;
		_rotationY = y;
	}

	/** Optional: set how thick the shapes outline will be. */
	public void setBorderWidth(int width) {
		_borderWidth = width;
	}

	/** Set whether or not the shape should wrap. */
	public void setWrapping(boolean wrap) {
		_wrapping = wrap;
	}

	public void setContainerW(int width){
		_cWidth = width;
	}

	public void setContainerH(int height){
		_cHeight = height;
	}

	/** Set whether or not the shape should paint itself. */
	public void setVisible(boolean visible) {
		_visibility = visible;
	}

	/**
	 * This method is best explained in pseudo code:
	 *	If shape is visible
	 *	    rotate graphics
	 *	    set the brush stroke (width) of the graphics (optional)
	 *	    set the color of the graphics to the fill color of the shape
	 *	    fill the shape
	 *	    set the color of the graphics to the border color of the shape
	 *	    draw the shape
	 *	    un-rotate the graphics
	 */
	public void paint(java.awt.Graphics2D brush) {

		if (this.getVisible()){
			brush.rotate(_rotationAngle, _rotationX, _rotationY);
			brush.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
			brush.setColor(_borderColor);
			brush.setStroke(new java.awt.BasicStroke(_borderWidth));
			brush.draw(_shape);
			brush.setColor(_fillColor);
			brush.fill(_shape);
			brush.rotate(-1*_rotationAngle, _rotationX, _rotationY);
		}
			
	}

	/** 
	 * Should return true if the point is within the shape.  
	 * There's a special case for when the shape is rotated which you will 
	 * hear about in lab.  This doesn't need to be done for Cartoon, 
	 * but it will be required for Swarm. 
	 * (I will do this during lab or ask a TA about it)
	 */
	public boolean contains(java.awt.Point p) {
		if (0 != _rotationAngle) {
			double x = _shape.getCenterX();
			double y = _shape.getCenterY();
			java.awt.geom.AffineTransform trans = java.awt.geom.AffineTransform.getRotateInstance(_rotationAngle, x, y);
			java.awt.Shape s = trans.createTransformedShape(_shape);
			return s.contains(p);

		}
		
		return _shape.contains(p); 
	}

	/**
	 * This should be called when the shape is clicked.
	 * You'll want to overwrite this in subclasses to do something useful.
	 * Should stay empty in this class 
	 */
	public void react() {}
	}


