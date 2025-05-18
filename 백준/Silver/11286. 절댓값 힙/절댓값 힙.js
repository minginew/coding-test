const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class AbsHeap {
  constructor() {
    this.heap = [];
  }

  shift() {
    if (this.heap.length == 1) {
      return this.heap.pop();
    }
    let smallerEle = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown(0);
    return smallerEle;
  }

  push(ele) {
    this.heap.push(ele);
    this.heapifyUp(this.heap.length - 1);
  }

  heapifyUp(index) {
    let parentIdx = Math.floor((index - 1) / 2);

    if (
      parentIdx >= 0 &&
      (this.heap[parentIdx][0] > this.heap[index][0] ||
        (this.heap[parentIdx][0] === this.heap[index][0] &&
          this.heap[parentIdx][1] > this.heap[index][1]))
    ) {
      [this.heap[parentIdx], this.heap[index]] = [
        this.heap[index],
        this.heap[parentIdx],
      ];
      this.heapifyUp(parentIdx);
    }
  }

  heapifyDown(index) {
    let leftChildIdx = 2 * index + 1;
    let rightChildIdx = 2 * index + 2;
    let smallerIdx = index;
    let length = this.heap.length;
    if (
      leftChildIdx < length &&
      (this.heap[leftChildIdx][0] < this.heap[smallerIdx][0] ||
        (this.heap[leftChildIdx][0] === this.heap[smallerIdx][0] &&
          this.heap[leftChildIdx][1] < this.heap[smallerIdx][1]))
    ) {
      smallerIdx = leftChildIdx;
    }

    if (
      rightChildIdx < length &&
      (this.heap[rightChildIdx][0] < this.heap[smallerIdx][0] ||
        (this.heap[rightChildIdx][0] === this.heap[smallerIdx][0] &&
          this.heap[rightChildIdx][1] < this.heap[smallerIdx][1]))
    ) {
      smallerIdx = rightChildIdx;
    }

    if (smallerIdx !== index) {
      [this.heap[smallerIdx], this.heap[index]] = [
        this.heap[index],
        this.heap[smallerIdx],
      ];

      this.heapifyDown(smallerIdx);
    }
  }
}
let idx = 0;
let N = Number(input[idx++]);
let arr = [];
for (let i = 0; i < N; i++) arr.push(Number(input[idx++]));
let pq = new AbsHeap();
let answer = [];
for (let i = 0; i < N; i++) {
  let num = arr[i];
  if (num == 0) {
    if (pq.heap.length === 0) answer.push(0);
    else answer.push(pq.shift()[1]);
  } else {
    pq.push([Math.abs(num), num]);
  }
}
console.log(answer.join("\n"));
