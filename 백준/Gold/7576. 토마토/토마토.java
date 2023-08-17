import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int[][] map;
	static int N;
	static int M;
	static int time;
	static int ripeTomato; // 익은 토마토 수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<int[]> q = new LinkedList<>();
		String[] input = br.readLine().split(" ");
		
 		N = Integer.parseInt(input[1]);
 		M = Integer.parseInt(input[0]);

 		map = new int[N][M];

 		
 		for(int r=0; r<N; r++) {
 			String[] row = br.readLine().split(" ");
 			for(int c=0; c<M; c++) {
 				int tomato = Integer.parseInt(row[c]);
 				map[r][c] = tomato;
 				if(tomato == 1) { // 익은 토마토의 좌표를 q에 쌓는다.
 					q.offer(new int[] {r,c});
 					ripeTomato++;
 				}
 				if(tomato == -1) ripeTomato++; //빈 공간은 익은 토마토로 구분한다. 처음부터 다 익어있는 경우를 위해
 			}
 		}
 		
 		if(ripeTomato == N*M) {
 			System.out.println(0);
 			return;
 		}
 		
 		bfs(q);
 		
 		if(ripeTomato == N*M) {
 			System.out.println(time);
 			return;
 		}else {
 			System.out.println(-1);
 		}
	}
	
	static void bfs(Queue<int[]> q) {
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int r = q.peek()[0];
				int c = q.poll()[1];
				
				for(int d=0; d<4; d++) {
					int nr = r+dy[d];
					int nc = c+dx[d];
					if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
					if(map[nr][nc]==0) {
						map[nr][nc] = 1;
						ripeTomato++;
						q.offer(new int[] {nr,nc});
					}
				}
			}
			size = q.size();
			if(q.isEmpty()) break;
			time++;
		}
	}
}