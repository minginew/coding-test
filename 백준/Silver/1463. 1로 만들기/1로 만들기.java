import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int minCount = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		res(N, 0);
		System.out.println(minCount);
	}
	static void res(int n, int count) {
		if(minCount<count) return;
		if(n==1) {
			if(minCount>count) minCount = count;
			return;
		}
		if(n<=0) return;
		if(n%3==0) res(n/3,count+1);
		if(n%2==0) res(n/2,count+1);
		res(n-1, count+1);
	}
}