package hack.mit.kuiper;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ReceiverListener extends Listener {
	@Override
	public void connected(Connection connection) {
		
	}
	
	@Override
	public void disconnected(Connection connection) {
		
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof XYZRGBAFrame)
		{
			// Here's the rendering stuffs
		}
	}
}
