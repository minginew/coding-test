const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let N = Number(input[0]);
let arr = Array.from({ length: N + 1 }, () => 0);
let preNum = Array.from({ length: N + 1 }, () => 0);

for (let i = 1; i <= N; i++) {
  if (3 * i <= N) {
    arr[3 * i] =
      arr[3 * i] == 0 ? arr[i] + 1 : Math.min(arr[i] + 1, arr[3 * i]);
    if (arr[3 * i] == arr[i] + 1) preNum[3 * i] = i;
  }
  if (2 * i <= N) {
    arr[2 * i] =
      arr[2 * i] == 0 ? arr[i] + 1 : Math.min(arr[i] + 1, arr[2 * i]);
    if (arr[2 * i] == arr[i] + 1) preNum[2 * i] = i;
  }
  if (i + 1 <= N) {
    arr[i + 1] =
      arr[i + 1] == 0 ? arr[i] + 1 : Math.min(arr[i] + 1, arr[i + 1]);
    if (arr[i + 1] == arr[i] + 1) preNum[i + 1] = i;
  }
}

let answer = String(N).concat(" ");
path(N);
console.log(arr[N]);
console.log(answer);

function path(num) {
  if (num === 1) {
    return;
  }
  answer = answer.concat(String(preNum[num])).concat(" ");
  path(preNum[num]);
}
