import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N,max;
	static int[] dr = {-1,-1,1,1};
	static int[] dc = {-1,1,1,-1};
	static boolean[] visit;
	static int[][] map;
	static int[] start = new int[2];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int r=0; r<N; r++) {
				String[] row = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(row[c]);
				}
			}
			max = -1;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					start = new int[] {r,c};
					visit = new boolean[101];
					visit[map[r][c]] = true;
					dfs(r,c,0,0,0);
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void dfs(int r, int c, int move, int d, int changeD) {
			if(changeD==4) return; //방향전환 횟수
			for(int i=0; i<2; i++) {// i:0 현재 방향 그대로 이동, i:1 방향전환
				int nr = r+dr[(d+i)%4]; 
				int nc = c+dc[(d+i)%4];
				if(nr==start[0] && nc==start[1]) {//시작점에 다시 돌아온다면 이동횟수를 기록하고 종료
					if(max<move+1) max = move+1;
					return;
				}
				if(nr<0||nr>=N||nc<0||nc>=N||visit[map[nr][nc]]) continue;//범위를 초과하는 경우
				visit[map[nr][nc]] = true;
				dfs(nr, nc, move+1, (d+i)%4, changeD+i);
				visit[map[nr][nc]] = false;
			}		
	}
	
}