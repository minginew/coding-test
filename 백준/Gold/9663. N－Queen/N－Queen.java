import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static int N;
	static int[] arr;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N]; 
		nQueen(0);
		System.out.println(count);
		
	}
	static boolean posible(int col) {
		for(int i=0; i<col; i++) {
			if(arr[i]==arr[col]) return false;
			else if(Math.abs(i-col) == Math.abs(arr[i] - arr[col])) return false;
		}
		return true;
	}
	
	static void nQueen(int depth) {
		if(depth==N) {
			count++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			arr[depth] = i;
			if(posible(depth)) {
				nQueen(depth+1);
			}
		}
	}
}