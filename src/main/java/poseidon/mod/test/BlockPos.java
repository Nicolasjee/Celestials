package poseidon.mod.test;

public class BlockPos {

	public int x;
	public int y;
	public int z;
	
	public BlockPos(int x1, int y1, int z1) {
		this.x = x1;
		this.y = y1;
		this.z = z1;
	}
	
	public void up(int i) {
		y += i;
	}
	
	public void print() {
		System.out.println("X: " + this.x + ", Y: " + this.y + ", Z: " + this.z);
	}
	
}
