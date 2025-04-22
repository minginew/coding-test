const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let T = Number(input.shift());

for (let t = 0; t < T; t++) {
  let [N, M, K] = input.shift().split(" ").map(Number);
  let arr = input.shift().split(" ").map(Number);
  let [money, count] = [0, 0];

  for (let i = 0; i < M; i++) {
    money += arr[i];
  }
  if (money < K) count++;
    
  if (N == M) {
    console.log(count);
    continue;
  }
    
  for (let left = 1; left < N; left++) {
    let right = left + M - 1;
    if (right >= N) right %= N;
    money += arr[right] - arr[left - 1];
    if (money < K) count++;
  }
  console.log(count);
}
