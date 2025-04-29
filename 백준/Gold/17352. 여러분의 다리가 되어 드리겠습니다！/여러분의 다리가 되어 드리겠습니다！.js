const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let parent = Array.from({ length: N }, (_, idx) => idx);
let rank = Array.from({ length: N }, () => 0);
let answer = [];

for (let i = 0; i < N - 2; i++) {
  let [n1, n2] = input[idx++].split(" ").map(Number);
  union(n1 - 1, n2 - 1);
}

for (let i = 0; i < parent.length; i++) {
  if (parent[i] === i) answer.push(i + 1);
}

console.log(answer.join(" "));

function find(node) {
  if (parent[node] === node) return node;
  else return (parent[node] = find(parent[node]));
}

function union(n1, n2) {
  let f1 = find(n1);
  let f2 = find(n2);
  if (f1 === f2) return;
  if (rank[f1] < rank[f2]) {
    parent[f1] = f2;
  } else {
    parent[f2] = f1;
    if (rank[f1] === rank[f2]) rank[f1]++;
  }
}
