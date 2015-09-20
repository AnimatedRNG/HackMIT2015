package hack.mit.kuiper;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class FrameListener extends Listener {
	
	@Override
	public void connected(Connection connection) {
		
	}
	
	@Override
	public void disconnected(Connection connection) {
		
	}
	
	@Override
	public void received (Connection connection, Object object) {
		if (object instanceof XYZRGBAFrame)
		{
			XYZRGBAFrame points = ((XYZRGBAFrame) object);
			System.out.println("Server has received frame with " + points.points.length + " points");
		}
	}
}
