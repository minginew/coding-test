import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node {
    int parent = -1;
    int left = -1;
    int right = -1;
    int dep;
}

public class Main {
    static int N;
    static int[] binaryArr;
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new Node[N+1];
        for(int i=0; i<=N; i++) tree[i] = new Node();
        tree[1].parent=1;
        tree[1].dep = 0;
        String[] input = br.readLine().split("");
        binaryArr = new int[input.length+1];
        Stack<Integer> stack = new Stack<>();
        int nodeNum = 1;
        for(int i=0; i<input.length; i++){
            int n = Integer.parseInt(input[i]);
            if(n == 1){
                int node = stack.pop();
                binaryArr[i+1] = node;
                if(!stack.isEmpty()){
                    int parent = stack.peek();
                    tree[node].parent = parent;
                    if(tree[parent].left == -1) tree[parent].left = node;
                    else if(tree[parent].right == -1) tree[parent].right = node;
                }
            }else {
                binaryArr[i+1] = nodeNum;
                stack.push(nodeNum++);
            }
        }

        dfs(1, 0);

        String[] input3 = br.readLine().split(" ");
        int node1 = binaryArr[Integer.parseInt(input3[0])];
        int node2 = binaryArr[Integer.parseInt(input3[1])];

        int lca = LCA(node1, node2);
        for(int i=1; i<binaryArr.length; i++){
            if(binaryArr[i] == lca){
                System.out.print(i+" ");
            }
        }
    }

    public static void dfs(int node, int dep){
        Node curr = tree[node];
        curr.dep = dep;
        if(curr.left != -1) dfs(curr.left, dep+1);
        if(curr.right != -1) dfs(curr.right, dep+1);
    }

    public static int LCA(int n1, int n2){
        while (true){
            if(n1 == n2) return n1;
            if(tree[n1].parent == tree[n2].parent) return tree[n1].parent;
            if(tree[n1].dep == tree[n2].dep){
                n1 = tree[n1].parent;
                n2 = tree[n2].parent;
            } else if(tree[n1].dep > tree[n2].dep){
                n1 = tree[n1].parent;
            } else if (tree[n1].dep < tree[n2].dep) {
                n2 = tree[n2].parent;
            }
        }
    }
}
