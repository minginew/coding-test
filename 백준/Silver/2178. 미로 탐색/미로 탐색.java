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
	static int minCount;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<int[]> q = new LinkedList<>();
		String[] input = br.readLine().split(" ");
		
 		N = Integer.parseInt(input[0]);
 		M = Integer.parseInt(input[1]);
 		map = new int[N][M];
 		
 		for(int r=0; r<N; r++) {
 			String[] row = br.readLine().split("");
 			for(int c=0; c<M; c++) {
 				map[r][c] = Integer.parseInt(row[c]);
 			}
 		}
 		
 		map[0][0]=0;
 		minCount++;
 		q.offer(new int[] {0,0});
 		bfs(q);
 		
 		System.out.println(minCount);
	}
	
	static void bfs(Queue<int[]> q ) {
		int size = q.size();
		while(!q.isEmpty()) {
			minCount++;
			for(int i=0; i<size; i++) {
				int r = q.peek()[0];
				int c = q.poll()[1];
				
				for(int d=0; d<4; d++) {
					int nr = r + dy[d];
					int nc = c + dx[d];
					if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==1) {
						map[nr][nc]=0;
						q.offer(new int[] {nr,nc});
					}
				}
			}
			size = q.size();
			if(map[N-1][M-1]==0) return;
		}
	}
}