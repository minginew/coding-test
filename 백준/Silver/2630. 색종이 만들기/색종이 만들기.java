import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int white;
	static int blue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(row[c]);
			}
		}
		reg(0, 0, N, map);
		System.out.println(white);
		System.out.println(blue);
		
		
	}
	static void reg(int R, int C, int N, int[][] map) {//R,C는 전수조사할 시작 위치, N은 전수조사할 길이
		int zeroCount = 0;
		int oneCount = 0;
		for(int r=R; r<R+N; r++) {
			for(int c=C; c<C+N; c++) {
				if(map[r][c]==0) zeroCount++;
				else oneCount++;
			}
		}
		if(oneCount != 0 && zeroCount != 0 ) {
			for(int i=R; i<=R+N/2; i+=N/2 ) {
				for(int j=C; j<=C+N/2; j+=N/2 ) {
					reg(i, j, N/2, map);
				}
			}
		}else if(zeroCount==0){
			blue++;
			return;
		}else if(oneCount==0){
			white++;
			return;
		}
	}

}