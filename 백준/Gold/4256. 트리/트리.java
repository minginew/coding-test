import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int left, light;
    public Node(int left, int light) {
        this.left = left;
        this.light = light;
    }
}
public class Main {
    static int T,N;
    static boolean[] visit;
    static int[] preorder, inorder, inorderIdx;
    static Node[] tree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            String[] input1 = br.readLine().split(" ");
            String[] input2 = br.readLine().split(" ");
            visit = new boolean[N+1];
            tree = new Node[N+1];
            preorder = new int[N+1];
            inorder = new int[N+1];
            inorderIdx = new int[N+1];
            for(int i=1; i<=N; i++){
                preorder[i] = Integer.parseInt(input1[i-1]);
                inorder[i] = Integer.parseInt(input2[i-1]);
                inorderIdx[inorder[i]] = i;
            }

            int root = preorder[1];
            StringBuilder sb = new StringBuilder();
            dfs(1);
            postorder(root, sb);
            System.out.println(sb);

        }
    }
    public static void dfs(int n){
        if(n > N) return;
        int node = preorder[n];
        int idx = inorderIdx[node];
        int leftChild = 0;
        int lightChild = 0;
        int leftNum = 0;
        int lightNum = 0;

        visit[node] = true;
        for(int i=idx-1; i>0; i--){
            if(!visit[inorder[i]]) leftNum++;
            else break;
        }
        for(int i=idx+1; i<=N; i++){
            if(!visit[inorder[i]]) lightNum++;
            else break;
        }

        if(leftNum > 0){
            leftChild = preorder[n+1];
        }
        if(lightNum > 0){
            lightChild  = preorder[n+leftNum+1];
        }

        tree[node] = new Node(leftChild, lightChild);
        dfs(n+1);
    }
    public static void postorder(int nodeNum, StringBuilder sb){
        Node node = tree[nodeNum];
        if(node.left != 0) postorder(node.left, sb);
        if(node.light != 0) postorder(node.light, sb);
        sb.append(nodeNum).append(" ");
    }
}