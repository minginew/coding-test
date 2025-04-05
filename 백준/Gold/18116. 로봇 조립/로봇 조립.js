const { count } = require("console");
const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let N = Number(input.shift());
let parent = Array.from({ length: 1000001 }, (_, index) => [index, 1]);
let rank = Array.from({ length: 1000001 }, () => 0);
let answer = "";

for (let i = 0; i < N; i++) {
  let [work] = input[i].split(" ");
  if (work === "I") {
    let [_, n1, n2] = input[i].split(" ");
    n1 = Number(n1);
    n2 = Number(n2);
    union(n1, n2);
  } else {
    let [_, n] = input[i].split(" ");
    n = Number(n);
    let group = find(n);
    answer = answer.concat(parent[group][1]).concat("\n");
  }
}
console.log(answer);

function find(node) {
  if (parent[node][0] === node) return node;
  else return (parent[node][0] = find(parent[node][0]));
}

function union(n1, n2) {
  let f1 = find(n1);
  let f2 = find(n2);
  if (f1 === f2) return;
  if (rank[f1] < rank[f2]) {
    parent[f1][0] = f2;
    parent[f2][1] += parent[f1][1];
  } else {
    parent[f2][0] = f1;
    parent[f1][1] += parent[f2][1];

    if (rank[f1] === rank[f2]) rank[f1]++;
  }
}
