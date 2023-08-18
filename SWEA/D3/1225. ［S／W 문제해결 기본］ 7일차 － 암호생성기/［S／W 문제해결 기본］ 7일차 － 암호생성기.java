import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=10; t++) {
			Integer.parseInt(br.readLine()); //테스트 케이스 번호
			String[] pw = br.readLine().split(" ");
			Queue<Integer> q = new LinkedList<>();
			for(String s:pw) q.offer(Integer.parseInt(s));
			int dec = 1;
			while(true) {
				if(q.peek()-dec<=0) {
					q.poll();
					q.offer(0);
					break;
				}
				q.offer(q.poll()-dec);
				dec++;
				if(dec==6) dec=1;
			}
			sb.append("#").append(t).append(" ");
			while(!q.isEmpty()) sb.append(q.poll()).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}