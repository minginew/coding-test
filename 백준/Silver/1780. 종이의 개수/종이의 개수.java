import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int paper1;
	static int paper2;
	static int paper3;
	
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
		System.out.println(paper3); //-1
		System.out.println(paper1); //0
		System.out.println(paper2); //1

	}
	static void reg(int R, int C, int N, int[][] map) {//R,C는 전수조사할 시작 위치, N은 전수조사할 길이
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		
		for(int r=R; r<R+N; r++) {
			for(int c=C; c<C+N; c++) {
				if(map[r][c]==0) count1++;
				else if(map[r][c]==1) count2++;
				else if(map[r][c]==-1) count3++;
			}
		}
		
		if(count2==0 && count3==0){
			paper1++;
			return;
		}else if(count1==0 && count3==0){
			paper2++;
			return;
		}else if(count1==0 && count2==0){
			paper3++;
			return;
		}else {
			for(int i=R; i<=R+2*N/3; i+=N/3 ) {
				for(int j=C; j<=C+2*N/3; j+=N/3 ) {
					reg(i, j, N/3, map);
				}
			}
		}
	}

}