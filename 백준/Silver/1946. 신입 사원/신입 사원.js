const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let T = Number(input.shift());
let answer = "";
for (let t = 0; t < T; t++) {
  let N = Number(input.shift());
  let standard = N + 1;
  let count = 0;
  let arr = input.splice(0, N).map((ele) => ele.split(" ").map(Number));
  arr.sort((a, b) => {
    return a[0] - b[0];
  });

  for (let i = 0; i < N; i++) {
    if (standard > arr[i][1]) {
      standard = arr[i][1];
      count++;
    }
  }
  answer = answer.concat(count).concat("\n");
}
console.log(answer);
