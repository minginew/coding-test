import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int minCost;
	static int[] cost,plan;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String[] inCost = br.readLine().split(" ");
			String[] inPlan = br.readLine().split(" ");
			cost = new int[inCost.length];
			plan = new int[inPlan.length];
			minCost = Integer.MAX_VALUE;
			for(int i=0; i<cost.length; i++) cost[i] = Integer.parseInt(inCost[i]);
			for(int i=0; i<plan.length; i++) plan[i] = Integer.parseInt(inPlan[i]);
			res(0, 0);
			if(minCost > cost[3]) minCost = cost[3];
			
			sb.append("#").append(t).append(" ").append(minCost).append("\n");
		}
		System.out.println(sb);
	}
	static void res(int n, int totalCost) {
		if(n>=12) {
			if(minCost > totalCost) minCost = totalCost;
			return;
		}
		res(n+1, totalCost+cost[0]*plan[n]);
		res(n+1, totalCost+cost[1]);
		res(n+3, totalCost+cost[2]);
	}
}