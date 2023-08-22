import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine()); //첫 번째
			String[] code = br.readLine().split(" ");//두 번째
			int M = Integer.parseInt(br.readLine());//세 번째
			String[] cmd1 = br.readLine().split(" ");//네 번째
			Queue<String> q = new LinkedList<>();//명령어를 큐에 담는다.
			for(String s: cmd1) q.offer(s);
			
			Solution sol = new Solution();
			LinkList list = sol.new LinkList();
			
			for(int i=0; i<N; i++) list.insert(code[i]);
			
			while(!q.isEmpty()) {
				String method = q.poll();
				int idx = Integer.parseInt(q.poll());
				int n = Integer.parseInt(q.poll());
				

				if(method.equals("I")) {
					for(int i=0; i<n; i++) {
						if(idx<list.size) {
							list.insMid(idx++, q.poll());
						}else {
							list.insert(q.poll());
						}
					}
				}else if(method.equals("D")) {
					if(list.size <= idx) continue;
					list.del(idx, n);
				}
			}
			String[] str = list.print();
			sb.append("#").append(t).append(" ");
			for(int i=0; i<10; i++) sb.append(str[i]).append(" ");//10개만 출력
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	class Node{ //노드
		String data;
		Node next;
		
		Node(){}
		Node(String data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	class LinkList{
		private Node head = new Node();
		private Node tail = head;
		int size = 0;
		
		void insert(String data) {//제일 뒤에 삽입
			if(head.data == null) {
				head.data = data;
			}
			else {
				Node current = new Node();
				tail.next = current;
				tail = current;
				current.data = data;
			}
			size++;
		}
		
		void insMid(int idx, String data) {//중간 삽입 
			if(idx==0) head = new Node(data, head);
			else {
				Node current = head;
				Node newNode = new Node();
				for(int i=0; i<idx-1; i++) {
					current = current.next;
				}
				newNode.next = current.next;
				current.next = newNode;
				newNode.data=data;
			}
			size++;
		}
		
		void del(int idx, int n) {//중간 삽입 
					Node current = head;
					for(int i=0; i<idx-1; i++) {
						current = current.next;
					}
					if(current == null) return;
					Node node = current;
					for(int i=0; i<n; i++) {
						node = node.next;
						size--;
					}
					if(idx==0) {
						head = node;
						return;
					}
					current.next = node.next;
		}
		
		String[] print() {//list를 String 배열로 반환
			StringBuilder sb = new StringBuilder();
			Node current = head;
			while(current.next != null) {
				sb.append(current.data).append(" ");
				current = current.next;
			}
			return sb.toString().split(" ");
		}
	}
}