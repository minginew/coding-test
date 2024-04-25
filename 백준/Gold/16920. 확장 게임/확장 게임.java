import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M,P;
    static int[] S;
    static boolean[] clear;
    static int[] result;
    static Queue<int[]>[] queArr;
    static char[][] map;
    static boolean[][] visit;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        P = Integer.parseInt(input[2]);
        map = new char[N][M];
        visit = new boolean[N][M];
        S = new int[P+1];
        clear = new boolean[P+1];
        result = new int[P+1];
        queArr = new LinkedList[P+1];

        String[] s = br.readLine().split(" ");
        for(int i=0; i<P; i++){
            S[i+1] = Integer.parseInt(s[i]);
            queArr[i+1] = new LinkedList<>();
        }

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split("");
            for(int c=0; c<M; c++){
                map[r][c] = row[c].charAt(0);
                if(map[r][c] == '.' || map[r][c] == '#') continue;
                int player = Character.getNumericValue(map[r][c]);
                queArr[player].offer(new int[] {r,c});
            }
        }

        int target = 1;

        Queue<int[]> q = new LinkedList<>();
        Outter:while (true){
            int count = 0;
            for(boolean c : clear){
                if(c) count++;
                if(count == P) break Outter;
            }

            // 타겟에 맞는 Q넣기
            while (!queArr[target].isEmpty()){
                int r = queArr[target].peek()[0];
                int c = queArr[target].poll()[1];
                q.offer(new int[] {r,c});
                visit[r][c] = true;
            }

            if(q.isEmpty()){
                clear[target] = true;
                target = (target+1)%P;
                if (target == 0) target=P;
                continue;
            }
            // bfs
            bfs(q, target);
            // 타겟 변경
            target = (target+1)%P;
            if (target == 0) target=P;
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c]=='.' || map[r][c]=='#') continue;
                result[map[r][c]-'0']++;
            }
        }

        for(int i=1; i<result.length; i++){
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
        //System.out.println(Arrays.deepToString(map));
    }

    static void bfs(Queue<int[]> q, int target) {

        int size = q.size();
        int move = 0;
        while (!q.isEmpty()){
            if(move == S[target]){
                queArr[target].addAll(q);
                q.clear();
                return;
            }

            for(int i=0; i<size; i++){
                int r = q.peek()[0];
                int c = q.poll()[1];

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr<0 || nr>=N || nc<0 || nc>=M || visit[nr][nc]) continue;
                    if(map[nr][nc] == '.'){
                        q.offer(new int[] {nr,nc});
                        map[nr][nc] = Integer.toString(target).charAt(0);
                        visit[nr][nc] = true;
                    }
                }
            }
            size = q.size();
            move++;
        }
    }
}