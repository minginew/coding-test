import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;
class Solution {
    public String[] solution(String[] strings, int n) {
        List<String> list = new LinkedList<>();
        for(String s : strings) list.add(s);
        //n-1 번 째 글자 (배열기준)
        Collections.sort(list, new Comparator<String>(){
            @Override
			public int compare(String o1, String o2) {
                int sort = Character.compare(o1.charAt(n),o2.charAt(n));
                if(sort==0) sort = o1.compareTo(o2);
				return sort;
			}
        });
        

        return list.toArray(String[]::new);
    }
}