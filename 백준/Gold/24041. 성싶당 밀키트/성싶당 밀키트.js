const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let [N, G, K] = input.shift().split(" ").map(Number);
let arr = input.map((ele) => {
  return ele.split(" ").map(Number);
});
arr.sort();
let left = 0;
let right = 2e9;
let answer = 0;
while (left <= right) {
  let mid = Math.trunc((left + right) / 2);
  let sum = 0;
  let g = [];
  for (let i = 0; i < arr.length; i++) {
    let s = arr[i][0];
    let l = arr[i][1];
    let k = arr[i][2];
    sum += s * Math.max(1, mid - l);
    if (k === 1) g.push(s * Math.max(1, mid - l));
  }
  g.sort((a, b) => b - a);
  for (let i = 0; i < K; i++) {
    if (g[i] === undefined) break;
    sum -= g[i];
  }

  if (sum <= G) {
    answer = mid;
    left = mid + 1;
  } else {
    right = mid - 1;
  }
}
console.log(answer);
