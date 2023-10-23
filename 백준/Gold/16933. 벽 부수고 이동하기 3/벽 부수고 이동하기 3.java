
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N,M,K;
	static int minMove = -1;
	static int[][][] map;
	static boolean[][][] visit;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		K = Integer.parseInt(in[2]);
		
		map = new int[N][M][K+1];
		visit = new boolean[N][M][K+1];
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split("");
			for(int c=0; c<M; c++) {
				int value = Integer.parseInt(row[c]);
				for(int k=0; k<=K; k++) map[r][c][k] = value;
			}
		}
		
		bfs();
		System.out.println(minMove);
	}
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0,0,0});
		visit[0][0][0] = true;
		
		int size = q.size();
		int day = 0; //0은 낮, 1은 밤: 벽을 못부숨 
		int move = 1;
		while(!q.isEmpty()) {
			for(int i=0; i<size; i++) {
				int r = q.peek()[0];
				int c = q.peek()[1];
				int k = q.poll()[2];
				
				if(r==N-1 && c==M-1) {
					minMove = move;
					return;
				}
				if(day==1) q.offer(new int[] {r,c,k});
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(nr<0 || nc<0 || nr>=N || nc>=M || visit[nr][nc][k]) continue;
					if(map[nr][nc][k]==0) {
						q.offer(new int[] {nr,nc,k});
						visit[nr][nc][k] = true;
					}else if(map[nr][nc][k]==1) {
						if(day==0 && k<K && !visit[nr][nc][k+1]) {
							q.offer(new int[] {nr,nc,k+1});
							visit[nr][nc][k+1] = true;
						}
					}
				}
			}
			size = q.size();
			day = (day+1)%2;
			move++;
		}
	}
}

