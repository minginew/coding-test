import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int count = 0;
		while(true) {
			if(str.startsWith("c=")) {				
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else if(str.startsWith("c-")) {
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else if(str.startsWith("dz=")) {
				count++;
				if(str.length()==3) break;
				str = str.substring(3);
			}else if(str.startsWith("d-")) {
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else if(str.startsWith("lj")) {
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else if(str.startsWith("nj")) {
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else if(str.startsWith("s=")) {
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else if(str.startsWith("z=")) {
				count++;
				if(str.length()==2) break;
				str = str.substring(2);
			}else {
				count++;
				if(str.length()==1) break;
				str = str.substring(1);
			}
		}
		System.out.println(count);
	}
}