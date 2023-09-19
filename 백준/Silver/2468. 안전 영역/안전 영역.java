import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int maxCount;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxCount = -1;
		map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(row[c]);
			}
		}
		
		for(int i=0; i<100; i++) {
			
			Queue<int[]> q = new LinkedList<>();
			boolean[][]  visit = new boolean[N][N];
			
			int count=0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(map[r][c]<=i || visit[r][c]) continue;
					q.offer(new int[] {r,c});
					visit[r][c] = true;
					bfs(q,visit,i);
					count++;
				}
			}
			if(maxCount<count) maxCount = count;
		}
		System.out.println(maxCount);
		
	}
	
	static void bfs(Queue<int[]> q, boolean[][] visit, int i) {
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