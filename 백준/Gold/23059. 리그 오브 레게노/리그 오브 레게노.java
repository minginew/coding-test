import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] degree;
    static boolean[] visit;
    static Map<String, Integer> map;
    static Map<Integer, String> indexMap;
    static List<Integer> adjArr[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        indexMap = new HashMap<>();
        adjArr = new ArrayList[2*N+1];
        degree = new int[2*N+1];
        for(int i=0; i<adjArr.length; i++){
            adjArr[i] = new ArrayList<>();
            degree[i] = -1;
        }

        int index = 0;
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            if(map.get(input[0]) == null){
                map.put(input[0],index);
                indexMap.put(index++, input[0]);
            }
            if(map.get(input[1]) == null){
                map.put(input[1],index);
                indexMap.put(index++, input[1]);
            }

            int st = map.get(input[0]);
            int ed = map.get(input[1]);
            if(degree[st] == -1) degree[st] = 0;
            if(degree[ed] == -1) degree[ed] = 0;
            adjArr[st].add(ed);
            degree[ed]++;
        }

        visit = new boolean[index];
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return indexMap.get(o1[0]).compareTo(indexMap.get(o2[0]));
                return Integer.compare(o1[1],o2[1]);
            }
        });
        for(int i=0; i<degree.length; i++){
            if(degree[i] == 0){
                q.offer(new int[] {i,0});
                visit[i] = true;
            }else if (degree[i] == -1) break;
        }

        int count = 0;
        while (!q.isEmpty()){
            int curr = q.peek()[0];
            int dep = q.poll()[1];
            visit[curr] = true;
            count++;
            sb.append(indexMap.get(curr)).append('\n');
            for(int i=0; i<adjArr[curr].size(); i++){
                int next = adjArr[curr].get(i);
                if(visit[next]) continue;
                degree[next]--;
                if(degree[next] == 0){
                    q.offer(new int[]{next, dep+1});
                }
            }
        }
        if(count == index){
            System.out.println(sb);
        }else {
            System.out.println(-1);
        }
    }
}