import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int min;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        int[][] map = new int[N][N];
        List<Integer> start = new LinkedList<>();
        List<Integer> link = new LinkedList<>();
        for(int r=0; r<N; r++){
            String[] input = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(input[c]);
            }
        }
        reg(start,link,map,0);
        System.out.println(min);

    }
    static void reg(List<Integer> start, List<Integer> link, int[][] map, int n){//n은 사람번호
        if(n==N){
            int S=0;
            int L=0;
            for(int i=0; i<start.size(); i++){
                for(int j=0; j<start.size(); j++){
                    S+=map[start.get(i)][start.get(j)];
                }
            }
            for(int i=0; i<link.size(); i++){
                for(int j=0; j<link.size(); j++){
                    L+=map[link.get(i)][link.get(j)];
                }
            }
            int diff = Math.abs(S-L);
            if(min>diff) min = diff;
            return;
        }
        if(start.size()<N/2){
            start.add(n);
            reg(start,link,map,n+1);
            start.remove(start.size()-1);
        }
        if(link.size()<N/2){
            link.add(n);
            reg(start,link,map,n+1);
            link.remove(link.size()-1);
        }
    }
}