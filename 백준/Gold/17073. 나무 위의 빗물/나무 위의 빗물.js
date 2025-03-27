const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let [input1, ...input2] = input;
input1 = input1.split(" ").map(Number);
input2 = input2.map((ele) => ele.split(" "));

let N = input1[0];
let M = input1[1];
let graph = new Array(N + 1).fill().map(() => []);
let tree = new Array(N + 1).fill().map(() => []);
let visit = Array(N + 1).fill(false);

for (let i = 0; i < N - 1; i++) {
  let n1 = Number(input2[i][0]);
  let n2 = Number(input2[i][1]);
  graph[n1].push(n2);
  graph[n2].push(n1);
}

dfs(1);
let leafNum = 0;
for (let i = 1; i <= N; i++) {
  if (tree[i].length == 0) leafNum++;
}
console.log(M / leafNum);

function dfs(node) {
  visit[node] = true;
  for (let i = 0; i < graph[node].length; i++) {
    let nextNode = graph[node][i];
    if (visit[nextNode]) continue;
    tree[node].push(nextNode);
    dfs(nextNode);
  }
}
