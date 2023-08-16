import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String[] input = br.readLine().split(" ");
			switch(input[0]) {
			case "push": 
				list.add(input[1]);
				break;
			case "pop":
				if(list.size()==0) {
					System.out.println(-1);
					continue;
				}
				System.out.println(list.get(0));
				list.remove(0);
				break;
			case "size":
				System.out.println(list.size());
				break;
			case "empty":
				if(list.size()==0) {
					System.out.println(1);
					continue;
				}
				System.out.println(0);
				break;
			case "front":
				if(list.size()==0) {
					System.out.println(-1);
					continue;
				}
				System.out.println(list.get(0));
				break;
			case "back":
				if(list.size()==0) {
					System.out.println(-1);
					continue;
				}
				System.out.println(list.get(list.size()-1));
				break;
			}
		}
	}
}