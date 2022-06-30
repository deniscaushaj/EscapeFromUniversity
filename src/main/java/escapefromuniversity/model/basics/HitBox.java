package escapefromuniversity.model.basics;

public interface HitBox {
	
	/**
	 * 
	 * @return the upper right corner of the invisible hit box surrounding the game object
	 */
	Point2D getTopLeftCorner();
	
	/**
	 * 
	 * @return the bottom left corner of the invisible hit box surrounding the game object 
	 */
	Point2D getBottomRightCorner();
	
	/**
	 * 
	 * @return the height of the invisible hit box surrounding the game object
	 */
	public double getHeight();
	
	/**
	 * 
	 * @return the width of the invisible hit box surrounding the game object
	 */
	public double getWidth();
	
	
	/**
	 * 
	 * @return true if two hit box are colliding
	 */
	public boolean isColliding(HitBox box);

}
