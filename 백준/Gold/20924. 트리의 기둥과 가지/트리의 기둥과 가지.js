const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Edge {
  constructor(v, w) {
    this.v = v;
    this.w = w;
  }
}

let idx = 0;
let [N, root] = input[idx++].split(" ").map(Number);
let visit = Array.from({ length: N + 1 }, () => false);
let adjArr = new Array(N + 1);
let [maxPillar, maxBranch, findPillar] = [0, 0, false];

for (let i = 0; i <= N; i++) adjArr[i] = [];
for (let i = 0; i < N - 1; i++) {
  let [v1, v2, w] = input[idx++].split(" ").map(Number);
  adjArr[v1].push(new Edge(v2, w));
  adjArr[v2].push(new Edge(v1, w));
}
dfs(root, 0);
console.log(maxPillar + " " + maxBranch);

function dfs(node, length) {
  if (findPillar) {
    if (length - maxPillar > maxBranch) maxBranch = length - maxPillar;
  } else {
    maxPillar = length;
  }
  if (adjArr[node].length > 2 || adjArr[root].length >= 2) findPillar = true;
  visit[node] = true;
  for (let i = 0; i < adjArr[node].length; i++) {
    let next = adjArr[node][i];
    if (visit[next.v]) continue;
    dfs(next.v, length + next.w);
  }
}
