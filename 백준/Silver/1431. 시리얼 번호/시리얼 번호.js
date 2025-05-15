const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let arr = [];
for (let i = 0; i < N; i++) {
  arr.push(input[idx++]);
}

arr = arr.sort((a, b) => {
  if (a.length > b.length) return 1;
  else if (a.length < b.length) return -1;
  else {
    if (sum(a) > sum(b)) return 1;
    else if (sum(a) < sum(b)) return -1;
    else {
      if (a > b) return 1;
      else return -1;
    }
  }
});

console.log(arr.join("\n"));

function sum(str) {
  let sum = 0;
  str = str.split("");
  str.forEach((ele) => {
    if (Number(ele)) {
      sum += Number(ele);
    }
  });
  return sum;
}
