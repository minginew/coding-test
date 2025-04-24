const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let T = Number(input[idx++]);
let answer = "";
let parents, rank, group;
for (let t = 0; t < T; t++) {
  let N = Number(input[idx++]);
  let arr = Array.from({ lengh: N }, () => null);
  let adjArr = Array.from({ length: N }, () => null);
  parents = Array.from({ length: N }, (_, idx) => idx);
  rank = Array.from({ length: N }, () => 0);
  group = N;

  for (let i = 0; i < N; i++) adjArr[i] = [];
  for (let i = 0; i < N; i++) {
    arr[i] = input[idx++].split(" ").map(Number);
  }

  for (let i = 0; i < N; i++) {
    let [x1, y1, a1] = arr[i];
    for (let j = 0; j < N; j++) {
      if (i == j) continue;
      let [x2, y2, a2] = arr[j];
      let [dx, dy] = [x1 - x2, y1 - y2];
      let dist = dx * dx + dy * dy;
      dist -= (a1 + a2) * (a1 + a2);
      if (dist <= 0) adjArr[i].push(j);
    }
  }

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < adjArr[i].length; j++) {
      let f1 = find(i);
      let f2 = find(adjArr[i][j]);
      if (f1 === f2) continue;
      union(f1, f2);
    }
  }
  answer = answer.concat(group).concat("\n");
}

function find(node) {
  if (node == parents[node]) return node;
  else return (parents[node] = find(parents[node]));
}

function union(f1, f2) {
  if (f1 == f2) return;
  group--;
  if (rank[f1] < rank[f2]) {
    parents[f1] = f2;
  } else {
    parents[f2] = f1;
    if (rank[f1] === rank[f2]) rank[f1]++;
  }
}

console.log(answer);
