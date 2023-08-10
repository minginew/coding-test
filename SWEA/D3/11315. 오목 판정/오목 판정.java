import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int M = 4; 
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[][] map = new String[N][N];
			
			for(int r=0; r<N; r++) {
				String[] arr = br.readLine().split("");
				for(int c=0; c<N; c++) {
					map[r][c] = arr[c];		
				}
			}
			String answer = "NO";
			Outter: for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(!map[r][c].equals("o")) continue;
					int countR=0;
					int countC=0;
					int countCr=0;
					int countCl=0;
					for(int m=1; m<=M; m++) {
						if(r+m<N && map[r+m][c].equals("o")) countR++;
						if(c+m<N && map[r][c+m].equals("o")) countC++;
						if(r+m<N && c+m<N && map[r+m][c+m].equals("o")) countCr++;
						if(r+m<N && c-m>=0 && map[r+m][c-m].equals("o")) countCl++;
					}
					if(countR==4 || countC==4 || countCr==4 || countCl==4) {
						answer = "YES";
						break Outter;
					}
					countR=0;
					countC=0;
					countCr=0;
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");		
		}
		System.out.println(sb.toString());
	}
}