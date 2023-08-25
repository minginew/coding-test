import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] in = br.readLine().split(" ");
			int N = Integer.parseInt(in[0]);
			int M = Integer.parseInt(in[1]);
			int[][] map = new int[N][3]; //N은 행번호, 
			
			for(int r=0; r<N; r++) {
				String[] row = br.readLine().split("");
				for(int c=0; c<M; c++) {
					if(row[c].equals("W")) {
						if(r>0) map[r][1]++;
						if(r>1) map[r][2]++;
					}
					if(row[c].equals("B")) {
						map[r][0]++;
						if(r>1) map[r][2]++;
					}
					if(row[c].equals("R")) {
						map[r][0]++;
						if(r>0) map[r][1]++;
					}
				}
			}
			for(int i=1; i<N; i++) {
				map[i][0] += map[i-1][0];
				map[i][1] += map[i-1][1];
				map[i][2] += map[i-1][2];
			}
			int min = Integer.MAX_VALUE;
			for(int i=0; i<N-2; i++) {
				for(int j=i+1; j<N-1; j++) {
					int sum = map[i][0]+(map[j][1]-map[i][1])+(map[N-1][2]-map[j][2]);
					if(sum<min) min=sum;
				}
			}
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
}
