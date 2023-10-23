import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int ini = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] dist;
	static boolean[][] visit;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			visit = new boolean[N][N];
			if(N==0) {
				System.out.println(sb);
				return;
			}
			for(int r=0; r<N; r++) {
				String[] row = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(row[c]);
					dist[r][c] = ini;
				}
			}
			
			dijstra();
			sb.append("Problem")
				.append(" ")
				.append(n++)
				.append(":")
				.append(" ")
				.append(dist[N-1][N-1])
				.append("\n");
			
		}
	}
	static void dijstra() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0});
		visit[0][0] = true;
		dist[0][0] = map[0][0];
		
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(visit[nr][nc]) {
					if(dist[nr][nc] > dist[r][c] + map[nr][nc]) {
						dist[nr][nc] = dist[r][c] + map[nr][nc];
						q.offer(new int[] {nr,nc});
					}
				}else {
					dist[nr][nc] = dist[r][c] + map[nr][nc];
					q.offer(new int[] {nr,nc});
					visit[nr][nc] = true;
				}
			}
		}
	}
}