import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int N,M;
	static int[] root,rank;
	
	static class Edge{
		int st;
		int ed;
		int w;
		
		public Edge(int st, int ed, int w) {
			super();
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in1 = br.readLine().split(" ");
		N = Integer.parseInt(in1[0]);
		M = Integer.parseInt(in1[1]);
		
		root = new int[N+1];
		rank = new int[N+1];
		
		PriorityQueue<Edge> edge = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		
		for(int i=0; i<M; i++) {
			String[] in2 = br.readLine().split(" ");
			int st = Integer.parseInt(in2[0]);
			int ed = Integer.parseInt(in2[1]);
			int w = Integer.parseInt(in2[2]);
			edge.offer(new Edge(st, ed, w));
		}
		
		makeSet();
		int link = 0;
		int cost = 0;
		while(!edge.isEmpty()) {
			if(link == N-2) break;
			Edge e = edge.poll();
			int x = e.st;
			int y = e.ed;
			int w = e.w;

			int findX = findSet(x);
			int findY = findSet(y);
			
			if(findX==findY) continue;
			
			union(findX, findY);
			link++;
			cost += w;
		}
		System.out.println(cost);
	}
	
	static void makeSet() {
		for(int i=0; i<root.length; i++)
			root[i] = i;
	}
	
	static int findSet(int x) {
		if(x == root[x]) return x;
		return root[x] = findSet(root[x]);
	}
	
	static void union(int x, int y) { //집합의 대표가 다를경우! 
		if(rank[x] < rank[y]) {
			root[x] = y;
		}else { //x가 속한 집합이 더 높을경우
			root[y] = x;
			if(rank[x]==rank[y]) {
				rank[x]++;
			}
		}
		
	}
	
}