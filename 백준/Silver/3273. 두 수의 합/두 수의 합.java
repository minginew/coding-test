import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int target;
		
		for(int i=0; i<N; i++) arr[i] = sc.nextInt();
		
		
		target = sc.nextInt();
		int count = 0;
		Arrays.sort(arr);
		
		int i=0;
		int j=N-1;
		while(i<j) {
			if(target > arr[i]+arr[j]) {
				i++;
				continue;
			}
			if(target < arr[i]+arr[j]) {
				j--;
				continue;
			}
			if(target == arr[i]+arr[j]) {
				count++;
				i++;
				continue;
			}
		}
		System.out.println(count);
	}
}