import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static String root;
    static Map<String, Double> lineage;
    static Map<String, String[]> parent;
    static Map<String, List<String>> childs;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        root = br.readLine();
        lineage = new HashMap<>();
        parent = new HashMap<>();
        childs = new HashMap<>();

        lineage.put(root, 1.0);
        for(int i=0; i<N; i++){
            String[] input2 = br.readLine().split(" ");
            String ch = input2[0];
            String pr1 = input2[1];
            String pr2 = input2[2];

            if(parent.get(ch) == null) parent.put(ch, new String[2]);
            if(childs.get(pr1) == null) childs.put(pr1, new ArrayList<>());
            if(childs.get(pr2) == null) childs.put(pr2, new ArrayList<>());

            parent.get(ch)[0] = pr1;
            parent.get(ch)[1] = pr2;
            childs.get(pr1).add(ch);
            childs.get(pr2).add(ch);
        }
        bfs(root);

        String king = "";
        double maxLineage = 0;
        for(int i=0; i<M; i++){
            String child = br.readLine();
            if(lineage.getOrDefault(child,0.0) > maxLineage){
                maxLineage = lineage.getOrDefault(child,0.0);
                king = child;
            }
        }

        System.out.println(king);
    }
    public static void bfs(String node){
        Queue<String> q = new LinkedList<>();
        Map<String, Boolean> visit = new HashMap<>();
        q.offer(node);
        visit.put(node, true);

        while (!q.isEmpty()){
            String curr = q.poll();
            if(childs.get(curr) == null) continue;
            for(String ch : childs.get(curr)){
                double blood1 = lineage.getOrDefault(parent.get(ch)[0], 0.0);
                double blood2 = lineage.getOrDefault(parent.get(ch)[1], 0.0);
                lineage.put(ch, (blood1/2) + (blood2/2));
                q.offer(ch);
            }
        }
    }
}