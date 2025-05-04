const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let idx = 0;
let N = Number(input[idx++]);
let person = [0, ...input[idx++].split(" ").map(Number)];
let adjArr = new Array(N + 1);
let visit = Array.from({ length: N + 1 }, () => false);
let dp = Array.from({ length: N + 1 }, () =>
  Array.from({ length: 2 }, () => 0)
);
for (let i = 0; i <= N; i++) adjArr[i] = [];
for (let i = 0; i < N - 1; i++) {
  let [n1, n2] = input[idx++].split(" ").map(Number);
  adjArr[n1].push(n2);
  adjArr[n2].push(n1);
}
dfs(1);
console.log(Math.max(dp[1][0], dp[1][1]));

//dp[][0]: 우수마을 o, dp[][1]: 우수마을 x
function dfs(node) {
  visit[node] = true;
  dp[node][0] = person[node];
  for (let i = 0; i < adjArr[node].length; i++) {
    let next = adjArr[node][i];
    if (visit[next]) continue;
    dfs(next);
    dp[node][0] += dp[next][1];
    dp[node][1] += Math.max(dp[next][0], dp[next][1]);
  }
}
