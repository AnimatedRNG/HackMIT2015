package hack.mit.kuiper;

public class XYZPoint {
	
	/**
	 * Essentially a 3d vector
	 */
	
	public float x, y, z;
	
	public XYZPoint(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// default is zero vector
	public XYZPoint() {
		this(0, 0, 0);
	}
}
