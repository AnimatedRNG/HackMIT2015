package hack.mit.kuiper;

import java.io.IOException;

import com.esotericsoftware.kryonet.Server;

public class HackMITServer {

	public Server server;
	public static final int PORT = 54099;
	public boolean running;
	
	public HackMITServer() {
		this.server = new Server(Registrar.WRITE_BUFFER_SIZE, Registrar.OBJECT_BUFFER_SIZE);
	}
	
	public void start() throws IOException {
		Registrar.register(this.server.getKryo());
		this.server.bind(PORT, PORT);
		this.server.addListener(new FrameListener());
		this.server.start();
		this.running = true;
	}
	
	public void stop() {
		this.server.stop();
		this.running = false;
	}
}
