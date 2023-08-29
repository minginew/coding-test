import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder tc = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringBuilder sb = new StringBuilder();
			String[] in = br.readLine().split(" ");
			int N = Integer.parseInt(in[0]); //읽고싶은 비트 수
			int num = Integer.parseInt(in[1]); //수
			while(true) {
				if(num<2) {//마지막 비트
					sb.append(num%2);
					break;
				}
				sb.append(num%2); //나머지를 StringBuilder에 추가
				num /= 2; //2로 나눈몫으로 초기화
			}
			sb.reverse(); //순차적으로 쌓았기 때문에 reverse
			int len = sb.toString().length(); //비트의 길이
			if(len<N) { //작성된 비트의 길이보다 읽고싶은 개수가 더 크면 off 
				tc.append("#").append(t).append(" ").append("OFF").append("\n");
				continue;
			}
			String check = sb.toString().substring(len-N); //읽고싶은 비트수 만큼 뒤에서 자른다.
			//0이 포함되지 않으면 on, 포함되면 off
			if(check.indexOf('0') == -1) tc.append("#").append(t).append(" ").append("ON").append("\n");
			else tc.append("#").append(t).append(" ").append("OFF").append("\n");
		}
		System.out.println(tc.toString());
	}
}