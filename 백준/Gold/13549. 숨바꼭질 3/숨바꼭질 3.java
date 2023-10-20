import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N,M,minTime;
	static int[] map;
	static int[] dx = {-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new int[100001];
		
		Arrays.fill(map, -1);
		map[N] = 0;
		bfs();
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		int size = q.size();
		while(!q.isEmpty()) {
			for(int i=0; i<size; i++) {
				int x = q.poll();
				for(int d=0; d<2; d++) {
					int nx = x+dx[d];
					if(nx<0 || nx>100000) continue;
					if(map[nx] == -1) {
						map[nx] = map[x]+1;
						q.offer(nx);
					}else if(map[nx] > map[x]+1) {
						map[nx] = map[x]+1;
						q.offer(nx);
					}
				}
				
				int x2 = x*2;
				if(x2>100000) continue;
				if(map[x2] == -1) {
					map[x2] = map[x];
					q.offer(x2);
				}else if(map[x2] > map[x]) {
					map[x2] = map[x];
					q.offer(x2);
				}
			}
			size = q.size();	
		}
		System.out.println(map[M]);
	}
}