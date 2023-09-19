import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int M;
	static int total = -1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static String[][][][][][][][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new String[N][M][2][2][2][2][2][2]; //no key, a, b, c, d, e, f 각 키를 가지고 있는 서로 다른차원
		
		int startR = 0;
		int startC = 0;
		
		for(int i=0; i<N; i++) {
			String[] row = br.readLine().split("");
			for(int j=0; j<M; j++) {
				String v = row[j];
				if(v.equals("0")) {
					startR = i;
					startC = j;
				}
				for(int a=0; a<2; a++) {
					for(int b=0; b<2; b++) {
						for(int c=0; c<2; c++) {
							for(int d=0; d<2; d++) {
								for(int e=0; e<2; e++) {
									for(int f=0; f<2; f++) {
										map[i][j][a][b][c][d][e][f] = v;
									}
								}
							}
						}
					}
				}
			}
		} //0일땐 문이 안열림, 1일땐 문이 열림
		
		Queue<int[]> q = new LinkedList<>();
		boolean[][][][][][][][] visit = new boolean[N][M][2][2][2][2][2][2];
		q.offer(new int[] {startR, startC, 0, 0, 0, 0, 0, 0});
		visit[startR][startC][0][0][0][0][0][0] = true;
		bfs(q, visit);
		System.out.println(total);
		
	}
	static void bfs(Queue<int[]> q, boolean[][][][][][][][] visit) {
		int size = q.size();
		int move = 0;
		while(!q.isEmpty()) {
			for(int i=0; i<size; i++) {
				int row=q.peek()[0];
				int col=q.peek()[1];
				int a=q.peek()[2];
				int b=q.peek()[3];
				int c=q.peek()[4];
				int d=q.peek()[5];
				int e=q.peek()[6];
				int f=q.poll()[7];
				
				if(map[row][col][a][b][c][d][e][f].equals("1")) {
					total = move;
					return;
				}
				
				for(int dir=0; dir<4; dir++) {
					int nr = row+dr[dir];
					int nc = col+dc[dir];
					if(nr<0 || nc<0 || nr>=N || nc>=M || visit[nr][nc][a][b][c][d][e][f] || map[nr][nc][a][b][c][d][e][f].equals("#")) continue;
					if(a==0 && map[nr][nc][a][b][c][d][e][f].equals("A")) continue;
					if(b==0 && map[nr][nc][a][b][c][d][e][f].equals("B")) continue;
					if(c==0 && map[nr][nc][a][b][c][d][e][f].equals("C")) continue;
					if(d==0 && map[nr][nc][a][b][c][d][e][f].equals("D")) continue;
					if(e==0 && map[nr][nc][a][b][c][d][e][f].equals("E")) continue;
					if(f==0 && map[nr][nc][a][b][c][d][e][f].equals("F")) continue;
					
					if(map[nr][nc][a][b][c][d][e][f].equals("a")) {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
						if(a==0 && !visit[nr][nc][1][b][c][d][e][f]) {
							q.offer(new int[] {nr,nc,1,b,c,d,e,f});
							visit[nr][nc][1][b][c][d][e][f] = true;
						}
					}else if (map[nr][nc][a][b][c][d][e][f].equals("b")) {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
						if(b==0 && !visit[nr][nc][a][1][c][d][e][f]) {
							q.offer(new int[] {nr,nc,a,1,c,d,e,f});
							visit[nr][nc][a][1][c][d][e][f] = true;
						}
					}else if (map[nr][nc][a][b][c][d][e][f].equals("c")) {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
						if(c==0 && !visit[nr][nc][a][b][1][d][e][f]) {
							q.offer(new int[] {nr,nc,a,b,1,d,e,f});
							visit[nr][nc][a][b][1][d][e][f] = true;
						}
					
					}else if (map[nr][nc][a][b][c][d][e][f].equals("d")) {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
						if(d==0 && !visit[nr][nc][a][b][c][1][e][f]) {
							q.offer(new int[] {nr,nc,a,b,c,1,e,f});
							visit[nr][nc][a][b][c][1][e][f] = true;
						}
					
					}else if (map[nr][nc][a][b][c][d][e][f].equals("e")) {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
						if(e==0 && !visit[nr][nc][a][b][c][d][1][f]) {
							q.offer(new int[] {nr,nc,a,b,c,d,1,f});
							visit[nr][nc][a][b][c][d][1][f] = true;
						}
					
					}else if (map[nr][nc][a][b][c][d][e][f].equals("f")) {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
						if(f==0 && !visit[nr][nc][a][b][c][d][e][1]) {
							q.offer(new int[] {nr,nc,a,b,c,d,e,1});
							visit[nr][nc][a][b][c][d][e][1] = true;
						}
					
					}else {
						q.offer(new int[] {nr,nc,a,b,c,d,e,f});
						visit[nr][nc][a][b][c][d][e][f] = true;
					}
				}
			}
			size = q.size();
			move++;
		}
	}
}