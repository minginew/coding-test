import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        
        LinkedList<Integer> largePrior = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>(); // {우선순위, 처음위치}
        
        
        int myPrior = priorities[location];
        
        for(int i=0; i<priorities.length; i++){
            if(priorities[i] > myPrior) largePrior.offer(priorities[i]);
            q.offer(new int[] {priorities[i], i});
        }
        
        Collections.sort(largePrior,Collections.reverseOrder());
        
        int answer = 0;
        while(!largePrior.isEmpty()){
            if(largePrior.peek() == q.peek()[0]){
                largePrior.poll();
                q.poll();
                answer++;
            }else if(largePrior.peek() > q.peek()[0]){
                q.offer(q.poll());
            }
        }
        
        while(true){
            int[] target = q.poll();
            if(target[0] == myPrior){
                answer++;
                if(target[1] == location) break;
            }
        }
        return answer;
    }
}