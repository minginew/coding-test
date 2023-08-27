import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int M;
    public static void main(String[] args) throws Exception {//첫 번째 input 1:북 2:남 3:서 4:동, 두 번째 input 왼쪽으로부터 거리 or 위쪽으로부터 거리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[1]); //행
        M = Integer.parseInt(input[0]); //열
        int round = 2*N + 2*M; //전체 둘레
        int storeNum = Integer.parseInt(br.readLine()); //상점의 수
        Queue<int[]> q = new LinkedList<>(); //상점의 좌표를 담을 큐
        int totalLen = 0; //전체 최단거리의 합

        for(int i=0; i<storeNum; i++){
            String[] store = br.readLine().split(" "); //상점에 대한 인풋
            int dir = Integer.parseInt(store[0]); //상점의 방향(동서남북)
            int dist = Integer.parseInt(store[1]); //상점이 떨어진 거리
            int[] st = coor(dir,dist); //방향과 거리를 넣어주면 해당 상점의 좌표를 반환하는 메서드.
            q.offer(new int[] {st[0],st[1],st[2]}); //구해진 상점의 좌표를 큐에 담는다.
        }

        String[] in = br.readLine().split(" "); //동근쨩의 방향과 거리를 담은 인풋
        int[] dong_geun = coor(Integer.parseInt(in[0]),Integer.parseInt(in[1])); //마찬가지로 메서드를 통해 동근쨩의 좌표 + 방향을 얻음
        while (!q.isEmpty()){//각 상점과 동근이의 거리를 비교하기 위한 while문
            int r=q.peek()[0];
            int c=q.peek()[1];
            int d=q.poll()[2]; //상점의 방향 (동서남북)
            int len = 0;
            if(d+dong_geun[2]==3 || d+dong_geun[2]==7) len = r+c+dong_geun[0]+dong_geun[1]; //동근이와 상점이 마주본다면 모든 좌표를 더해준다.
            else len = Math.abs(r-dong_geun[0])+Math.abs(c-dong_geun[1]); //그게 아니라면 동근이와 상점 각 행과 열의 차이의 절댓값을 더해준다.
            totalLen += Math.min(len,round-len); // 시계방향과 반시계 방향중 더 짧은것을 택하여 전체 최단거리에 넣는다.
        }
        System.out.println(totalLen);
    }
    static int[] coor(int dir, int dist){ //방향과 거리를 인자로 받으면 좌표를 리턴해주는 메서드
        int r=0;
        int c=0;
        switch (dir){
            case 1: //북쪽
                r=0;
                c=dist; //왼쪽으로부터 떨어진 거리
                break;
            case 2: //남쪽
                r=N; //맨 밑
                c=dist; //왼쪽으로부터 떨어진 거리
                break;
            case 3: //서쪽
                r=dist; //위쪽에서 떨어진 거리
                c=0;
                break;
            case 4: //동쪽
                r=dist; //위쪽에서 떨어진 거리
                c=M;
                break;
        }
        return new int[] {r,c,dir};
    }
}