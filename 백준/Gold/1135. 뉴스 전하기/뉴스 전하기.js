const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let parents = input[idx++].split(" ").map(Number);
let adjArr = Array.from({ length: N }, () => []);
for (let i = 1; i < N; i++) {
  adjArr[parents[i]].push(i);
}

let answer = dfs(0);
console.log(answer - 1);

function dfs(node) {
  let workTime = [];
  for (let i = 0; i < adjArr[node].length; i++) {
    let next = adjArr[node][i];
    workTime.push(dfs(next));
  }
  if (workTime.length === 0) {
    return 1;
  } else {
    let maxTime = 0;
    workTime.sort((a, b) => b - a);
    workTime.forEach((ele, idx) => {
      if (maxTime < ele + idx + 1) {
        maxTime = ele + idx + 1;
      }
    });
    return maxTime;
  }
}
