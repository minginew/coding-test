import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N,M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visit;
	static Queue<int[]> q;
	static int Time = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new char[N][M];
		
		for(int r=0; r<N; r++) {
			char[] row = br.readLine().toCharArray();
			for(int c=0; c<M; c++) {
				map[r][c] = row[c];
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == 'L') {
					q = new LinkedList<>();
					visit = new boolean[N][M];
					q.offer(new int[] {r,c});
					visit[r][c] = true;
					bfs();
				}
			}
		}
		System.out.println(Time);
		
	}
	static void bfs() {
		int size = q.size();
		int time = -1;
		while(!q.isEmpty()) {
			for(int i=0; i<size; i++) {
				int r = q.peek()[0];
				int c = q.poll()[1];
				
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(nr<0 || nc<0 || nr>=N || nc>=M || visit[nr][nc] || map[nr][nc]=='W') continue;
					q.offer(new int[] {nr,nc});
					visit[nr][nc] = true;
				}
			}
			size = q.size();
			time++;
		}
		if(Time < time) Time = time;		
	}
}
