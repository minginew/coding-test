const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class MinHeap {
  constructor() {
    this.heap = [];
  }
  swap(arr, idx1, idx2) {
    [arr[idx1], arr[idx2]] = [arr[idx2], arr[idx1]];
  }
  heapifyUp(index) {
    let parentIdx = Math.trunc((index - 1) / 2);
    if (parentIdx >= 0 && this.heap[parentIdx][1] > this.heap[index][1]) {
      this.swap(this.heap, parentIdx, index);
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
      this.heap[leftChildIdx][1] < this.heap[smallerIdx][1]
    ) {
      smallerIdx = leftChildIdx;
    }
    if (
      rightChildIdx < length &&
      this.heap[rightChildIdx][1] < this.heap[smallerIdx][1]
    ) {
      smallerIdx = rightChildIdx;
    }

    if (smallerIdx !== index) {
      this.swap(this.heap, smallerIdx, index);
      this.heapifyDown(smallerIdx);
    }
  }

  push(ele) {
    this.heap.push(ele);
    this.heapifyUp(0);
  }

  shift() {
    if (this.heap.length === 1) {
      return this.heap.pop();
    }
      
    let smallerEle = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown(this.heap.length - 1);

    return smallerEle;
  }
}

let idx = 0;
const T = Number(input[idx++]);
const answer = [];
for (let t = 0; t < T; t++) {
  const [K, M, P] = input[idx++].split(" ").map(Number);
  const adjArr = Array.from({ length: M + 1 }, () => []);
  const degree = Array.from({ length: M + 1 }, () => 0);
  const strahler = Array.from({ length: M + 1 }, () => -1);
  const count = Array.from({ length: M + 1 }, () => 0);
  const visit = Array.from({ length: M + 1 }, () => false);
  const pq = new MinHeap();
  for (let p = 0; p < P; p++) {
    let [st, ed] = input[idx++].split(" ").map(Number);
    adjArr[st].push(ed);
    degree[ed]++;
  }

  for (let i = 1; i <= M; i++) {
    if (degree[i] === 0) {
      strahler[i] = 1;
      count[i] = 1;
      pq.push([i, 1]);
    }
  }

  while (pq.heap.length > 0) {
    let [node, num] = pq.shift();
    visit[node] = true;
    if (count[node] > 1) {
      num++;
      strahler[node]++;
    }
    for (let i = 0; i < adjArr[node].length; i++) {
      let next = adjArr[node][i];
      if (visit[next]) continue;

      if (strahler[next] < num) {
        strahler[next] = num;
        count[next] = 1;
      } else if (strahler[next] === num) count[next]++;

      if (--degree[next] === 0) {
        pq.push([next, strahler[next]]);
      }
    }
  }
  answer.push(K + " " + strahler.sort((a, b) => a - b)[M]);
}
console.log(answer.join("\n"));
