import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            sb.append("#").append(t).append(" ");
            String sentence = "";
            int[] index = new int[6];
            for(int i=0; i<5; i++){
                sentence += br.readLine();
                index[i+1] = sentence.length(); //다음 단어가 몇번째 인덱스에서 시작하는지 나타내는 배열
                //[0]: 첫번 째 단어 시작위치, [1]: 두번 째 단어 시작위치..
            }
            int size1 = index[1]; //첫번 째 단어 끝위치 + 1
            int size2 = index[2]; //두번 째 단어 끝위치 + 1
            int size3 = index[3]; //세번 째 단어 끝위치 + 1
            int size4 = index[4]; //네번 째 단어 끝위치 + 1
            int size5 = index[5]; //다섯번 째 단어 끝위치 + 1
            while(true){
                if(index[0]<size1) sb.append(sentence.charAt(index[0]++));
                if(index[1]<size2) sb.append(sentence.charAt(index[1]++));
                if(index[2]<size3) sb.append(sentence.charAt(index[2]++));
                if(index[3]<size4) sb.append(sentence.charAt(index[3]++));
                if(index[4]<size5) sb.append(sentence.charAt(index[4]++));
                if(index[0]==size1 && index[1]==size2 && index[2]==size3 && index[3]==size4 && index[4]==size5) break;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}