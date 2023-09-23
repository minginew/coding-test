import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static int N,M,K,maxKill;
    static List<int[]> archer,enemy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] position = br.readLine().split(" ");
        N = Integer.parseInt(position[0]);
        M = Integer.parseInt(position[1]);
        K = Integer.parseInt(position[2]);
        enemy = new ArrayList<>();

        for(int r=0; r<N; r++) {
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++) {
                int n = Integer.parseInt(row[c]);
                if(n==1){
                    enemy.add(new int[] {r,c});
                }
            }
        }


        boolean[] pick = new boolean[M];
        res(0, 0, pick);
        System.out.println(maxKill);
    }

    public static void res(int n, int c, boolean[] pick) {
        if(c==3) {
            archer = new ArrayList<>();
            for(int i=0; i<pick.length; i++) {
                if(!pick[i]) continue;
                archer.add(new int[] {N,i});
            }
            defense();
            return;
        }
        if(n==M) return;

        pick[n] = true;
        res(n+1, c+1, pick);
        pick[n] = false;
        res(n+1, c, pick);
    }

    public static void defense() {
        int count = 0;
        int move = 0;
        boolean[] kill = new boolean[enemy.size()];
        boolean[] out = new boolean[enemy.size()];

        while(count < enemy.size()) {
            HashSet<Integer> killSet = new HashSet<>();
            for(int i=0; i<archer.size(); i++) {
                int idx = -1;
                int minD = Integer.MAX_VALUE;
                int xA = archer.get(i)[0];
                int yA = archer.get(i)[1];

                for(int j=0; j<enemy.size(); j++) {
                    if(kill[j] || out[j]) continue;
                    int x = enemy.get(j)[0];
                    int y = enemy.get(j)[1];
                    int D = Math.abs(x+move-xA) + Math.abs(y-yA) ;

                    if(x + move >= N) {
                        out[j] = true;
                        count++;
                        continue;
                    } else if (D>K) continue;
                    
                    if(minD > D) {
                        minD = D;
                        idx = j;
                    }else if(minD == D && enemy.get(idx)[1]>y) {
                        idx = j;
                    }
                }
                if (idx == -1) continue;
                if(minD <= K) {
                    if(!killSet.contains(idx)) count++;
                    killSet.add(idx);
                }
            }

            for(int k : killSet){
                kill[k] = true;
            }
            move++;
        }
        int killCount = 0;
        for(int i=0; i<kill.length; i++) {
            if(kill[i]) killCount++;
        }
        if(maxKill < killCount) maxKill = killCount;
    }
}