const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let [N, K] = input.shift().split(" ").map(Number);
let arr = input[0].split(" ").map(Number);
let diff = [];

for (let i = 0; i < arr.length - 1; i++) {
  diff.push(arr[i + 1] - arr[i]);
}

diff.sort((a, b) => a - b);
let answer = 0;
for (let i = 0; i < N - K; i++) {
  answer += diff[i];
}

console.log(answer);
