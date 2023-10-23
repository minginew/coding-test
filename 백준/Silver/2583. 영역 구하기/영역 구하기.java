import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N,M,K,Answer;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map;
	static Queue<int[]> q;
	static List<Integer> areaSize;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in = br.readLine().split(" ");
		N=Integer.parseInt(in[1]);
		M=Integer.parseInt(in[0]);
		K=Integer.parseInt(in[2]);
		map = new int[N][M];
		areaSize = new ArrayList<>();

		for(int k=0; k<K; k++) {
			String[] xy = br.readLine().split(" ");
			int r1 = Integer.parseInt(xy[0]);
			int c1 = Integer.parseInt(xy[1]);
			int r2 = Integer.parseInt(xy[2]);
			int c2 = Integer.parseInt(xy[3]);
			
			for(int r=r1; r<r2; r++) {
				for(int c=c1; c<c2; c++) {
					map[r][c] = 1;
				}
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c]==1) continue;
				q = new LinkedList<>();
				q.offer(new int[] {r,c});
				map[r][c] = 1;
				bfs(areaSize);
			}
		}
		
		Collections.sort(areaSize);
		sb.append(Answer).append("\n");
		for(int n : areaSize) sb.append(n).append(" ");
		System.out.println(sb);	
		
	}
	static void bfs(List<Integer> areaSize) {
		int area = 0;
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			area++;
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc]==1) continue;
				q.offer(new int[] {nr,nc});
				map[nr][nc] = 1;
			}
		}
		Answer++;
		areaSize.add(area);
	}
}
