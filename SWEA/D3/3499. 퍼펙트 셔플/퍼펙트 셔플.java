import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			Queue<String> q = new LinkedList<>();
			int N = Integer.parseInt(br.readLine());
			int halfLen = (N%2)==0 ? N/2 : N/2+1; //두번째 카드덱의 시작 위치
			String[] card = br.readLine().split(" "); 
			
			for(int i=0; i<halfLen; i++) {
				q.offer(card[i]);//첫번째 카드덱에서 한장
				if((N%2)==1 && i==halfLen-1) continue; //총 카드덱의 길이가 홀수일 때 + 첫번째 카드덱을 다 소진
				q.offer(card[halfLen+i]);//두번째 카드덱에서 한장
			}
			sb.append("#").append(t).append(" ");
			while(!q.isEmpty()) sb.append(q.poll()).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}