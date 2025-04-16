const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let [N, M] = input.shift().split(" ").map(Number);
let adjArr = Array.from({ length: N + 1 });
let visit = Array.from({ length: N + 1 }, () => false);
let praise = Array.from({ length: N + 1 }, () => 0);
let answer = Array.from({ length: N + 1 }, () => 0);
let parentInfo = input.shift().split(" ").map(Number);

for (let i = 0; i <= N; i++) adjArr[i] = [];

for (let i = 1; i <= N; i++) {
  let p = parentInfo[i - 1];
  if (p === -1) continue;
  adjArr[p].push(i);
}

for (let i = 0; i < M; i++) {
  let [n, w] = input[i].split(" ").map(Number);
  praise[n] += w;
}
dfs(1, 0);
answer.shift();
console.log(answer.join(" "));

function dfs(node, p) {
  if (visit[node]) return;
  visit[node] = true;
  answer[node] += p + praise[node];
  for (let i = 0; i < adjArr[node].length; i++) {
    let next = adjArr[node][i];
    if (visit[next]) continue;
    dfs(next, answer[node]);
  }
}
