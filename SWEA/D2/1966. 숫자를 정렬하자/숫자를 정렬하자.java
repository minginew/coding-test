import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			int[] data = new int[N];
			for(int i=0; i<N; i++) data[i] = Integer.parseInt(input[i]);
			
			for(int i=1; i<data.length; i++) {
				int key = data[i]; // 이번에 정렬할 값
				int j;
				for(j=i-1; j>=0 && key<data[j]; j--) {
					data[j+1] = data[j];
				}//넣을 위치를 찾고 뒤로 미는 작업까지 동시에 진행 (2가지)
				data[j+1] = key;
			}
			sb.append("#").append(t).append(" ");
			for(int n : data) sb.append(n).append(" ");
			sb.append("\n");
		}	
		System.out.println(sb.toString());
	}
}