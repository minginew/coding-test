import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = (int)Math.pow(2, Integer.parseInt(input[0]));
		int row = Integer.parseInt(input[1]);
		int col = Integer.parseInt(input[2]);
		reg(0, 0, N, row, col);
		System.out.println(n);

	}
	
	static void reg(int R, int C, int N, int row, int col) {
		if(N==1) return;
		for(int r=R; r<=R+N/2; r+=N/2) {
			for(int c=C; c<=C+N/2; c+=N/2) {
				if((row >= r && row < r+N/2) && (col >= c && col < c+N/2)) {
					reg(r, c, N/2, row, col);
					return;
				}else {
					n += (N/2)*(N/2);
				}
			}
		}
	}
}