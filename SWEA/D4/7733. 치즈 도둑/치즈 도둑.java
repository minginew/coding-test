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
			int min = 100;
			for(int r=0; r<N; r++) {//치즈세팅
				String[] row = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(row[c]);
					if(min>map[r][c]) min = map[r][c];
				}
			}
			
			for(int i=1; i<100; i++) {//i는 1일~100일까지
				if(i<min) continue;
				Queue<int[]> q = new LinkedList<>();
				boolean[][] visit = new boolean[N][N];
				
				int chunk = 0;
				for(int r=0; r<N; r++) {//치즈 덩어리수 계산하기
					for(int c=0; c<N; c++) {
						if(map[r][c]<=i || visit[r][c]) continue;
						q.offer(new int[] {r,c});
						visit[r][c] = true;
						bfs(q,visit,i);
						chunk++;
					}
				}
				if(maxChunk<chunk) maxChunk=chunk;
			}
			sb.append("#").append(tc).append(" ").append(maxChunk).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs(Queue<int[]> q, boolean[][] visit, int i) {
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=N || map[nr][nc]<=i || visit[nr][nc]) continue;
				q.offer(new int[] {nr,nc});
				visit[nr][nc] = true;
			}
		}
	}
}