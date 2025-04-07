const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

dp = Array.from({ length: 31 }, () => Array.from({ length: 31 }, () => 0));

for (let w = 1; w <= 30; w++) {
  for (let h = 0; h <= w; h++) {
    if (h == 0) {
      dp[w][h] = 1;
      continue;
    }
    dp[w][h] = dp[w][h - 1] + dp[w - 1][h];
  }
}

for (let i = 0; i < input.length; i++) {
  N = Number(input[i]);
  if (N === 0) break;
  console.log(dp[N][N]);
}
