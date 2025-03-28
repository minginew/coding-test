const fs = require("fs");
const { tmpdir } = require("os");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Heap {
  constructor() {
    this.heap = [];
    this.size = 0;
  }
  getLeftChildIdx(index) {
    return 2 * index + 1;
  }
  getRightChildIdx(index) {
    return 2 * index + 2;
  }
  getParentIdx(index) {
    return Math.floor((index - 1) / 2);
  }

  insert(pay) {
    let insertNode = pay;
    this.heap.push(insertNode);
    if (this.heap.length == 1) return;
    this.heapifyUp();
  }

  heapifyUp() {
    let index = this.heap.length - 1;
    let target = this.heap[index];

    while (index > 0) {
      let parentIndex = this.getParentIdx(index);
      if (target > this.heap[parentIndex]) {
        this.heap[index] = this.heap[parentIndex];
        index = parentIndex;
      } else {
        break;
      }
    }
    this.heap[index] = target;
  }

  remove() {
    if (this.heap.length == 0) return undefined;
    else if (this.heap.length == 1) return this.heap.pop();
    let removeNode = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.heapifyDown();
    return removeNode;
  }

  heapifyDown() {
    let index = 0;
    let target = this.heap[index];
    let length = this.heap.length;

    while (this.getLeftChildIdx(index) < length) {
      let leftChildIdx = this.getLeftChildIdx(index);
      let rightChildIdx = this.getRightChildIdx(index);
      let largerIdx = index;

      if (leftChildIdx < length) {
        if (this.heap[largerIdx] < this.heap[leftChildIdx]) {
          largerIdx = leftChildIdx;
        }
      }

      if (rightChildIdx < length) {
        if (this.heap[largerIdx] < this.heap[rightChildIdx]) {
          largerIdx = rightChildIdx;
        }
      }

      if (largerIdx == index) break;
      this.heap[index] = this.heap[largerIdx];
      this.heap[largerIdx] = target;
      index = largerIdx;
    }
  }
}

class PriorityQueue extends Heap {
  constructor() {
    super();
  }

  offer = (pay) => this.insert(pay);
  poll = () => this.remove();
  isEmpty = () => this.heap.length <= 0;
}

let pq = new PriorityQueue();
let [N, ...works] = input;
N = Number(N);
if (N == 0) {
  console.log(0);
  return;
}
works = works.map((work) => {
  work = work.split(" ");
  return [Number(work[0]), Number(work[1])];
});
works.sort((a, b) => b[1] - a[1]);

let total = 0;
let day = works[0][1];
for (let i = 0; i < N; i++) {
  if (day == works[i][1]) {
    pq.offer(works[i][0]);
  } else {
    while (works[i][1] < day) {
      if (!pq.isEmpty()) total += pq.poll();
      day--;
    }
    pq.offer(works[i][0]);
  }
}
while (day > 0) {
  if (!pq.isEmpty()) total += pq.poll();
  day--;
}
console.log(total);
