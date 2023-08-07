import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(bf.readLine());
			String[] str = bf.readLine().split(" ");
			int[] block = new int[N];
			int max = 0;
			for(int i=0; i<N; i++) block[i] = Integer.parseInt(str[i]); //여기까진 입력값 받아서 배열로 정리
			
			for(int i=0; i<N; i++) {
				int count = 0;
				for(int j=i+1; j<N; j++) {
					if(block[i] <= block[j]) {
						count++;
					}
				}
				if(max < (N-(i+1)-count)) max =(N-(i+1)-count); //배열길이 -(내인덱스+1) -(나보다 크거나 같은 개수)
			}
			System.out.println("#"+ tc + " " + max);
		}
	}
}