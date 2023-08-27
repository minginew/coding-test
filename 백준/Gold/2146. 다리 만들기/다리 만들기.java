import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1,1,0,0}; // 상 하 좌 우
    static int[] dc = {0,0,-1,1};
    static int N;
    static int length =Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] visitIs = new boolean[N][N];
        boolean[][] visitSea = new boolean[N][N];
        Queue<int[]> is = new LinkedList<>();
        Queue<int[]> sea = new LinkedList<>();

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(row[c]);
            }
        }
        int isNum = 1;
        for (int r=0; r<N; r++){
            for (int c=0; c<N; c++) {
                if (map[r][c] != 0 && !visitIs[r][c]) {//다른 위치의 섬을 다른 숫자로 구분짓는 조건문
                    is.offer(new int[]{r, c});
                    visitIs[r][c] = true;
                    map[r][c] = isNum;
                    bfs1(is, map, visitIs, isNum);
                    isNum++; //1번 섬부터 ~ n번 섬까지 색칠한다.
                }
            }
        }

        for (int r=0; r<N; r++){
            for (int c=0; c<N; c++) {
                if (map[r][c] == 0) {
                    sea.offer(new int[]{r, c}); //다리에 길이를 셀 때 사용될 바다의 좌표 큐
                    visitSea[r][c] = true;
                    bfs2(sea,map,visitSea);
                    visitSea = new boolean[N][N];
                }
            }
        }
        System.out.println(length-1); // 처음 시작위치가 중복 (-1)
    }
    static void bfs1(Queue<int[]> is, int[][] map, boolean[][] visitIs, int isNum){ //떨어져있는 섬을 구분하기 위해서 만든 bfs
        int size = is.size();
        while (!is.isEmpty()){
            for (int i=0; i<size; i++){
                int r = is.peek()[0];
                int c = is.poll()[1];

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N || visitIs[nr][nc] || map[nr][nc]==0) continue;
                    map[nr][nc] = isNum;
                    is.offer(new int[] {nr,nc});
                    visitIs[nr][nc] = true;
                }
            }
            size = is.size();
        }
    }

    static void bfs2(Queue<int[]> sea, int[][] map, boolean[][] visitSea){ //해당 바다에 놓을 수 있는 가장 짧은 다리길이 측정
        int total = 0;
        int len = 0;
        int firstIs = -1; // 바다에서 다리를 만들기 시작할 때 제일 먼저 만나는 섬
        int secondIs = -1; // 바다에서 다리를 만들기 시작할 때 두 번째로 만나는 섬
        int size = sea.size();
        while (!sea.isEmpty()){
            len++;
            for (int i=0; i<size; i++){
                int r = sea.peek()[0];
                int c = sea.poll()[1];

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N || visitSea[nr][nc]) continue;
                    if(map[nr][nc]==0){
                        sea.offer(new int[] {nr,nc});
                        visitSea[nr][nc] = true;
                    }else {
                        if(firstIs == -1){ //처음 만나는 섬의 숫자를 입력 (bfs1에서 숫자로 구분 지어놓음)
                            firstIs = map[nr][nc];
                            total += len; //처음 다리를 놓기 시작한 바다 위치부터 첫 번째 섬이랑 이어지는 바다위치까지 거리
                        } else if (firstIs != map[nr][nc] && secondIs == -1) { //두 번째로 만나는 섬의 숫자를 입력
                            secondIs = map[nr][nc];
                            total += len; //처음 다리를 놓기 시작한 바다 위치부터 두 번째 섬이랑 이어지는 바다위치까지 거리
                            //처음 위치가 중복되기 때문에 나중에 -1 해줘야 함
                        }
                    }
                }
                if(secondIs!=-1 && firstIs!=-1 && length > total){ // 두 섬을 모두 만나고, 어떤 바다위치에서 다리를 연결했을 때 가장 짧은지 비교 후 초기화
                    length = total;
                    sea.clear();
                    return;
                }
            }
            size = sea.size();
        }
    }
}