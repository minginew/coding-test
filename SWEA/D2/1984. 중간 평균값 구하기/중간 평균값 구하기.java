import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int [] map = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(map);
			int sum=0;
			for(int i=1; i<9; i++) sum+=map[i];
			System.out.println("#"+tc+" "+(int)Math.round(sum/8.0));
		}
	}
}
