import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map = new int[16][16];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<10; tc++) {
			int t = Integer.parseInt(br.readLine());
			int startR = 0;
			int startC = 0;
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visit = new boolean[16][16];
			
			for(int r=0; r<16; r++) {
				String[] row = br.readLine().split("");
				for(int c=0; c<16; c++) {
					map[r][c] = Integer.parseInt(row[c]);
					if(map[r][c]==2) {
						startR = r;
						startC = c;
					}
				}
			}
			q.offer(new int[] {startR,startC});
			visit[startR][startC] = true;
			int possible = bfs(q, visit);
			sb.append("#").append(t).append(" ").append(possible).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int bfs(Queue<int[]> q, boolean[][] visit) {
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr<0 || nc<0 || nr>15 || nc>15 || visit[nr][nc] || map[nr][nc]==1) continue;
				q.offer(new int[] {nr,nc});
				visit[nr][nc] = true;
				if(map[nr][nc]==3) return 1;
			}
		}
		return 0;
	}
}