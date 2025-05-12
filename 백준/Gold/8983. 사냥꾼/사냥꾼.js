const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
const [M, N, L] = input[idx++].split(" ").map(Number);
const gun = input[idx++]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);

let count = 0;
for (let i = 0; i < N; i++) {
  let [left, right] = [0, M - 1];
  let animal = input[idx++].split(" ").map(Number);
  if (animal[1] > L) continue;
  while (left <= right) {
    let mid = parseInt((left + right) / 2);
    let dist = Math.abs(gun[mid] - animal[0]) + animal[1];
    if (dist <= L) {
      count++;
      break;
    }
    if (animal[0] < gun[mid]) {
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }
}
console.log(count);
