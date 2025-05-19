const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
function swap(arr, idx1, idx2) {
  [arr[idx1], arr[idx2]] = [arr[idx2], arr[idx1]];
}
class MinHeap {
  constructor() {
    this.heap = [];
  }

  heapifyUp(index) {
    let parentIdx = Math.trunc((index - 1) / 2);
    if (parentIdx >= 0 && this.heap[parentIdx][2] > this.heap[index][2]) {
      swap(this.heap, parentIdx, index);
      this.heapifyUp(parentIdx);
    }
  }
  heapifyDown(index) {
    let length = this.heap.length;
    let leftChildIdx = 2 * index + 1;
    let rightChildIdx = 2 * index + 2;
    let smallerIdx = index;

    if (
      leftChildIdx < length &&
      this.heap[leftChildIdx][2] < this.heap[smallerIdx][2]
    ) {
      smallerIdx = leftChildIdx;
    }
    if (
      rightChildIdx < length &&
      this.heap[rightChildIdx][2] < this.heap[smallerIdx][2]
    ) {
      smallerIdx = rightChildIdx;
    }
    if (smallerIdx !== index) {
      swap(this.heap, index, smallerIdx);
      this.heapifyDown(smallerIdx);
    }
  }

  push(ele) {
    this.heap.push(ele);
    this.heapifyUp(this.heap.length - 1);
  }
  shift() {
    if (this.heap.length === 1) {
      return this.heap.pop();
    }
    let removeEle = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown(0);
    return removeEle;
  }
}

let idx = 0;
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];
const [N, M] = input[idx++].split(" ").map(Number);
const [sr, sc, er, ec] = input[idx++].split(" ").map(Number);
const map = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
const visit = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => false)
);
const dist = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => 1000000)
);

for (let r = 0; r < N; r++) {
  let row = input[idx++].split("");
  for (let c = 0; c < M; c++) {
    map[r][c] = row[c];
  }
}

const pq = new MinHeap();
pq.push([sr-1, sc-1, 0]);
dist[sr-1][sc-1] = 0;
while (pq.heap.length > 0) {
  let [r, c, k] = pq.shift();
  visit[r][c] = true;
  for (let d = 0; d < 4; d++) {
    let nr = r + dr[d];
    let nc = c + dc[d];
    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
    else if (map[nr][nc] === "0") {
      if (!visit[nr][nc] && dist[nr][nc] > k) {
        pq.push([nr, nc, k]);
        dist[nr][nc] = k;
      }
    } else if (map[nr][nc] === "1" || map[nr][nc] === "#") {
      if (!visit[nr][nc] && dist[nr][nc] > k + 1) {
        pq.push([nr, nc, k + 1]);
        dist[nr][nc] = k + 1;
      }
    }
  }
}

console.log(dist[er - 1][ec - 1]);
