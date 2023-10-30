import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N,M;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		map = new int[N][M];
		
		String[] rainArr = br.readLine().split(" ");
		for(int c=0; c<M; c++) {
			int rain = Integer.parseInt(rainArr[c]);
			for(int r=N-1; r>=N-rain; r--) {
				map[r][c] = 1;
			}
		}
		
		int answer = 0;
		for(int r=N-1; r>=0; r--) {
			int left = 0;
			int right = 0;
			int count = 0;
			for(int c=0; c<M; c++) {
				if(left==0) {
					left = map[r][c];
				}else if(right==0){
					right = map[r][c];
					if(right==1) {
						answer += count;
						right = 0;
						count=0;
						continue;
					}
					count++;
				}
			}
		}
		System.out.println(answer);
	}
}