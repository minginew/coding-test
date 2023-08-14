import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			int tc = Integer.parseInt(br.readLine());
			String target = br.readLine();
			String st = br.readLine();
			
			int count = 0;
			for(int i=0; i<st.length()-1; i++) {
				if(st.startsWith(target,i)) {
					count++;
				}	
			}
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		System.out.println(sb.toString());
	}
}