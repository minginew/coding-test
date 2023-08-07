import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int cross = 1;
		int[] numCount = new int[10];
		
		for(int i=0; i<3; i++) cross *= Integer.parseInt(bf.readLine());

		String[] numArr = (""+cross).split("");
		
		for(String st : numArr) numCount[Integer.parseInt(st)] += 1;
		
		
		for(int n : numCount) System.out.println(n);
	
	}
}