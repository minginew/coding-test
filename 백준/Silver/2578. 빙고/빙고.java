import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[26][2]; //첫 번째 인덱스는 빙고판 번호, 두 번째 인덱스는 빙고판 번호에 해당하는 좌표
        boolean[][] visit = new boolean[5][5]; //사회자가 부르는 번호의 위치를 표시할 배열
        Queue<Integer> q = new LinkedList<>(); //사회자가 부르는 숫자

        for (int r=0; r<5; r++){ //빙고판 채우기
            String[] row = br.readLine().split(" ");
            for (int c=0; c<5; c++){
                int idx = Integer.parseInt(row[c]);
                map[idx] = new int[] {r,c};
            }
        }

        for (int r=0; r<5; r++){ //사회자가 부르는 숫자 큐에 채우기
            String[] nums = br.readLine().split(" ");
            for (int c=0; c<5; c++){
                q.offer(Integer.parseInt(nums[c]));
            }
        }
        int time = 0;
        while (!q.isEmpty()){ //최소 12개는 되어야 빙고를 외칠 수 있으므로 12개까진 점프
            time++;
            int num = q.poll();
            visit[map[num][0]][map[num][1]] = true;

            if(time>11){
                int bingo = 0;
                for(int r=0; r<5; r++){ //가로검사
                    int count=0;
                    for (int c=0; c<5; c++){
                        if(!visit[r][c]) break;
                        else count++;
                        if (count==5) bingo++;
                    }
                    if(bingo>=3){
                        System.out.println(time);
                        return;
                    }
                }
                for(int c=0; c<5; c++){ //세로검사
                    int count=0;
                    for (int r=0; r<5; r++){
                        if(!visit[r][c]) break;
                        else count++;
                        if (count==5) bingo++;
                    }
                    if(bingo>=3){
                        System.out.println(time);
                        return;
                    }
                }
                for(int r=0,c=0; r<5 && c<5; r++,c++){//좌측대각
                    if(!visit[r][c]) break;
                    if(r==4&&c==4) bingo++;
                    if(bingo>=3){
                        System.out.println(time);
                        return;
                    }
                }
                for(int r=0,c=4; r<5 && c>=0; r++,c--){//우측대각
                    if(!visit[r][c]) break;
                    if(r==4&&c==0) bingo++;
                    if(bingo>=3){
                        System.out.println(time);
                        return;
                    }
                }
            }
        }
    }
}