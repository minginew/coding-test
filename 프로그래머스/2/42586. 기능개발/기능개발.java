import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] progDays = new int[progresses.length];
        Queue<Integer> q = new LinkedList<>();
        
        // 각 작업이 완료되는데 걸리는 날짜배열
        for(int i=0; i<progDays.length; i++){
            int prog = progresses[i];
            int speed = speeds[i];
            int progDay = (100-prog)/speed;
            if((100-prog)%speed != 0) progDay += 1;
            
            progDays[i] = progDay;
        }
        
        //동시에 완료되는 작업들 카운트하기
        int day = progDays[0];
        int progCount = 0;
        for(int i=0; i<progDays.length; i++){
            if(progDays[i] <= day) progCount++;
            else{
                day = progDays[i];
                q.offer(progCount);
                progCount = 1;
            }
            
            
            if(i == progDays.length-1) q.offer(progCount);
        }
        
        //큐를 배열에 옮기기
        int[] answer = new int[q.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = q.poll();
        }
          
        return answer;
    }
}