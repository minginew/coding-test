import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		Arrays.sort(arr);
		
		int i = 0;
		int j = arr.length - 1;
		
		int gap = Integer.MAX_VALUE;
		int num1 = 0;
		int num2 = 0;
		
		int temp=0;
		int sum=0;
		while (i < j) {
			sum = arr[i] + arr[j];
			temp = Math.abs(sum);
			if (temp < gap) {
				gap = temp;
				num1 = arr[i];
				num2 = arr[j];
			}
			if (sum > 0)
				j--;
			else
				i++;
		}
		System.out.println(num1 + " " + num2);
	}
}