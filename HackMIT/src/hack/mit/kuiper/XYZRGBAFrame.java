package hack.mit.kuiper;

public class XYZRGBAFrame {
	public XYZRGBAPoint[] points;
	
	public XYZRGBAFrame() {
	}
	
	public XYZRGBAFrame(int pointNum) {
		this.points = new XYZRGBAPoint[pointNum];
	}
	
	public void downsample() {
		/* Implement this! */
	}
}
