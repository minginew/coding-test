import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int N,M,T;
    static int x,d,k;
    static List<List<Integer>> plates;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);
        plates = new ArrayList<>();

        for(int i=0; i<N; i++){
            plates.add(new LinkedList<>());
            String[] input2 = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                int num = Integer.parseInt(input2[j]);
                plates.get(i).add(num);
            }
        }

        for(int t=0; t<T; t++){
            String[] input3 = br.readLine().split(" ");
            x = Integer.parseInt(input3[0]);
            d = Integer.parseInt(input3[1]);
            k = Integer.parseInt(input3[2]);
            if(d==0) k=M-k;
            spin();
            boolean isAdj = adjRemove();
            if(!isAdj) isNotAdj();
        }

        int sum = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sum += plates.get(i).get(j);
            }
        }
        System.out.println(sum);
    }

    public static void spin(){
        for(int i=0; i<N; i++){
            if((i+1)%x != 0) continue;
            for(int j=0; j<M; j++){
                if(j==k) break;
                int num = plates.get(i).get(j);
                plates.get(i).add(num);
            }
            for(int j=0; j<k; j++){
                plates.get(i).remove(0);
            }
        }
    }

    public static boolean adjRemove(){
        List<List<Integer>> newPlates = new ArrayList<>();
        for(int i=0; i<N; i++){
            newPlates.add(new LinkedList<>());
            for(int j=0; j<M; j++){
                int num = plates.get(i).get(j);
                newPlates.get(i).add(num);
            }
        }

        boolean isAdj = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int num = plates.get(i).get(j);
                int top = plates.get((i-1+N)%N).get(j);
                int bottom = plates.get((i+1+N)%N).get(j);
                int left = plates.get(i).get((j-1+M)%M);
                int light = plates.get(i).get((j+1+M)%M);

                if(i != 0 && top != 0 && num == top) {
                    newPlates.get((i-1+N)%N).set(j, 0);
                    isAdj = true;
                }
                if(i != N-1 && bottom != 0 && num == bottom) {
                    newPlates.get((i+1+N)%N).set(j, 0);
                    isAdj = true;

                }
                if(left !=0 && num == left) {
                    newPlates.get(i).set((j-1+M)%M, 0);
                    isAdj = true;
                }
                if(light != 0 && num == light) {
                    newPlates.get(i).set((j+1+M)%M, 0);
                    isAdj = true;
                }
            }
        }
        plates = newPlates;
        return isAdj;
    }

    public static void isNotAdj(){
        double min = 0;
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int num = plates.get(i).get(j);
                if(num == 0) continue;
                min += num;
                count++;
            }
        }

        min /= count;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int num = plates.get(i).get(j);
                if(num == 0) continue;
                if(num > min) plates.get(i).set(j, num-1);
                else if(num < min) plates.get(i).set(j, num+1);
            }
        }
    }
}