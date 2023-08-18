import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] map = new int[100001];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int subin = Integer.parseInt(input[0]);
		int sis = Integer.parseInt(input[1]);
		Queue<Integer> q = new LinkedList<>();
		if(subin == sis) {
			System.out.println(0);
			return;
		}
			
		map[subin] = 1;
		map[sis] = 2;
		q.offer(subin);
		
		int time=0;
		int size=1;
		Outer: while(!q.isEmpty()) {
			time++;
			for(int i=0; i<size; i++) {
				int now = q.poll();
				if(now-1>=0 && map[now-1]!=1) {
					if(map[now-1]==2) break Outer;
					q.offer(now-1);
					map[now-1] = 1;
				}
				if(now+1<=100000 && map[now+1]!=1) {
					if(map[now+1]==2) break Outer;
					q.offer(now+1);
					map[now+1] = 1;
				}
				if(2*now <=100000 && map[2*now]!=1) {
					if(map[2*now]==2) break Outer;
					q.offer(2*now);
					map[2*now] = 1;
				}
			}
			size = q.size();
		}
		System.out.println(time);
	}
}