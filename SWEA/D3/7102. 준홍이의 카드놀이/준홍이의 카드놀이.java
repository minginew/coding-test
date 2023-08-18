import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String[] size = br.readLine().split(" ");
			int N = Integer.parseInt(size[0]);
			int M = Integer.parseInt(size[1]);
			
			int[] firstCard = new int[N];
			int[] secondCard = new int[M];
			int[] count = new int[N+M+1];
			
			for(int i=0; i<N; i++) firstCard[i] = i+1;
			for(int i=0; i<M; i++) secondCard[i] = i+1;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					count[firstCard[i]+secondCard[j]]++;
				}
			}
			
			int max = 0;
			for(int n : count) if(max<=n) max=n;
			sb.append("#").append(t).append(" ");
			for(int i=0; i<count.length; i++) {
				if(count[i] == max) sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}