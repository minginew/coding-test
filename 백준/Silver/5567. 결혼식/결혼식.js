const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let M = Number(input[idx++]);
let visit = Array.from({ length: N + 1 }, () => false);
let adjArr = new Array(N + 1);
let queue = [];
for (let i = 0; i <= N; i++) adjArr[i] = [];
for (let i = 0; i < M; i++) {
  let [v1, v2] = input[idx++].split(" ").map(Number);
  adjArr[v1].push(v2);
  adjArr[v2].push(v1);
}
queue.push([1, 0]);
visit[1] = true;
let answer = 0;
while (queue.length > 0) {
  let [curr, dep] = queue.shift();
  if (dep <= 2) answer++;
  for (let i = 0; i < adjArr[curr].length; i++) {
    let next = adjArr[curr][i];
    if (visit[next]) continue;
    queue.push([next, dep + 1]);
    visit[next] = true;
  }
}

console.log(answer - 1);
