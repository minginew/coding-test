const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let [N, K] = input.shift().split(" ").map(Number);
let ryan = input
  .shift()
  .split(" ")
  .map((ele, idx) => (Number(ele) === 1 ? idx : -1))
  .filter((idx) => idx !== -1);

if (ryan.length < K) {
  console.log(-1);
  return;
}

let [left, right, minLen] = [0, K - 1, Infinity];
while (right < N) {
  let len = ryan[right] - ryan[left] + 1;
  if (minLen > len) minLen = len;
  [left, right] = [left + 1, right + 1];
}

console.log(minLen);
