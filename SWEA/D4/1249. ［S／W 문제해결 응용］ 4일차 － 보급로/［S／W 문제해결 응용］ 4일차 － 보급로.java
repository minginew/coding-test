import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N;
	static int[][][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N][2];
			boolean[][] visit = new boolean[N][N];
			Queue<int[]> q = new LinkedList<>();
			
 			for(int i=0; i<N; i++) {
				String[] row = br.readLine().split("");
				for(int j=0; j<N; j++) {
					map[i][j][0] = Integer.parseInt(row[j]);
					map[i][j][1] = Integer.parseInt(row[j]);
				}
			}
			q.offer(new int[] {0,0});
			visit[0][0] = true;
 			dijkstra(q, visit);
			sb.append("#").append(t).append(" ").append(map[N-1][N-1][1]).append("\n");
		}
		System.out.println(sb);
	}
	public static void dijkstra(Queue<int[]> q,  boolean[][] visit) {
		while(!q.isEmpty()) {
				int r = q.peek()[0];
				int c = q.poll()[1];
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
					if(!visit[nr][nc]) {
						map[nr][nc][1] += map[r][c][1];
						visit[nr][nc] = true;
						q.offer(new int[] {nr,nc});
					}else {
						if(map[nr][nc][1] > map[r][c][1]+map[nr][nc][0]) {
							map[nr][nc][1] = map[r][c][1]+map[nr][nc][0];
							q.offer(new int[] {nr,nc});
						}
					}
					
				}
		}
	}
}