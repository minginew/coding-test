import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,K;
    static int[][] belt, robot;
    static List<Integer> books = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        belt = new int[N][2];
        robot = new int[N][2];

        for(int i=0; i<N; i++){
            belt[i][0] = Integer.parseInt(input2[i]);
        }

        for(int i=N; i<2*N; i++){
            belt[2*N-i-1][1] = Integer.parseInt(input2[i]);
        }

        int count = 0;
        while (true){
            count++;
            belt = spin(belt);
            robot = spin(robot);
            robot[N-1][0] = 0;
            move();

            if(belt[0][0] != 0){
                robot[0][0] = 1;
                belt[0][0]--;
            }

            int breakBelt = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<2; j++){
                    if(belt[i][j] == 0) breakBelt++;
                }
            }
            if(breakBelt >= K){
                System.out.println(count);
                return;
            }
        }


    }

    public static int[][] spin (int[][] arr){
        int[][] newArr = new int[N][2];
        for(int j=0; j<2; j++){
            for(int i=0; i<N; i++){
                if(j==0 && i!=N-1){
                    newArr[i+1][0] = arr[i][0];
                } else if (j==1 && i!=0) {
                    newArr[i-1][1] = arr[i][1];
                }
            }
        }
        newArr[0][0] = arr[0][1];
        newArr[N-1][1] = arr[N-1][0];
        return newArr;
    }

    public static void move (){
      for(int i=N-2; i>=0; i--){
          if(robot[i][0] == 0) continue;
          if(belt[i+1][0] > 0 && robot[i+1][0] == 0){
              robot[i+1][0] = 1;
              robot[i][0] = 0;
              belt[i+1][0]--;
          }
      }
      robot[N-1][0] = 0;
    }
}
