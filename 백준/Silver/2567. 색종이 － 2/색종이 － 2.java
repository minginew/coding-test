import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[100][100];
		for(int n=0; n<N; n++) {
			String[] input = br.readLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			for(int i=r; i<r+10; i++) {
				for(int j=c; j<c+10; j++) {
					map[i][j] = 1;
				}
			}
		}
		for(int r=0; r<100; r++) {
			for(int c=0; c<100; c++) {
				if(map[r][c] == 0) continue;
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(nr<0 || nc<0 || nr>=100 || nc>=100) count++;
					else if(map[nr][nc] == 0) count++;
				}
			}
		}
		System.out.println(count);
	}
}