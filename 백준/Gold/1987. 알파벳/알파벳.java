import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int R,C;
	static int max = Integer.MIN_VALUE;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][] visit;
	static char[][] map;
	static List<Character> list = new ArrayList<>();	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		R = Integer.parseInt(in[0]);
		C = Integer.parseInt(in[1]);
		map = new char[R][C];
		visit = new boolean[R][C];
		for(int r=0; r<R; r++) {
			char[] row = br.readLine().toCharArray();
			for(int c=0; c<C; c++) {
				map[r][c] = row[c];
			}
		}
		visit[0][0] = true;
		list.add(map[0][0]);
		dfs(0,0,1);
		System.out.println(max);
		
	}
	static void dfs(int r, int c, int move) {
		if(max<move) max = move;
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=R || nc>=C || visit[nr][nc] || list.contains(map[nr][nc])) continue;
			visit[nr][nc] = true;
			list.add(map[nr][nc]);
			dfs(nr,nc,move+1);
			visit[nr][nc] = false;
			list.remove(list.size()-1);
		}
	}
}