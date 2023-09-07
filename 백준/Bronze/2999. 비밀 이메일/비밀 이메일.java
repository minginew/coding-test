import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split("");
		int len = str.length;
		int R=0;
		int C=0;
		for(int i=10; i>0; i--) {
			if((len%i) != 0) continue;
			if (R<Math.min(len/i, i)) {
				C = Math.max(len/i, i);
				R = Math.min(len/i, i);
			}
		}
		for(int c=0; c<R; c++) {
			for(int r=c; r<R*C; r+=R) {
				sb.append(str[r]);
			}
		}
		System.out.println(sb);
	}
}