import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int L;
	static int C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		String[] pw = br.readLine().split(" ");
		Arrays.sort(pw);
		List<String> out = new LinkedList<>();
		L = Integer.parseInt(input[0]); //L개로 구성된 암호
		C = Integer.parseInt(input[1]); //사용된 단어 개수
		
		com(pw, out, 0, L, 0, 0);
	}
	//n: 자음수, m: 모음수
	public static void com(String[] pw, List<String> out, int idx, int L, int n, int m) {
		if(L==0) {
			if(n<2 || m<1) return;
			StringBuilder sb = new StringBuilder();
			for(String s : out) sb.append(s);
			System.out.println(sb);
			return;
		}
		if(idx == C) return;
		
		out.add(pw[idx]);
		if("aeiou".contains(pw[idx])) com(pw, out, idx+1, L-1, n, m+1);
		else com(pw, out, idx+1, L-1, n+1, m);
		out.remove(out.size()-1);
		
		com(pw, out, idx+1, L, n, m);
	}
}