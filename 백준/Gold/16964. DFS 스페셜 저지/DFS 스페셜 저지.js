const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let [N] = input.shift().split(" ").map(Number);
let adjArr = Array.from({ length: N + 1 }, () => Array.from({ length: 0 }));
let visit = Array.from({ length: N + 1 }, () => false);
let path = input.pop();
let pathArr = path.split(" ").map(Number);
let pathIdx = 0;
for (let i = 0; i < N - 1; i++) {
  let [st, ed] = input[i].split(" ").map(Number);
  adjArr[st].push(ed);
  adjArr[ed].push(st);
}

let adjIdxArr = [];
for (let i = 0; i < adjArr.length; i++) {
  adjIdxArr.push(new Map());
  for (let j = 0; j < adjArr[i].length; j++) {
    let map = adjIdxArr[i];
    let node = adjArr[i][j];
    map.set(node, j);
  }
}

pathArr.shift();
let answer = "";
dfs(1);
if (answer.trim() === path) {
  console.log(1);
} else {
  console.log(0);
}

function dfs(node) {
  if (visit[node]) return;
  visit[node] = true;
  answer = answer.concat(node).concat(" ");
  while (pathArr.length > 0) {
    target = pathArr[pathIdx];
    targetIdx = adjIdxArr[node].get(target);
    if (targetIdx === undefined) {
      return;
    } else {
      next = adjArr[node][targetIdx];
      if (!visit[next]) {
        pathIdx++;
        dfs(next);
      }
    }
  }
}
