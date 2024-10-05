import java.util.*;
class Solution {
    boolean solution(String s) {
        String[] arr = s.split("");
        
        int left = 0;
        int light = 0;
        boolean answer = true;
        
        for(int i=0; i<arr.length; i++){
            if(light > left){
                answer = false;
                break;
            }else{
                if(arr[i].equals("(")) left++;
                else if(arr[i].equals(")")) light++;
            }
        }
        
        if(left != light) answer = false;


        return answer;
    }
}