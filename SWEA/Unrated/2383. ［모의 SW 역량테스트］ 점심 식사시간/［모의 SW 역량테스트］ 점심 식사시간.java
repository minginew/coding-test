import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
 
class Solution {
    static List<Integer[]> peopleToStair;
    static List<Integer[]> stairLocation;
    static List<Integer> dt1;
    static List<Integer> dt2;
    static Queue<Integer> stand1;
    static Queue<Integer> stand2;
    static Queue<Integer> stair1;
    static Queue<Integer> stair2;
    static int time;
    static int min = Integer.MAX_VALUE;
 
    public static void main(String args[]) throws Exception {
 
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int test_case = 1; test_case <= T; test_case++) {
            peopleToStair = new LinkedList<>(); // {계단1까지 소요시간, 계단2까지 소요시간}
            stairLocation = new LinkedList<>(); // {계단의 행, 계단의 열}
            dt1 = new LinkedList<>();
            dt2 = new LinkedList<>();
            stand1 = new LinkedList<>();
            stand2 = new LinkedList<>();
            stair1 = new LinkedList<>();
            stair2 = new LinkedList<>();
 
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            for (int r = 0; r < N; r++) { // 맵 초기화와 동시에 계단 좌표 초기화! + 계단 사이즈
                for (int c = 0; c < N; c++) {
                    map[r][c] = sc.nextInt();
                    if (map[r][c] >= 2) {
                        stairLocation.add(new Integer[] { r, c, map[r][c] });
                    }
                }
            }
            for (int r = 0; r < N; r++) { // {계단 1까지 가는데 걸리는시간, 계단 2까지 가는데 걸리는 시간}
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == 1) {
                        peopleToStair.add(distance(r, c, stairLocation));
                    }
                }
            }
            choiceStair(0);
            System.out.println("#" + test_case +" "+min);
            min = Integer.MAX_VALUE;
        }
    }
    static Integer[] distance(int r, int c, List<Integer[]> stLocation) {// 좌표를 받고
        int d1 = Math.abs(r - stLocation.get(0)[0]) + Math.abs(c - stLocation.get(0)[1]);
        int d2 = Math.abs(r - stLocation.get(1)[0]) + Math.abs(c - stLocation.get(1)[1]);
        return new Integer[] { d1, d2 };
    }
    static void choiceStair(int index) {
        if (dt1.size() + dt2.size() == peopleToStair.size()) {
            LinkedList<Integer> list1 = new LinkedList<>();
            LinkedList<Integer> list2 = new LinkedList<>();
            list1.addAll(dt1);
            list2.addAll(dt2);
            Collections.sort(list1, Comparator.naturalOrder());
            Collections.sort(list2, Comparator.naturalOrder());
            stand1 = list1;
            stand2 = list2;
            timer();
            return;
        }
        if (index < peopleToStair.size()) {
            dt1.add(peopleToStair.get(index)[0]);
            choiceStair(index + 1);
            dt1.remove(dt1.size() - 1);
 
            dt2.add(peopleToStair.get(index)[1]);
            choiceStair(index + 1);
            dt2.remove(dt2.size() - 1);
        }
    }
    static void timer() { // 총 이동시간 stand : map대기 stair : 계단대기
        int count1 = 0;
        int count2 = 0;
        int size1 = stairLocation.get(0)[2];
        int size2 = stairLocation.get(1)[2];
 
        while (!(stand1.isEmpty() && stair1.isEmpty())) {
                count1++;
                while (!stair1.isEmpty() && stair1.peek() == count1) stair1.poll();
                while (!stand1.isEmpty() && stair1.size() < 3 && stand1.peek() + 1 <= count1) {
                    int temp = stand1.poll() + 1;
                    if (temp <= count1) stair1.offer(count1 + size1);
                }
             
        }
 
        while (!(stand2.isEmpty() && stair2.isEmpty())) {
                count2++;
                while (!stair2.isEmpty() && stair2.peek() == count2) stair2.poll();
                while (!stand2.isEmpty() && stair2.size() < 3  && stand2.peek() + 1 <= count2) {
                    int temp = stand2.poll() + 1;
                    if (temp <= count2) stair2.offer(count2 + size2);
                }
        }
        if (min > Math.max(count1, count2)) min = Math.max(count1, count2);
    }
}