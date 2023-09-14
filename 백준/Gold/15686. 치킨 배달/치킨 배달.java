import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int N;
	static int M;
	static int minChickenTotalD = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		int[][] map = new int[N][N];
		List<int[]> chicken = new LinkedList<>();
		List<int[]> home = new LinkedList<>();
		List<int[]> out = new LinkedList<>();
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				int value = Integer.parseInt(row[c]);
				map[r][c] = value;
				if(value == 1) home.add(new int[] {r,c});
				else if(value == 2) chicken.add(new int[] {r,c});
			}
		}//맵 세팅 + 치킨집 위치 세팅 + 집 위치 세팅
		
		com(chicken, home, out, 0, M);
		System.out.println(minChickenTotalD);
	}
	
	public static void com(List<int[]> chicken, List<int[]> home, List<int[]> out, int idx, int M) {
		if(M==0) {//M개의 치킨집 조합을 out 리스트에 담음
			int total = 0;
			for(int i=0; i<home.size(); i++) {
				int chickenD = Integer.MAX_VALUE;
				for(int j=0; j<out.size(); j++){
					int D = Math.abs(home.get(i)[0]-out.get(j)[0]) + Math.abs(home.get(i)[1]-out.get(j)[1]);
					if(chickenD > D) chickenD = D;
				}
				total += chickenD;
			}
			if(total < minChickenTotalD) minChickenTotalD = total;
		}
		if(idx==chicken.size()) return;
		out.add(chicken.get(idx));
		com(chicken, home, out, idx+1, M-1);
		out.remove(out.size()-1);
		
		com(chicken, home, out, idx+1, M);
	}
}