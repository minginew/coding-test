const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
class Node {
  constructor(v, w) {
    this.v = v;
    this.w = w;
  }
}
let idx = 0;
let T = Number(input[idx++]);
for (let t = 0; t < T; t++) {
  let [N, M] = input[idx++].split(" ").map(Number);
  let adjArr = new Array(N + 1);
  let dp = Array.from({ length: N + 1 }, () => 0);
  let visit = Array.from({ length: N + 1 }, () => false);
  for (let i = 0; i <= N; i++) adjArr[i] = [];
  for (let i = 0; i < M; i++) {
    let [n1, n2, w] = input[idx++].split(" ").map(Number);
    adjArr[n1].push(new Node(n2, w));
    adjArr[n2].push(new Node(n1, w));
  }

  if (N === 1) console.log(0);
  else {
    dfs(1, Number.MAX_SAFE_INTEGER);
    console.log(dp[1]);
  }

  function dfs(node, w) {
    visit[node] = true;
    let weight = 0;
    for (let i = 0; i < adjArr[node].length; i++) {
      let next = adjArr[node][i];
      if (visit[next.v]) continue;
      weight += dfs(next.v, next.w);
    }
    if (weight === 0) dp[node] = w;
    else dp[node] = Math.min(w, weight);
    return dp[node];
  }
}
