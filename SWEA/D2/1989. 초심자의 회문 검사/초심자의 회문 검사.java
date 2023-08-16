import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int answer = 0;
			StringBuilder sb = new StringBuilder();
			String origin = br.readLine();
			String[] str = origin.split(" ");
			for(String s : str) sb.append(s);
			if(sb.reverse().toString().equals(origin)) answer = 1;
			System.out.println("#" + t + " " + answer);
		}
	}
}