import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Edge {
	int i; //섬과
	int j; //섬을
	double w; //잇는데 드는 비용
	
	public Edge(int i, int j, double w) {
		super();
		this.i = i;
		this.j = j;
		this.w = w;
	}
}

public class Solution {
	static int idx;
	static double E;
	static int[] x,y,p;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			idx = Integer.parseInt(br.readLine());
			
			String[] inX = br.readLine().split(" ");
			String[] inY = br.readLine().split(" ");
			x = new int[idx];
			y = new int[idx];
			p = new int[idx];
			E = Double.parseDouble(br.readLine());
			
			for(int i=0; i<idx; i++) x[i] = Integer.parseInt(inX[i]);
			for(int i=0; i<idx; i++) y[i] = Integer.parseInt(inY[i]);
			
			for(int i=0; i<p.length; i++) make(i);
			
			List<Edge> edge = new LinkedList<>();
			for(int i=0; i<idx; i++) {
				for(int j=i+1; j<idx; j++) {
					double w = Math.pow(x[i]-x[j], 2) + Math.pow(y[i]-y[j], 2);
					edge.add(new Edge(i, j, w));
				}
			}
			
			Collections.sort(edge, new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					// TODO Auto-generated method stub
					return Double.compare(o1.w, o2.w);
				}
			});
			
			double cost = 0;
			for(Edge e : edge) {
				int findX = find(e.i);
				int findY = find(e.j);
				
				if(findX != findY) {
					union(findX, findY);
					cost += E*e.w;
				}
			}
			sb.append("#").append(t).append(" ").append((long)Math.round(cost)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void make(int i) {
		p[i] = i;
	}
	public static int find(int x) {
		if(x != p[x]) 
			p[x] = find(p[x]);
		return p[x];
	}
	public static void union(int x, int y) {
		p[y] = x;
	}
}