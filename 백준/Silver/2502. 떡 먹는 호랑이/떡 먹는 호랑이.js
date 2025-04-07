const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
const [N, M] = input[0].split(" ").map(Number);

dp = Array.from({ length: 2 }, () => Array.from({ length: N + 1 }, () => 0));
dp[0][3] = 1;
dp[0][4] = 1;
dp[1][3] = 1;
dp[1][4] = 2;

for (let i = 5; i <= N; i++) {
  dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
  dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
}

let firstRatio = dp[0][N];
let secondRatio = dp[1][N];
let A = 1;
let B = 1;
for (let i = 1; i <= M; i++) {
  if ((M - firstRatio * i) % secondRatio === 0) {
    A = i;
    B = (M - firstRatio * i) / secondRatio;
    break;
  }
}
console.log(A);
console.log(B);
