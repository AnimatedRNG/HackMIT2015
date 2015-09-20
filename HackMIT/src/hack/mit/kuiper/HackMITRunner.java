package hack.mit.kuiper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ufl.digitalworlds.j4k.J4KSDK;

public class HackMITRunner {
	public static void main(String[] args) {
		HackMITClient client = new HackMITClient();
		List<InputSource> kinects = new ArrayList<InputSource>();
		InputSource kinect1 = new InputSource(new XYZPoint(0f, 0f, 0f), client);
		InputSource kinect2 = new InputSource(client);
		InputSource kinect3 = new InputSource(client);
		kinects.add(kinect1);
		//kinects.add(kinect2);
		//kinects.add(kinect3);
		HackMITServer server = new HackMITServer();
		try {
			server.start();
			Thread.sleep(500);
			client.connect();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		//kinect.computeUV(true);
		for (InputSource kinect : kinects) 
		{
			kinect.start(J4KSDK.COLOR|J4KSDK.XYZ|J4KSDK.PLAYER_INDEX|J4KSDK.DEPTH);
			kinect.showViewerDialog();
		}
		try {Thread.sleep(20000);} catch (InterruptedException e) {}
		for (InputSource kinect : kinects)
			kinect.stop();
		server.stop();
	}
}
