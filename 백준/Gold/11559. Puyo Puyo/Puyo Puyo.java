import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int count;
	static boolean boom;
	static boolean[][] visit;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[][] map = new char[12][6];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int r=0; r<12; r++) { //초기맵
			char[] row = br.readLine().toCharArray();
			for(int c=0; c<6; c++) {
				map[r][c] = row[c];
			}
		}
		
		boom = true; //연쇄가 일어났는지 여부
		while(boom) {
			boom = false;
			visit = new boolean[12][6];
			for(int r=0; r<12; r++) {
				for(int c=0; c<6; c++) {
					if(map[r][c] == '.') continue;
					bfs(r,c);
				}
			}
			if(boom) count++;
			rebuild();
		}
		System.out.println(count);
	}
	
	static void bfs(int R, int C) { 
		Queue<int[]> boomCoor = new LinkedList<>(); //터뜨릴 좌표 
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {R,C});
		visit[R][C] = true;
		
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			boomCoor.offer(new int[] {r,c});
			
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr<0 || nc<0 || nr>=12 || nc>=6 || visit[nr][nc]) continue;
				if(map[R][C] != map[nr][nc]) continue;
				q.offer(new int[] {nr,nc});
				visit[nr][nc] = true;
			}
		}
		if(boomCoor.size()>=4) {
			boom = true; //연쇄가 발생!!
			while(!boomCoor.isEmpty()) {
				int r = boomCoor.peek()[0];
				int c = boomCoor.poll()[1];
				map[r][c] = '.';
			}
		}
	}
	
	static void rebuild() {
		Queue<Character> puyo = new LinkedList<>();
		for(int c=0; c<6; c++) {
			for(int r=11; r>=0; r--) {
				if(map[r][c] == '.') continue;
				puyo.offer(map[r][c]);
				map[r][c] = '.';
			}
			int n = 11;
			while(!puyo.isEmpty()) {
				char color = puyo.poll();
				map[n--][c] = color;
			}
		}
	}
}