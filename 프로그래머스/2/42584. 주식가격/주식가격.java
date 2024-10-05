import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<prices.length; i++){
            int target = prices[i];
            int increase = 0;
            for(int j = i+1; j<prices.length; j++){
                if(target > prices[j]){
                    increase++;
                    break;
                }else increase++;
            }
            q.offer(increase);
        }
        
        
        int[] answer = new int[prices.length];
        for(int i=0; i<answer.length; i++){
            answer[i] = q.poll();
        }
        
        return answer;
    }
}