import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N,M,minTime;
	static int[][] map;
	static int[] dx = {-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new int[100001][2]; //[0]: 해당 인덱스까지 오는데 걸린시간, [1]: 해당 인덱스 이전의 인덱스
		if(N==M) {
			System.out.println(0);
			System.out.println(N);
			return;
		}
		for(int i=0; i<=100000; i++) map[i][0] = -1;
		map[N] = new int[] {0,N}; 
		bfs();
		
		System.out.println(map[M][0]);
		StringBuilder sb = new StringBuilder();
		sb.append(M).append(" ");
		int p = map[M][1]; //내 부모가 누구인지.
		while(true) {
			sb.append(p).append(" ");
			if(p==N) break;
			p = map[p][1];
		}
		String[] move = sb.toString().split(" ");
		for(int i=move.length-1; i>=0; i--) {
			System.out.print(move[i]);
			System.out.print(" ");
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		int size = q.size();
		while(!q.isEmpty()) {
			for(int i=0; i<size; i++) {
				int x = q.poll();
				for(int d=0; d<3; d++) {
					int nx = x;
					if(d<2) nx += dx[d];
					else nx *= 2;
					
					if(nx<0 || nx>100000) continue;
					if(map[nx][0] == -1) {
						map[nx][0] = map[x][0]+1;
						map[nx][1] = x; //nx번째 인덱스는 x번째 인덱스로부터 왔다.
						q.offer(nx);
					}else if(map[nx][0] > map[x][0]+1) {
						map[nx][0] = map[x][0]+1;
						map[nx][1] = x;
						q.offer(nx);
					}
				}
			}
			size = q.size();	
		}
	}
}