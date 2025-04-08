const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let N = Number(input.shift());
let st = input[0].split(" ").map(Number);
let dx = [
  [1, 3, 9],
  [1, 9, 3],
  [3, 1, 9],
  [3, 9, 1],
  [9, 1, 3],
  [9, 3, 1],
];
let dp = [];
let visit = [];

for (let i = 0; i <= 60; i++) {
  dp.push(
    Array.from({ length: 61 }, () => Array.from({ length: 61 }, () => -1))
  );
  visit.push(
    Array.from({ length: 61 }, () => Array.from({ length: 61 }, () => false))
  );
}
let q = [[st[0], st[1] ?? 0, st[2] ?? 0]];
dp[st[0]][st[1] ?? 0][st[2] ?? 0] = 0;
while (q.length > 0) {
  if (dp[0][0][0] !== -1) break;
  x1 = q[0][0];
  x2 = q[0][1];
  x3 = q[0][2];
  q.shift();

  for (let d = 0; d < 6; d++) {
    nx1 = x1 - dx[d][0] < 0 ? 0 : x1 - dx[d][0];
    nx2 = x2 - dx[d][1] < 0 ? 0 : x2 - dx[d][1];
    nx3 = x3 - dx[d][2] < 0 ? 0 : x3 - dx[d][2];
    if (dp[nx1][nx2][nx3] === -1 || dp[nx1][nx2][nx3] > dp[x1][x2][x3] + 1) {
      q.push([nx1, nx2, nx3]);
      dp[nx1][nx2][nx3] = dp[x1][x2][x3] + 1;
    }
  }
}
console.log(dp[0][0][0]);
