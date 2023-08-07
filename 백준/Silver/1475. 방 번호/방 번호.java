import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] roomNum = bf.readLine().split("");
		int[] setCount = new int[9];
		int max = 0;

		for (String s : roomNum) {
			if (s.equals("9")) {
				setCount[6] += 1;
				continue;
			}
			setCount[Integer.parseInt(s)] += 1;
		}
		setCount[6] = (int)Math.ceil(setCount[6]/2.0);
		for (int i = 0; i < setCount.length; i++) {
			
			if (setCount[i] > max) max = setCount[i];
		}
		System.out.println(max);
	}
}
