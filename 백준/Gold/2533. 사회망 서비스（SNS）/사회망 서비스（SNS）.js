const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let idx = 0;
let N = Number(input[idx++]);
let tree = new Array(N + 1);
let visit = new Array({ length: N + 1 }, () => false);
let dp = Array.from({ length: N + 1 }, () =>
  Array.from({ length: 2 }, () => 0)
);

for (let i = 0; i <= N; i++) tree[i] = [];
for (let i = 0; i < N - 1; i++) {
  let [n1, n2] = input[idx++].split(" ").map(Number);
  tree[n1].push(n2);
  tree[n2].push(n1);
}
dfs(1);
console.log(Math.min(dp[1][0], dp[1][1]));

//dp[][0]: 자신이 얼리어답터, dp[][1]: 자신이 얼리어답터 x
function dfs(node) {
  visit[node] = true;
  dp[node][0] = 1;
  for (let i = 0; i < tree[node].length; i++) {
    let next = tree[node][i];
    if (visit[next]) continue;
    dfs(next);
    dp[node][0] += Math.min(dp[next][0], dp[next][1]);
    dp[node][1] += dp[next][0];
  }
}
