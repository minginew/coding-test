import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t=1; t<=T; t++) {
            System.out.print("#" + t + " ");
            int N = sc.nextInt();

            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());

            for(int i=0; i<N; i++) {
                int n = sc.nextInt();
                if(n==1){
                    n = sc.nextInt();
                    pq.add(n);
                } else {
                    if(!pq.isEmpty())
                        System.out.print(pq.poll() + " ");
                    else 
                        System.out.print("-1 ");
                }
            }
            System.out.println();
        }
    }
}