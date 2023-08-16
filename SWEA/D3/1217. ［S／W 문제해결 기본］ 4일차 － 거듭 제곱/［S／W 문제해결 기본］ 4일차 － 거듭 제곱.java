import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			multiple(1, Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void multiple(int ini, int num, int count) {
		ini *= num;
		answer = ini;
		if(--count>0) multiple(ini, num, count);
	}
}