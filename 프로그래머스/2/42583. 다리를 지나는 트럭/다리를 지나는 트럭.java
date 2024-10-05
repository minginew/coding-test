import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> bridge = new LinkedList<>(); //{트럭무게, 나갈 수 있는시간}
        Queue<Integer> truck = new LinkedList<>(); 
        for(int w : truck_weights) truck.offer(w);
        
        
        int time = 1;
        int bridge_weight = 0;
        while(!truck.isEmpty()){
            if(!bridge.isEmpty() && time == bridge.peek()[1]){
                bridge_weight -= bridge.poll()[0];
            }
            
            int truck_weight = truck.peek();
            if(weight >= (bridge_weight + truck_weight)){
                truck.poll();
                bridge_weight += truck_weight;
                bridge.offer(new int[] {truck_weight, time + bridge_length});
                time++;
            }else {
                time++;
            }
        }
        
        while(!bridge.isEmpty()){
            time = bridge.poll()[1];
        }
        
        
        return time;
    }
}