const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Node {
  constructor(data) {
    this.data = data;
    this.next = null;
  }
}
class Queue {
  constructor() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  isEmpty() {
    if (this.size == 0) return true;
    else return false;
  }

  offer(data) {
    const newNode = new Node(data);
    if (!this.head) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.tail.next = newNode;
      this.tail = newNode;
    }
    this.size++;
  }

  poll() {
    if (!this.head) {
      return null;
    } else {
      const pollNode = this.head;
      this.head = this.head.next;
      this.size--;
      return pollNode;
    }
  }
}

let dr = [-1, 1, 0, 0, 0, 0];
let dc = [0, 0, -1, 1, 0, 0];
let dh = [0, 0, 0, 0, -1, 1];
const input1 = input.splice(0, 1)[0].split(" ");
let M = Number(input1[0]);
let N = Number(input1[1]);
let H = Number(input1[2]);

let total = M * N * H;
let q = new Queue();
let maps = [];
let visit = [];

for (let h = 0; h < H; h++) {
  maps[h] = [];
  visit[h] = [];
  for (let r = 0; r < N; r++) {
    maps[h][r] = new Array(M).fill(0);
    visit[h][r] = new Array(M).fill(false);
  }
}

for (let h = 0; h < H; h++) {
  for (let r = 0; r < N; r++) {
    const input2 = input.splice(0, 1)[0].split(" ");
    for (let c = 0; c < M; c++) {
      maps[h][r][c] = Number(input2[c]);
      if (maps[h][r][c] === 1) {
        q.offer([h, r, c]);
        visit[h][r][c] = true;
      } else if (maps[h][r][c] === -1) {
        total--;
      }
    }
  }
}

let tomato = 0;
let time = 0;
let size = q.size;
while (!q.isEmpty()) {
  for (let i = 0; i < size; i++) {
    const curr = q.poll();
    const [h, r, c] = curr.data;
    tomato++;
    for (let d = 0; d < 6; d++) {
      let nr = r + dr[d];
      let nc = c + dc[d];
      let nh = h + dh[d];

      if (
        nr < 0 ||
        nc < 0 ||
        nh < 0 ||
        nr >= N ||
        nc >= M ||
        nh >= H ||
        visit[nh][nr][nc] ||
        maps[nh][nr][nc] != 0
      )
        continue;
      q.offer([nh, nr, nc]);
      visit[nh][nr][nc] = true;
      maps[nh][nr][nc] = 1;
    }
  }
  size = q.size;
  time++;
}
if (tomato !== total) console.log(-1);
else console.log(time - 1);
