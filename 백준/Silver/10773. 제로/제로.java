import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> nums = new Stack<>();
		List<Integer> list = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		
		for(int i=0; i<N; i++) list.add(Integer.parseInt(br.readLine()));
		for(int n : list) {
			if(n==0 && !nums.isEmpty()) {
				nums.pop(); 
				continue;
			}
			nums.push(n);
		}
		for(int n : nums) sum+=n;
		System.out.println(sum);
	}
}