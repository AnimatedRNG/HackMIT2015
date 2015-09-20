package hack.mit.kuiper;

import com.esotericsoftware.kryo.Kryo;

public abstract class Registrar {

	public static final int WRITE_BUFFER_SIZE = 65536*2;
	public static final int OBJECT_BUFFER_SIZE = 65536*2;
	
	public static void register(Kryo kryo) {
		kryo.register(XYZRGBAPoint.class);
		kryo.register(hack.mit.kuiper.XYZRGBAPoint[].class);
		kryo.register(XYZRGBAFrame.class);
	}
}
