import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] in = br.readLine().split(" ");
		double[] Y = new double[N];
		
		for(int i=0; i<N; i++) {
			Y[i] = Double.parseDouble(in[i]);
		}//건물 높이 초기화
		
		int maxCnt = Integer.MIN_VALUE;
		for(int curr=0; curr<N; curr++) {
			int viewCnt = 0;
			for(int left=curr-1; left>=0; left--) {
				boolean possible = true;
				double a = (Y[curr]-Y[left])/(double)(curr-left); //기울기
				double b = Y[curr]; //절편
				//y=ax+b (현재 건물위치를 x=0이라 생각한다.)
				for(int j=curr-1; j>left; j--) {
					int x = j-curr;
					double y = a*x+b;
					if(Y[j] >= y) {
						possible = false;
						break;
					}
				}
				if(possible) viewCnt++;
			}
			
			for(int right=curr+1; right<N; right++) {
				boolean possible = true;
				double a = (Y[curr]-Y[right])/(double)(curr-right); //기울기
				double b = Y[curr]; //절편
				//y=ax+b (현재 건물위치를 x=0이라 생각한다.)
				for(int j=curr+1; j<right; j++) {
					int x = j-curr;
					double y = a*x+b;
					if(Y[j] >= y) {
						possible = false;
						break;
					}
				}
				if(possible) viewCnt++;
			}
			
			if(maxCnt < viewCnt) maxCnt = viewCnt;
		}
		System.out.println(maxCnt);
	}
}