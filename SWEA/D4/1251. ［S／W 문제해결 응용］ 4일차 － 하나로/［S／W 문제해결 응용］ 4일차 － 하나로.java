import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
	static class Edge{
		int ed;
		double w;
		
		public Edge(int ed, double w2) {
			super();
			this.ed = ed;
			this.w = w2;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] inX = br.readLine().split(" ");
			String[] inY = br.readLine().split(" ");
			int[] x = new int[N];
			int[] y = new int[N];
			double E = Double.parseDouble(br.readLine());
			
			List<Edge>[] list = new ArrayList[N];
			boolean[] visit = new boolean[N];
			
			for(int i=0; i<N; i++) x[i] = Integer.parseInt(inX[i]);
			for(int i=0; i<N; i++) y[i] = Integer.parseInt(inY[i]);
			for(int i=0; i<N; i++) list[i] = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					double w = E*(Math.pow(x[i]-x[j], 2) + Math.pow(y[i]-y[j], 2));
					list[i].add(new Edge(j, w));
				}
			}
			
			PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					return Double.compare(o1.w, o2.w);
				}
			});
			
			pq.addAll(list[0]);
			visit[0] = true;
			
			int cnt = 1;
			double sum = 0;
			while(cnt!=N) {
				Edge e = pq.poll();
				
				if(visit[e.ed]) continue;
				pq.addAll(list[e.ed]);
				visit[e.ed] = true;
				sum += e.w;
				cnt++;
				if(cnt==N) break;
			}
			sb.append("#").append(t).append(" ").append(Math.round(sum)).append("\n");
		}
		System.out.println(sb);
	}
}