import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] in1 = br.readLine().split("");
		String[] in2 = br.readLine().split("");
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];
		int[] target = new int[N];
		for(int i=0; i<N; i++) {
			arr1[i] = arr2[i] = Integer.parseInt(in1[i]);
			target[i] = Integer.parseInt(in2[i]);
		}
		arr2[0] = (arr1[0]+1)%2;
		arr2[1] = (arr1[1]+1)%2;

		int count1 = 0;
		int count2 = 1;
		for(int i=0; i<N-1; i++) {
			if(arr1[i]!=target[i]) {
				arr1[i] = (arr1[i]+1)%2;
				arr1[i+1] = (arr1[i+1]+1)%2;
				if(i<N-2) arr1[i+2] = (arr1[i+2]+1)%2;
				count1++;
			}
			if(arr2[i]!=target[i]) {
				arr2[i] = (arr2[i]+1)%2;
				arr2[i+1] = (arr2[i+1]+1)%2;
				if(i<N-2) arr2[i+2] = (arr2[i+2]+1)%2;
				count2++;
			}
			
		}
		
		int ans = -1;
		if(arr1[N-1] == target[N-1]) ans = count1;
		else if(arr2[N-1] == target[N-1]) ans = count2;
		System.out.println(ans);
		
 	}
}