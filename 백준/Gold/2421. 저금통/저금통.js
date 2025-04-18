const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let N = Number(input);
let dp = Array.from({ length: N + 1 }, () =>
  Array.from({ length: N + 1 }, () => 0)
);

for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= N; j += 2) {
    let num = Number(i.toString() + j.toString());
    let decimal = true;
    for (let h = 2; h <= Math.sqrt(Number(i.toString() + j.toString())); h++) {
      if (num % h === 0) {
        decimal = false;
        break;
      }
    }
    if (decimal) {
      dp[i][j] = 1;
    }
  }
}

for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= N; j++) {
    dp[i][j] += Math.max(dp[i - 1][j], dp[i][j - 1]);
  }
}

console.log(dp[N][N] - 1);
