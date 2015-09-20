package hack.mit.kuiper;

public class XYZRGBAPoint {
	public float x, y, z;
	public byte r, g, b, a;
	
	public XYZRGBAPoint() {
		this(0, 0, 0, (byte)0, (byte)0, (byte)0, (byte)0);
	}
	
	public XYZRGBAPoint(float x, float y, float z, byte r, byte g, byte b, byte a) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
}
