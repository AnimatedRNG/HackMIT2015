package hack.mit.kuiper;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;

public class HackMITClient {

	public Client client;
	
	public HackMITClient() {
		/**
		 * Create a new network client and start it
		 */
		this.client = new Client(Registrar.WRITE_BUFFER_SIZE, Registrar.OBJECT_BUFFER_SIZE);
		Registrar.register(this.client.getKryo());
		this.client.start();
	}
	
	public void connect() throws IOException {
		this.client.connect(5000, "localhost", HackMITServer.PORT, HackMITServer.PORT);
	}
	
	public void publishData(XYZRGBAFrame frame) {
		System.out.println("Sending frame with " + frame.points.length + " points");
		this.client.sendUDP(frame);
	}
}
