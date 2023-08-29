import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split("");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(row[c]);
			}
		}
		reg(0, 0, N, map, sb);
		System.out.println(sb.toString());
	}
	
	static void reg(int R, int C, int N, int[][] map, StringBuilder sb) {
		int zeroCount=0;
		int oneCount=0;
		for(int r=R; r<R+N; r++) {
			for(int c=C; c<C+N; c++) {
				if(map[r][c]==0) zeroCount++;
				else oneCount++;
			}
		}
		
		if(zeroCount == 0) {
			sb.append("1");
		}else if(oneCount == 0) {
			sb.append("0");
		}else {
			sb.append("(");
			for(int r=R; r<=R+N/2; r+=N/2) {
				for(int c=C; c<=C+N/2; c+=N/2) {
					reg(r, c, N/2, map, sb);
				}
			}
			sb.append(")");
		}
	}
}