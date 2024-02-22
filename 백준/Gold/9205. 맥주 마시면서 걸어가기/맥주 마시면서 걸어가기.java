import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int storeNum;
    static String possible;
    static int[] start;
    static int[] target;
    static List<int[]> store;
    static boolean[] visit;

    public static void main(String[] args) throws Exception{
        // 1. 좌표는 전체를 활용한다.
        // 2. 50보 이동할 때 마다, 맥주를 소모한다. -> 애초에 좌표를 50으로 잡아도 됨
        // 3. 현재 맥주가 0이면 멈춘다.
        // 4. 편의점은 1로 둔다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            storeNum = Integer.parseInt(br.readLine());
            possible = "sad";
            start = new int[2];
            target = new int[2];
            store = new ArrayList<>();
            visit = new boolean[storeNum];

            for(int i=0; i<=storeNum+1; i++){
                String[] temp = br.readLine().split(" ");
                if(i==0){
                    start[0] = Integer.parseInt(temp[0]);
                    start[1] = Integer.parseInt(temp[1]);
                }else if(i==storeNum+1){
                    target[0] = Integer.parseInt(temp[0]);
                    target[1] = Integer.parseInt(temp[1]);
                }
                else{
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    store.add(new int[] {x,y});
                }
            }
            bfs();
            System.out.println(possible);
        }


    }

    static void bfs(){
        Queue<int[]> curr = new LinkedList<>();
        curr.add(new int[] {start[0],start[1],1000});

        while (!curr.isEmpty()){
            int r = curr.peek()[0];
            int c = curr.peek()[1];
            int m = curr.poll()[2];

            int targetDiff = Math.abs(r-target[0]) + Math.abs(c-target[1]);
            if(m>=targetDiff){
                possible = "happy";
                return;
            }
            for(int i=0; i<store.size(); i++){
                if(visit[i]) continue;
                int[] next = store.get(i);
                int diff = Math.abs(r-next[0]) + Math.abs(c-next[1]);
                if(m>=diff){
                    if(m == diff){
                        curr.add(new int[] {next[0],next[1],1000});
                        visit[i] = true;
                    }else{
                        curr.add(new int[] {next[0],next[1],1000+(m-diff)%50});
                        visit[i] = true;
                    }
                }
            }
        }


    }
}