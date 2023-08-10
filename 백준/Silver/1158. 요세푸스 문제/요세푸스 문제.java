import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int C = Integer.parseInt(input[1]);
		for(int i=1; i<=N; i++) q.offer(i);
		
		sb.append("<");
		int count = 0;
		while(!q.isEmpty()) {
			count++;
			if((count%C) != 0 ) {
				int temp = q.poll();
				q.offer(temp);
			}else {
				if(q.size() != N) sb.append(" ");
				sb.append(q.poll());
				if(!q.isEmpty()) sb.append(",");
			}
		}
		sb.append(">");
		System.out.println(sb.toString());
	}
}