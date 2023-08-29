import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int Min = -1;
    public static void main(String[] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        List<Integer> A = new LinkedList<>();
        List<Integer> B = new LinkedList<>();
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[] person = new int[N];
        String[] P = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            person[i] = Integer.parseInt(P[i]);
        }
        for(int r=0; r<N; r++){
            String[] link = br.readLine().split(" ");
            for(int c=1; c<=Integer.parseInt(link[0]); c++){
                int col = Integer.parseInt(link[c]);
                map[r][col-1] = 1;
            }
        }
        reg(map,person,A,B,0);
        System.out.println(Min);


    }
    static void reg(int[][] map, int[] person, List<Integer> A, List<Integer> B, int n){
        if(n==N){
            if (A.size()==0 || B.size()==0) return;
            Queue<Integer> qA = new LinkedList<>();
            Queue<Integer> qB = new LinkedList<>();
            Set<Integer> checkA = new HashSet<>();
            Set<Integer> checkB = new HashSet<>();
            qA.offer(A.get(0));
            qB.offer(B.get(0));
            checkA.add(A.get(0));
            checkB.add(B.get(0));

            while (!qA.isEmpty()){
                int area = qA.poll();
                for(int i=0; i<A.size(); i++){
                    if (map[area][A.get(i)] == 1){
                        if(!checkA.contains(A.get(i))) qA.offer(A.get(i));
                        checkA.add(A.get(i));
                    }
                }
            }
            while (!qB.isEmpty()){
                int area = qB.poll();
                for(int i=0; i<B.size(); i++){
                    if (map[area][B.get(i)] == 1){
                        if(!checkB.contains(B.get(i))) qB.offer(B.get(i));
                        checkB.add(B.get(i));
                    }
                }
            }
            if(A.size()!=checkA.size() || B.size()!=checkB.size()) return;

            int aSum = 0;
            int bSum = 0;
            for(int i=0; i<A.size(); i++) aSum += person[A.get(i)];
            for(int i=0; i<B.size(); i++) bSum += person[B.get(i)];
            int diff = Math.abs(aSum-bSum);
            if(Min == -1 || Min>diff) Min = diff;
            return;
        }
        A.add(n);
        reg(map, person, A, B, n+1);
        A.remove(A.size()-1);

        B.add(n);
        reg(map, person, A, B, n+1);
        B.remove(B.size()-1);
    }
}