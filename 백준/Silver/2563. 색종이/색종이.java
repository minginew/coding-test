import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] paper = new int[100][100]; //도화지
        int N = Integer.parseInt(br.readLine());

        for(int n=0; n<N; n++){ //색종이 수
            String[] input = br.readLine().split(" ");
            int r = Integer.parseInt(input[1]); //색종이가 도화지 아래로 부터 떨어진 거리 (즉, 이 색종이가 시작하는 행)
            int c = Integer.parseInt(input[0]); //색종이가 도화지 옆으로 부터 떨어진 거리 (즉, 이 색종이가 시작하는 열)
            
            // 해당 지점으로부터 10칸씩 1을 채운다. (색종이 길이가 10이니까!) 
            for(int i=r; i<r+10; i++){
                for(int j=c; j<c+10; j++){
                    paper[i][j] = 1; // 이 1의 의미는 길이가 1인 색종이의 넓이, 겹치는 지점 넓이도 결국 1로 카운팅 되므로 신경 안써도 됨 
                }
            }
        }
        int area = 0;
        for(int r=0; r<paper.length; r++){ 
            for (int c=0; c< paper.length; c++){
                if (paper[r][c] == 1) area++; //1인 부분을 모두 더한다. 
            }
        }
        System.out.println(area);
    }
}