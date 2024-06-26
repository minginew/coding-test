# [Platinum V] 행성 터널 - 2887

[문제 링크](https://www.acmicpc.net/problem/2887)

### 성능 요약

메모리: 94560 KB, 시간: 1384 ms

### 분류

그래프 이론, 최소 스패닝 트리, 정렬

### 제출 일자

2024년 4월 10일 11:43:48

### 문제 설명

<p>
	때는 2040년, 이민혁은 우주에 자신만의 왕국을 만들었다. 왕국은 N개의 행성으로 이루어져 있다. 민혁이는 이 행성을 효율적으로 지배하기 위해서 행성을 연결하는 터널을 만들려고 한다.</p>

<p>
	행성은 3차원 좌표위의 한 점으로 생각하면 된다. 두 행성 A(x<sub>A</sub>, y<sub>A</sub>, z<sub>A</sub>)와 B(x<sub>B</sub>, y<sub>B</sub>, z<sub>B</sub>)를 터널로 연결할 때 드는 비용은 min(|x<sub>A</sub>-x<sub>B</sub>|, |y<sub>A</sub>-y<sub>B</sub>|, |z<sub>A</sub>-z<sub>B</sub>|)이다.</p>

<p>
	민혁이는 터널을 총 N-1개 건설해서 모든 행성이 서로 연결되게 하려고 한다. 이때, 모든 행성을 터널로 연결하는데 필요한 최소 비용을 구하는 프로그램을 작성하시오.</p>

### 입력

 <p>
	첫째 줄에 행성의 개수 N이 주어진다. (1 ≤ N ≤ 100,000) 다음 N개 줄에는 각 행성의 x, y, z좌표가 주어진다. 좌표는 -10<sup>9</sup>보다 크거나 같고, 10<sup>9</sup>보다 작거나 같은 정수이다. 한 위치에 행성이 두 개 이상 있는 경우는 없다. </p>

### 출력

 <p>
	첫째 줄에 모든 행성을 터널로 연결하는데 필요한 최소 비용을 출력한다.</p>

### 해결과정

<p>
 1. Queue에 들어갈 불필요한 Edge를 줄이기 위해서 Queue에 방문했던 Edge 정보를 기록해봤지만 유의미한 변화는 없었다.
 <br/>
 2. 문제에서 min(diffX, diffY, diffZ)를 비용으로 정의하며 이를 가지고 Edge를 만든다면 최대 N(N-1)개가 만들어진다. (N <= 10만)
 <br/>
 3. 그래서 x,y,z를 각각 나누어서 MST를 만들면, 모든 정점에 대해 자신에게 이어지는 각 축에대한 비용을 가진 Edge를 모두 알 수 있으므로 30만개로 줄일 수 있다.
 <br/>
 4. Prim은 정점 기준이므로 간선을 선택할때 간선의 start 정점은 이미 방문한 정점이어야 한다. -> 30만개 간선을 한번에 넣었다가 잘못된 값이 나왔다. 
 <br/>
 
</p>
