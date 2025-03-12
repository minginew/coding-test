import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[] parent;
    static List<Integer> card;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        parent = new int[M];
        card = new ArrayList<>();

        String[] input2 = br.readLine().split(" ");
        for(int i=0; i<M; i++){
            parent[i] = i;
            card.add(Integer.parseInt(input2[i]));
        }
        card.sort(Comparator.naturalOrder());

        String[] input3 = br.readLine().split(" ");
        for(int i=0; i<K; i++) {
            int st = -1;
            int ed = M;
            int target = Integer.parseInt(input3[i]);

            while (st+1 < ed) {
                int mid = (st + ed) / 2;
                if (card.get(mid) <= target) {
                    st = mid;
                } else {
                    ed = mid;
                }
            }
            int nextPick = find(ed);
            sb.append(card.get(nextPick)).append("\n");
            if(nextPick < M-1) union(nextPick, nextPick+1);
        }
        System.out.println(sb);
    }
    
    public static int find(int node){
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void union(int v1, int v2){
        int f1 = find(v1);
        int f2 = find(v2);
        if(f1 == f2) return;
        if(f1 < f2){
            parent[f1] = f2;
        }else {
            parent[f2] = f1;
        }
    }
}
