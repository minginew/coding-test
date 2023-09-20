import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
	static int N,M;
	static int[] root,rank;
	
	static class Edge{
		int i;
		int j;
		int cost;
		
		public Edge(int i, int j, int cost) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		List<Edge> list = new ArrayList<>();
		root = new int[N+1];
		rank = new int[N+1];
		for(int i=0; i<=N; i++) makeSet(i);
		
		for(int i=0; i<M; i++) {
			String[] in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]);
			int b = Integer.parseInt(in[1]);
			int c = Integer.parseInt(in[2]);
			list.add(new Edge(a,b,c));
		}
		
		Collections.sort(list, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.cost, o2.cost);
			}
		});
		
		int cost = 0;
		int count = 0;
		for(Edge e : list) {
			int findX = findSet(e.i);
			int findY = findSet(e.j);
			
			if(findX==findY) continue;
			unionSet(findX, findY);
			cost += e.cost;
			count++;
			
			if(count == N-1) break;
		}
		System.out.println(cost);
	}
	
	static void makeSet(int i) {
		root[i] = i;
	}
	
	static int findSet(int x) {
		if(root[x]==x) return x;
		return root[x] = findSet(root[x]);
	}
	
	static void unionSet(int x, int y) {
		if(x<y) root[x] = y;
		else root[y] = x;
		if(x==y) rank[x]++;
	}
}