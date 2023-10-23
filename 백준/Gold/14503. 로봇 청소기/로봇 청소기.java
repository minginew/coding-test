import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N,M,R,C,D,Clean; // R,C = 시작좌표 , D = 바라보는 방향 
	static int[] dr = {-1,0,1,0}; //북-서-남-동 (반시계)
	static int[] dc = {0,-1,0,1};
	static int[][] map;
	static boolean[][] visit;
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in1 = br.readLine().split(" ");
		String[] in2 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		R = Integer.parseInt(in2[0]);
		C = Integer.parseInt(in2[1]);
		D = Integer.parseInt(in2[2]);
		if(D==1) D=3;
		else if(D==3) D=1;
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split(" ");
			for(int c=0; c<M; c++) { 
				map[r][c] = Integer.parseInt(row[c]);
			}
		}

		bfs();
		System.out.println(Clean);
	}
	static void bfs() {
		int count=1;
		q.offer(new int[] {R,C});
		map[R][C] = 2;
	outter:while(!q.isEmpty()) {
			int r = q.peek()[0]; 
			int c = q.poll()[1];

			for(int d=1; d<5; d++) {
				int newD = (D+d)%4;
				int nr = r+dr[newD];
				int nc = c+dc[newD];
				if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc] != 0) continue;
				
				q.offer(new int[] {nr,nc});
				D =  newD;
				map[nr][nc] = 2;
				count++;
				continue outter;
			}
			int nr = r -dr[D];
			int nc = c -dc[D];
			if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc]==1) {
				Clean = count;
				return;
			}else if(map[nr][nc]==2){
				q.offer(new int[] {nr,nc});
			}
		}
		Clean = count;
	}
}