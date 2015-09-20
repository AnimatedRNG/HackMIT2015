package hack.mit.kuiper;

import java.util.Arrays;
import java.util.List;

public class XYZRGBAFrame {
	
	// length of grid and initial coordinates
	// TODO: FIX - arbitrary constants for now......
	public static final float CONS_GRID_LENGTH = 25,
			CONS_INIT_X = 0,
			CONS_INIT_Y = 0,
			CONS_INIT_Z = 0;
	
	// list of color&depth points
	public XYZRGBAPoint[] points;
	
	public XYZRGBAFrame() {
		// remove
		// no empty constructor
	}
	
	public XYZRGBAFrame(int pointNum) {
		points = new XYZRGBAPoint[pointNum];
	}
	
	public void downsample() {
		/* Implement this! */
		// Create a Voxel for this frame
		Voxel myVoxel = new Voxel(XYZRGBAFrame.CONS_INIT_X,
				XYZRGBAFrame.CONS_INIT_Y,
				XYZRGBAFrame.CONS_INIT_Z,
				XYZRGBAFrame.CONS_GRID_LENGTH,
				Arrays.asList(this.points));
		
		// Turn our one Voxel into a lot of more precise Voxels!
		List<Voxel> subVoxels = myVoxel.subdivide();
		
		// Turn those babyVoxels into points!
		// - Define a new array of points
		XYZRGBAPoint[] new_points = new XYZRGBAPoint[ subVoxels.size() ];
		
		// - Convert Voxel to XYZRGBAPoint
		for (int i=0; i<subVoxels.size(); i++) {
			new_points[i] = subVoxels.get(i).getCentroid();
		}
		
		// get rid of old numbers
		this.points = new_points;
	}
}
