import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		String[] weiIn = br.readLine().split(" ");
		int trNum = Integer.parseInt(in[0]); //트럭수
		int brLen = Integer.parseInt(in[1]); //다리길이
		int maxWei = Integer.parseInt(in[2]); //최대하중

		Queue<Integer> truck = new LinkedList<>();
		Queue<int[]> bridge = new LinkedList<>();
		for(String s : weiIn) truck.offer(Integer.parseInt(s));
		
		
		int time = 0;
		int weiNow = 0;
		while(!truck.isEmpty() || !bridge.isEmpty()) {
			time++;
			if(!bridge.isEmpty() && bridge.peek()[0]==time) {
				weiNow -= bridge.poll()[1];
			}
			if(bridge.size()<brLen && !truck.isEmpty() && weiNow+truck.peek() <= maxWei) {
				int trWei = truck.poll();
				weiNow += trWei;
				bridge.offer(new int[] {time+brLen, trWei});
			}
		}
		System.out.println(time);
	}
	
}