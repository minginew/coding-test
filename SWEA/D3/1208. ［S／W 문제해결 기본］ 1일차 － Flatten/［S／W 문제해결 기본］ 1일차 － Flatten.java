import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1; t<=10; t++) {
			int[] boxArr = new int[100];
			int D = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<boxArr.length; i++) boxArr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(boxArr);
			for(int i=0; i<D; i++) {
				if(boxArr[0]>boxArr[1] || boxArr[98]>boxArr[99]) Arrays.sort(boxArr);
				boxArr[99]--;
				boxArr[0]++;
			}
			if(boxArr[0]>boxArr[1] || boxArr[98]>boxArr[99]) Arrays.sort(boxArr);
			System.out.println("#"+t+" "+(boxArr[99]-boxArr[0]));
		}
	}
}