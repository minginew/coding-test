import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[10];
        int preNum = -1;
        
        for(int num : arr){
            if(preNum != num){
                preNum = num;
                q.offer(num);
            }
        } 
        
        
        int[] answer = new int[q.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = q.poll();
        }
        


        return answer;
    }
}