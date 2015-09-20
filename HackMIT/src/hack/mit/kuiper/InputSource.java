package hack.mit.kuiper;
import edu.ufl.digitalworlds.j4k.J4KSDK;

public class InputSource extends J4KSDK {

	/**
	 * InputSource are video devices that collect 2 images
	 * One RGB "color" frame and one Depth image
	 */
	
	// Need a client to send data to
	private HackMITClient client;
	
	// Physical position of InputSource relative to center of room
	private XYZPoint displacementVector;
	
	/**
	 * Default constructor
	 * @param displacementVector - physical position of InputSource relative to center of room
	 * @param client - Device to communicate data to
	 */
	public InputSource(XYZPoint displacementVector, HackMITClient client) {
		this.displacementVector = displacementVector;
		this.client = client;
	}
	
	/**
	 * Constructor if no location is given
	 * @param client - Device to communicate data to
	 */
	public InputSource(HackMITClient client) {
		this(new XYZPoint(), client);
	}
	
	@Override
	public void onColorFrameEvent(byte[] color_frame) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDepthFrameEvent(short[] depth_frame, byte[] player_index, 
			float[] xyz, float[] uv) {
		/**
		 * Run this when given new depth image
		 */
		//System.out.println(this.getXYZ()[0]);
		/*System.out.println("UV: " + this.getUV().length);
		System.out.println("Depth: " + this.getDepthFrame().length);
		System.out.println("RGB: " + this.getColorFrame().length);
		System.out.println("XYZ: " + this.getXYZ().length);*/
		
		// Initialize array of all points
		XYZRGBAPoint points[] = new XYZRGBAPoint[this.getDepthFrame().length];
		int pointsNum = 0;
		for (int i = 0; i < this.getDepthFrame().length; i++)
		{
			if (player_index[i] == -1 || this.getDepthFrame()[i] == 0 || !Float.isFinite(this.getXYZ()[i]) || !Float.isFinite(this.getUV()[i]))
				continue;
			XYZRGBAPoint point = new XYZRGBAPoint(this.getXYZ()[i], this.getXYZ()[i + 1],
					this.getXYZ()[i + 2], this.getColorFrame()[i], this.getColorFrame()[i + 1],
					this.getColorFrame()[i + 2], this.getColorFrame()[i + 3]);
			point.x += displacementVector.x;
			point.y += displacementVector.y;
			point.z += displacementVector.z;
			points[pointsNum++] = point;
			/*System.out.println("Player Index: " + player_index[i]);
			System.out.println("Depth: " + this.getDepthFrame()[i]);
			System.out.println("UV: (" + this.getUV()[i] + ", " + this.getUV()[i+1] + ")");
			System.out.println("RGB: (" + this.getColorFrame()[i] + ", " + this.getColorFrame()[i+1]
					+ ", " + this.getColorFrame()[i+2] + ")");
			System.out.println("XYZ: (" + this.getXYZ()[i] + ", " + this.getXYZ()[i+1]
					+ ", " + this.getXYZ()[i+2] + ")");*/
		}
		XYZRGBAFrame frame = new XYZRGBAFrame(pointsNum);
		System.arraycopy(points, 0, frame.points, 0, pointsNum);
		this.client.publishData(frame);
	}

	@Override
	public void onSkeletonFrameEvent(boolean[] arg0, float[] arg1, float[] arg2, byte[] arg3) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
	}

}
