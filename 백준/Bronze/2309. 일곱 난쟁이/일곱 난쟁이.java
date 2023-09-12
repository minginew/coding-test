import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[9];
		for(int i=0; i<9; i++) arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		int left = 0;
		int right = arr.length-1;
		int sum = 0;
		for(int i=0; i<arr.length; i++) sum += arr[i];
		int twoSum = sum - 100;
		
		while(left<right) {
			int temp = arr[left]+arr[right];
			if(twoSum > temp) {
				left++;
			}
			else if(twoSum < temp) {
				right--;
			}else {
				arr[left] = 0;
				arr[right] = 0;
				break;
			}
		}
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 0) continue;
			System.out.println(arr[i]);
		}
	}

}