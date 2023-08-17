import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Stack;
public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder  answer  = new StringBuilder();
		for(int t=1; t<=10; t++) { 
			int N = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();
			Stack<Character> stack1 = new Stack<>();
			Stack<Integer> stack2 = new Stack<>();
			
			for(char c:input) { //후위 표현식으로 만드는 과정
				if(c=='+') {
					while(!stack1.isEmpty() && stack1.peek()!='(') {
						sb.append(stack1.pop());
					}
					stack1.push(c);
				}else if(c=='-') {
					while(!stack1.isEmpty() && stack1.peek()!='(') {
						sb.append(stack1.pop());
					}
					stack1.push(c);
				}else if(c=='*') {
					while(!stack1.isEmpty() && stack1.peek()=='*' && stack1.peek()=='/') {
						sb.append(stack1.pop());
					}
					stack1.push(c);
				}else if(c=='/') {
					while(!stack1.isEmpty() && stack1.peek()=='*' && stack1.peek()=='/') {
						sb.append(stack1.pop());
					}
					stack1.push(c);
				}else if(c=='(') {
					stack1.push(c);
				}else if(c==')') {
					while(stack1.peek()!='(') {
						sb.append(stack1.pop());
					}
					stack1.pop();
				}else { //피연산자
					sb.append(c);
				}
			}
			
			char[] postFix = sb.toString().toCharArray(); //후위 표현식
			for(char c : postFix) {
				if(c=='+') {
					int rightNum = stack2.pop();
					int leftNum = stack2.pop();
					stack2.push(leftNum + rightNum);
				}else if(c=='-') {
					int rightNum = stack2.pop();
					int leftNum = stack2.pop();
					stack2.push(leftNum - rightNum);
				}else if(c=='*') {
					int rightNum = stack2.pop();
					int leftNum = stack2.pop();
					stack2.push(leftNum * rightNum);
				}else if(c=='/') {
					int rightNum = stack2.pop();
					int leftNum = stack2.pop();
					stack2.push(leftNum / rightNum);
				}else { 
					stack2.push(Integer.parseInt(String.valueOf(c)));  
				}
			}
			answer.append("#").append(t).append(" ").append(stack2.pop()).append("\n");
		}
		System.out.println(answer.toString());
	}
}