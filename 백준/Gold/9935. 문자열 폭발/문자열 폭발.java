import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        char[] charArr = br.readLine().toCharArray();
        char[] explosion = br.readLine().toCharArray();
        char lastChar = explosion[explosion.length-1];
        Stack<Character> stack = new Stack<>();
        Stack<Character> temp = new Stack<>();

        for(int i=0; i<charArr.length; i++){
            char c = charArr[i];
            if(c != lastChar) stack.push(c);
            else {
                stack.push(c);
                boolean isExplosion = true;
                for(int j=explosion.length-1; j>=0; j--){
                    if(stack.isEmpty()){
                        isExplosion = false;
                        break;
                    }
                    char top = stack.pop();
                    if(explosion[j] == top){
                        temp.push(top);
                    }else {
                        temp.push(top);
                        isExplosion = false;
                        break;
                    }
                }
                if(!isExplosion) while (!temp.isEmpty()) stack.push(temp.pop());
                else temp.clear();
            }
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else{
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()){
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }
    }
}
