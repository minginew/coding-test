import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static int N,M,blockNum;
    static int MaxCount = Integer.MAX_VALUE;
    static List<Integer> pick;
    static int[][] map;
    static ArrayList<int[]> cctvList;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        cctvList = new ArrayList<>();

        int cctvNum = 0;
        for(int r=0; r<N; r++){
            String[] col = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                int n = Integer.parseInt(col[c]);
                map[r][c] = n;
                if(n==6) blockNum++;
                else if (n!=0) {
                    cctvList.add(new int[] {r,c,n});
                    cctvNum++;
                }
            }
        }

        pick = new ArrayList<>();
        permutation(cctvNum,0);
        System.out.println(MaxCount-blockNum);

    }
    public static void permutation(int n, int m){
        if(m == n){
            int count = 0;
            int[][] newMap = new int[N][M];
            for(int i=0; i<cctvList.size(); i++){
                int[] cctv = cctvList.get(i);
                int r = cctv[0];
                int c = cctv[1];
                int t = cctv[2];
                int d = pick.get(i);
                int[] dir = dirCheck(t,d);
                for(int j=0; j<dir.length; j++){
                    for(int h=0; h<Math.max(N,M); h++){
                        int nr = r+h*dr[dir[j]];
                        int nc = c+h*dc[dir[j]];
                        if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc]==6) break;
                        newMap[nr][nc] = 1;
                    }
                }
            }
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(newMap[r][c] == 0) count++;
                }
            }
            if(MaxCount > count) MaxCount = count;
            return;
        }
        for(int i=0; i<4; i++){
            if(cctvList.get(m)[2] == 2 && i>1) break;
            else if (cctvList.get(m)[2] == 5 && i!=0) break;
            pick.add(i);
            permutation(n,m+1);
            pick.remove(pick.size()-1);
        }
    }

    public static int[] dirCheck(int type, int d){
        if(type == 1){
            return new int[] {d};
        }else if (type == 2){
            return new int[] {0+d, 2+d};
        }else if (type == 3){
            return new int[] {(0+d)%4, (1+d)%4};
        }else if (type == 4){
            return new int[] {(0+d)%4, (1+d)%4, (2+d)%4};
        }else {
            return new int[] {0,1,2,3};
        }
    }
}