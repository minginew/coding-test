import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] arr = new String[N+1];
			for(int i=1; i<arr.length; i++) {
				arr[i] = br.readLine().split(" ")[1];
			}
			sb.append("#").append(t).append(" ");
			inOrder(1, arr, sb);
			sb.append('\n');			
		}
		System.out.println(sb.toString());
	}
	
	static void inOrder(int i,String[] arr, StringBuilder sb) {
		if(i<arr.length) {
			inOrder(2*i,arr,sb);
			sb.append(arr[i]);
			inOrder(2*i+1,arr,sb);
		}
	}
}