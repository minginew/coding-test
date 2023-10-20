import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] in1 = br.readLine().split(" ");
		int N = Integer.parseInt(in1[0]);
		int M = Integer.parseInt(in1[1]);
		int[][] map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(row[c]);
			}
		}
		//x=r, y=c;
		
		for(int r=0; r<N; r++) {//0,0부터 x,y까지 누적합!
			for(int c=0; c<N; c++) {
				if(r==0) {
					if(c==0) continue;
					map[r][c] += map[r][c-1];
				}else {
					if(c==0) map[r][c] += map[r-1][c];
					else map[r][c] += map[r-1][c] + map[r][c-1] - map[r-1][c-1];
				}
			}
		}
		
		
		for(int i=0; i<M; i++) {
			String[] in2 = br.readLine().split(" ");
			int x1 = Integer.parseInt(in2[0]); 
			int y1 = Integer.parseInt(in2[1]); 
			int x2 = Integer.parseInt(in2[2]); 
			int y2 = Integer.parseInt(in2[3]);
			
			int sum = 0;
			if(x1==1 && y1==1) {
				sum = map[x2-1][y2-1];
				sb.append(sum).append("\n");
			}else if(x1==1) {
				sum = map[x2-1][y2-1]  - map[x2-1][y1-2];
				sb.append(sum).append("\n");
			}else if(y1==1) {
				sum = map[x2-1][y2-1] - map[x1-2][y2-1];
				sb.append(sum).append("\n");
			}else {
				sum = map[x2-1][y2-1] -map[x1-2][y2-1] - map[x2-1][y1-2] + map[x1-2][y1-2];
				sb.append(sum).append("\n");
			}
		}
		System.out.println(sb);
	}
}