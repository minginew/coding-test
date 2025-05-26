const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let arr = input[idx++]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);
console.log(arr[Math.trunc((N - 1) / 2)]);
