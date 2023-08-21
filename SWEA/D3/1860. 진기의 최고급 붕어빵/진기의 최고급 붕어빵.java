import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		outer:for(int t=1; t<=T; t++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]); //사람 수 
			int M = Integer.parseInt(input[1]); //붕어빵 만드는데 걸린 시간
			int K = Integer.parseInt(input[2]); //M초당 만드는 붕어빵 수
			
			input = br.readLine().split(" "); 
			List<Integer> waitList = new LinkedList<>();
			for(String s:input) waitList.add(Integer.parseInt(s));//손님도착 시간
			Collections.sort(waitList, Comparator.naturalOrder());//정렬
			Queue<Integer> wait = (Queue<Integer>) waitList;
	
			int time=0;
			int fish=0;
			while(!wait.isEmpty()) {
				if(time!=0) {
					if((time%M)==0) fish+=K;
				}
				while(!wait.isEmpty()&&time==wait.peek()) {
					wait.poll();
					fish--;
					if(fish<0) {
						sb.append("#").append(t).append(" ").append("Impossible").append("\n");
						continue outer;
					}
				}
				time++;
			}
			sb.append("#").append(t).append(" ").append("Possible").append("\n");
 		}
		System.out.println(sb.toString());
		
	}
}