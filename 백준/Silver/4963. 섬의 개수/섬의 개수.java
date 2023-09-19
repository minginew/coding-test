import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N,M;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[1]);
			M = Integer.parseInt(in[0]);
			map = new int[N][M];
			
			if(N==0&&M==0) return;
			
			for(int r=0; r<N; r++) {
				String[] row = br.readLine().split(" ");
				for(int c=0; c<M; c++) {
					map[r][c] = Integer.parseInt(row[c]);
				}
			}
			
			int count = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c]==0) continue;
					
					Queue<int[]> q = new LinkedList<>();
					q.offer(new int[] {r,c});
					map[r][c] = 0; //땅을 바다로 만들면서 방문처리
					bfs(q);
					count++;
				}
			}
			System.out.println(count);
		}
	}
	
	public static void bfs(Queue<int[]> q) {
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			
			for(int d=0; d<8; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc]==0) continue;
				q.offer(new int[] {nr,nc});
				map[nr][nc] = 0;
			}
		}
		
	}
}