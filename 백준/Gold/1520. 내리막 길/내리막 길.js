const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let dr = [-1, 1, 0, 0];
let dc = [0, 0, -1, 1];
let [N, M] = input.shift().split(" ").map(Number);
let map = input.map((ele) => {
  return ele.split(" ").map(Number);
});
let dp = Array.from({ length: N }, () => Array.from({ length: M }, () => -1));

dfs(0, 0);
console.log(dp[0][0]);

function dfs(r, c) {
  if (r == N - 1 && c == M - 1) return 1;
  dp[r][c] = 0;
  for (let d = 0; d < 4; d++) {
    let nr = r + dr[d];
    let nc = c + dc[d];
    if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[r][c] <= map[nr][nc])
      continue;
    if (dp[nr][nc] != -1) {
      dp[r][c] += dp[nr][nc];
      continue;
    }
    let count = dfs(nr, nc);
    dp[r][c] += count;
  }
  if (dp[r][c] == 0) return 0;
  else return dp[r][c];
}
