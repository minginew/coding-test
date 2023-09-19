import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int maxChunk;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			maxChunk=1;
			map = new int[N][N];
			
			
			for(int r=0; r<N; r++) {//치즈세팅
				String[] row = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(row[c]);
				}
			}
			
			for(int i=1; i<=100; i++) {//i는 1일~100일까지
				Queue<int[]> q = new LinkedList<>();
				boolean[][] visit = new boolean[N][N];
				
				for(int r=0; r<N; r++) { //i일와 같은 맛에 해당하는 치즈 먹기, 0으로
					for(int c=0; c<N; c++) {
						if(map[r][c] == i) map[r][c]=0;
					}
				}
				
				int chunk = 0;
				for(int r=0; r<N; r++) {//치즈 덩어리수 계산하기
					for(int c=0; c<N; c++) {
						if(map[r][c]==0 || visit[r][c]) continue;
						q.offer(new int[] {r,c});
						visit[r][c] = true;
						bfs(q,visit);
						chunk++;
					}
				}
				if(maxChunk<chunk) maxChunk=chunk;
				
			}
			sb.append("#").append(tc).append(" ").append(maxChunk).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs(Queue<int[]> q, boolean[][] visit) {
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=N || map[nr][nc]==0 || visit[nr][nc]) continue;
				q.offer(new int[] {nr,nc});
				visit[nr][nc] = true;
			}
		}
	}
}