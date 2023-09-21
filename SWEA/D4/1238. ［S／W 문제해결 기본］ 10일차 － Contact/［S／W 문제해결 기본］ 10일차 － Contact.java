import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			
			String[] in = br.readLine().split(" ");
			int N = Integer.parseInt(in[0]);
			int S = Integer.parseInt(in[1]);
			boolean[] visit = new boolean[101];
			Queue<Integer> node = new LinkedList<>();
			List<Integer>[] list = new ArrayList[101];
			for (int i = 0; i < 101; i++) list[i] = new ArrayList<>();

			String[] input = br.readLine().split(" ");
			for(int i=0; i<N; i+=2) {
				int st = Integer.parseInt(input[i]);
				int ed = Integer.parseInt(input[i+1]);
				list[st].add(ed);
			}
			
			node.offer(S);
			visit[S] = true;
			int maxNode = -1; //가장 큰 노드
			int maxDep = -1; //가장 큰 노드의 깊이
			
			int size = 1;
			int depth = 0; //탐색하는 노드의 깊이
			while(!node.isEmpty()) {
				for(int i=0; i<size; i++) {
					int st = node.poll();
					int count = 0; //다음에 연결할 노드 수 카운트
					for(int j=0; j<list[st].size(); j++) {
						int ed = list[st].get(j);
						if(!visit[ed]) {
							visit[ed] = true;
							node.offer(ed);
							count++;
						}
					}
					if(count == 0) {
						if(maxNode<st) {
							maxNode =st;
							maxDep = depth;
						}
						if(maxDep < depth) {
							maxNode =st;
							maxDep = depth;
						}
					}
				}
				size = node.size();
				depth++;
			}
			sb.append("#").append(t).append(" ").append(maxNode).append("\n");
		}
		System.out.println(sb);
	}
}