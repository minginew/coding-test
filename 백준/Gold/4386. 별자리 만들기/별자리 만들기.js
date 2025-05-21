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
    this.heapifyUp(this.heap.length - 1);
  }

  shift() {
    if (this.heap.length === 1) {
      return this.heap.pop();
    }

    let smallerEle = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown(0);
    return smallerEle;
  }
}
let idx = 0;
const N = Number(input[idx++]);
const adjArr = Array.from({ length: N }, () => new Array());
const coordinate = Array.from({ length: N }, () => null);
const visit = Array.from({ length: N }, () => false);

for (let i = 0; i < N; i++) {
  coordinate[i] = input[idx++].split(" ").map(Number);
}

for (let i = 0; i < N; i++) {
  for (let j = i + 1; j < N; j++) {
    let [x1, y1] = coordinate[i];
    let [x2, y2] = coordinate[j];
    let dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    dist = Math.round(dist * 100) / 100;
    adjArr[i].push([j, dist]);
    adjArr[j].push([i, dist]);
  }
}

const pq = new MinHeap();
let cost = 0;
let pick = 0;
pq.heap.push([0, 0]);

while (pq.heap.length > 0) {
  if (pick === N) break;
  let [curr, w1] = pq.shift();
  if (visit[curr]) continue;
  visit[curr] = true;
  cost += w1;
  pick++;
  for (let i = 0; i < adjArr[curr].length; i++) {
    let [next, w2] = adjArr[curr][i];
    if (visit[next]) continue;
    pq.push([next, w2]);
  }
}
console.log(cost);
