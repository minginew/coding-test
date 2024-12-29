import java.util.*;

class Solution {
    static String begin;
    static String target;
    static String[] words;
    static boolean[] visit;
    
    static int beginIdx = -1;
    static int targetIdx = -1;
    static int minCount = Integer.MAX_VALUE;
    
    public int solution(String param1, String param2, String[] param3) {
        begin = param1;
        target = param2;
        words = param3;
        visit = new boolean[words.length];

        // 재귀
        // 글자 넣기
        // 1개만 다른 단어 찾기
        // 여러개 있을경우 재귀의 분기점이 생긴다.
        // 있을경우 재귀 진행
        // 없을경우 영원히 배제
        // End Point 타켓에 도착?
        
        recursion(begin, 0, visit);
        if(minCount == Integer.MAX_VALUE) minCount = 0;
        return minCount;
    }
    
    public static boolean wordChecker(String str1, String str2){
        String[] arr1 = str1.split("");
        String[] arr2 = str2.split("");
        
        int unequal = 0;
        for(int i=0; i<arr1.length; i++){
            if(!arr1[i].equals(arr2[i])) unequal++;
            if(unequal > 1) return false;
        }
        return true;
    }
    
    public static void recursion (String word, int count, boolean[] visit){
        if(word.equals(target)){
            if(minCount > count) minCount = count;
        }
        
        for(int i=0; i<words.length; i++){
            if(visit[i] || word.equals(words[i])) continue;
            
            boolean correct = wordChecker(word, words[i]);
            if(correct){
                visit[i] = true;
                recursion(words[i], count+1, visit);
                visit[i] = false;
            }
        }
    }
}