package hack.mit.kuiper;

import java.util.ArrayList;
import java.util.List;

public class Voxel {
	
	/**
	 * Voxels represent the space of a cube and the XZYRGBA points within it
	 */
	
	// VOXEL CONSTANTS
	// TODO: FIX - arbitrary constants for now.....
	public static final float MIN_COUNT = 3,
			MAX_COUNT = 8,
			MIN_LENGTH = 5;
	
	// All voxels are cubes, so only four dimensions are needed to define it in 3-space
	public float x, y, z, length;
	
	// List of detected points within cube
	public List<XYZRGBAPoint> pointList;
	
	/**
	 * Default constructor
	 * @param x - Upper-left x-coordinate of voxel
	 * @param y - Upper-left y-coordinate of voxel
	 * @param z - Upper-left z-coordinate of voxel
	 * @param l - Length dimension of the voxel
	 * @param plist - Array of all points within voxel boundry 
	 */
	public Voxel(float x, float y, float z, float l, List<XYZRGBAPoint> plist) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.length = l;
		this.pointList = plist;
	}
	
	public Voxel(float x, float y, float z, float l) {
		this(x, y, z, l, new ArrayList<XYZRGBAPoint>());
	}
	
	public List<Voxel> subdivide() {
		// recursion is seriously the answer to most of my hard problems
		ArrayList<Voxel> subVoxels = new ArrayList<Voxel>();
		
		// if voxel is too small, don't bother dividing anymore. Return self
		if (this.length < Voxel.MIN_LENGTH) {
			subVoxels.add(this);
			return subVoxels;
		}
		// if voxel has too few points, discard
		else if (this.pointList.size() < Voxel.MIN_COUNT) {
			return subVoxels;
		}
		// if voxel is just the right size, keep it
		else if (this.pointList.size() >= Voxel.MIN_COUNT && this.pointList.size() <= Voxel.MAX_COUNT) {
			subVoxels.add(this);
			return subVoxels;
		}
		// lastly, if voxel is too big, subdivide it!
		else {    // a.k.a. this.pointList.length > Voxel.MAX_COUNT
			// babyVoxels will be the list of baby voxels
			// 8 = 2^3 subvoxels because spliting in 3-dimensions
			Voxel[] babyVoxels = new Voxel[8];
			
			// defining their dimensions below
			for (int i=0; i<8; i++) {
				babyVoxels[i] = new Voxel(
						(float) (this.x + (i%2)*(length/2.0)),
						(float) (this.y + ((i/2)%2)*(length/2.0)),
						(float) (this.z + (i/4)*(length/2.0)),
						this.length/2);
			}
			
			// assign the points to babyvoxels
			for (XYZRGBAPoint p: this.pointList) {
				// identifier of voxel in subVoxels List
				int voxelid = 0;
				
				// calculate which voxel the point belongs in
				if (p.x < this.x+this.length/2.0) {
					voxelid += 1;
				}
				if (p.y < this.y+this.length/2.0) {
					voxelid += 2;
				}
				if (p.z < this.z+this.length/2.0) {
					voxelid += 4;
				}
				
				// place the point into the appropriate subvoxel
				babyVoxels[voxelid].addPoint(p);
			}
			
			// NOW comes the recursive part: subdivide each of these baby voxels
			for (Voxel bv: babyVoxels) {
				subVoxels.addAll(bv.subdivide());
			}
			
			// Done, return the subVoxel array 
			return subVoxels;
		}
	}
	
	public XYZRGBAPoint getCentroid() {
		float avg_x=0, avg_y=0, avg_z=0;
		byte avg_r=0, avg_g=0, avg_b=0, avg_a=0;
		int count = this.pointList.size();
		for (XYZRGBAPoint p : this.pointList) {
			avg_x += p.x;
			avg_y += p.y;
			avg_z += p.z;
			avg_r += p.r;
			avg_g += p.g;
			avg_b += p.b;
			avg_a += p.a;
		}
		return new XYZRGBAPoint(avg_x/count, avg_y/count, avg_z/count, (byte)(avg_r/count), (byte)(avg_g/count), (byte)(avg_b/count), (byte)(avg_a/count));
	}
	
	public void addPoint(XYZRGBAPoint p) {
		this.pointList.add(p);
	}
	
	public void setPoints(List<XYZRGBAPoint> points) {
		this.pointList = points;
	}
}
