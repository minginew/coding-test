const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let arr = input.splice(1, N).map(Number);

class MaxHeap {
  constructor() {
    this.heap = [];
  }

  heapifyUp(index) {
    let parentIdx = Math.trunc((index - 1) / 2);

    if (parentIdx >= 0 && this.heap[parentIdx] < this.heap[index]) {
      [this.heap[parentIdx], this.heap[index]] = [
        this.heap[index],
        this.heap[parentIdx],
      ];
      this.heapifyUp(parentIdx);
    }
  }

  heapifuDown(index) {
    let length = this.heap.length;
    let leftChildIdx = 2 * index + 1;
    let rightChildIdx = 2 * index + 2;
    let largerIdx = index;

    if (
      leftChildIdx < length &&
      this.heap[largerIdx] < this.heap[leftChildIdx]
    ) {
      largerIdx = leftChildIdx;
    }

    if (
      rightChildIdx < length &&
      this.heap[largerIdx] < this.heap[rightChildIdx]
    ) {
      largerIdx = rightChildIdx;
    }

    if (index !== largerIdx) {
      [this.heap[largerIdx], this.heap[index]] = [
        this.heap[index],
        this.heap[largerIdx],
      ];
      this.heapifuDown(largerIdx);
    }
  }

  push(ele) {
    this.heap.push(ele);
    this.heapifyUp(this.heap.length - 1);
  }

  shift() {
    if (this.heap.length === 0) {
      return;
    }
    const max = this.heap[0];
    const lastIdx = this.heap.length - 1;
    this.heap[0] = this.heap[lastIdx];
    this.heap.pop();
    this.heapifuDown(0);
    return max;
  }
}

const maxHeap = new MaxHeap();
const answer = [];

for (let i = 0; i < N; i++) {
  const x = arr[i];

  if (x !== 0) {
    maxHeap.push(x);
  } else {
    const min = maxHeap.shift() || 0;
    answer.push(min);
  }
}

console.log(answer.join("\n"));
