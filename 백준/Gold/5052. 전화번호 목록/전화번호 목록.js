const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let [T, ...input1] = input;
let answer = "";
T = Number(T);
for (let t = 0; t < T; t++) {
  let N = Number(input1.shift());
  let arr = input1.splice(0, N);
  arr.sort();
  let std = arr[0];
  let result = "YES";
  for (let i = 1; i < N; i++) {
    if (arr[i].length < std.length) {
      std = arr[i];
    } else if (arr[i].startsWith(std)) {
      result = "NO";
      break;
    } else {
      std = arr[i];
    }
  }
  answer = answer.concat(result).concat("\n");
}
console.log(answer);
