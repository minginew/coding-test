const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let dr = [-1, 1, 0, 0];
let dc = [0, 0, -1, 1];
let N = Number(input.shift());
let map = input.map((ele) => {
  let row = ele.split(" ").map(Number);
  return row;
});

let dp = Array.from({ length: N }, () => Array.from({ length: N }, () => -1));

for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (dp[r][c] !== -1) continue;
    dfs(r, c, 0);
  }
}

function dfs(r, c, n) {
  let maxCount = n;
  for (let d = 0; d < 4; d++) {
    let nr = r + dr[d];
    let nc = c + dc[d];

    if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[r][c] >= map[nr][nc])
      continue;
    if (dp[nr][nc] !== -1) {
      if (maxCount < dp[nr][nc] + n + 1) maxCount = dp[nr][nc] + n + 1;
      continue;
    }
    let count = dfs(nr, nc, n + 1);
    if (maxCount < count) maxCount = count;
  }
  dp[r][c] = maxCount - n;
  return maxCount;
}

let answer = 0;
for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (answer < dp[r][c]) answer = dp[r][c];
  }
}
console.log(answer + 1);
