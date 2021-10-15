package poseidon.mod.test.blocks;

public interface ILog {

	public default void log(String s) {
		System.out.println(s);
	}
	
}
