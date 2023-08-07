import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int tc=1; tc<=10; tc++) {
            int N = Integer.parseInt(bf.readLine());
            List<Integer> building = new ArrayList<>(N+4);
             
            building.add(0);
            building.add(0);
            String[] arr = bf.readLine().split(" ");
            for(String s : arr) building.add(Integer.parseInt(s));
            building.add(0);
            building.add(0);
             
            int sum = 0;
            for(int i=2; i<N+2; i++) {
                int max = Math.max(Math.max(building.get(i-2), building.get(i-1)),Math.max(building.get(i+1), building.get(i+2)));
                if(building.get(i)-max > 0) sum += building.get(i)-max;
            }
             
            System.out.println("#"+tc+ " " + sum);
        }
    }
}