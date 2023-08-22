import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine()); //첫 번째
			String[] code = br.readLine().split(" ");//두 번째
			int M = Integer.parseInt(br.readLine());//세 번째
			String[] cmd1 = br.readLine().substring(2).split("I ");//네 번째
			
			Solution sol = new Solution();
			LinkedList list = sol.new LinkedList();
			
			for(int i=0; i<N; i++) list.insert(code[i]);
			
			for(int i=0; i<M; i++) {
				String[] cmd2 = cmd1[i].split(" ");
				int idx = Integer.parseInt(cmd2[0]);
				for(int j=2; j<cmd2.length; j++) {
					if(list.size>idx) {
						list.insMid(idx++, cmd2[j]);//중간삽입
					}else {
						list.insert(cmd2[j]);//끝에 삽입
					}
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
	
	class LinkedList{
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