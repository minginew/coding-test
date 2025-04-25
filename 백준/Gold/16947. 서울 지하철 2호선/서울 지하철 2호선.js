const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let index = 0;
let cycleStart = -1;
let N = +input[index++];
let adjArr = new Array(N);
let visit = Array.from({ length: N }, () => false);
let cycle = Array.from({ length: N }, () => false);
let path = Array.from({ length: N }, () => 0);

for (let i = 0; i < N; i++) adjArr[i] = new Array();
for (let i = 0; i < N; i++) {
  let [st, ed] = input[index++].split(" ").map(Number);
  adjArr[st - 1].push(ed - 1);
  adjArr[ed - 1].push(st - 1);
}

dfs(0, -1);
let queue = [];
visit.fill(false);
for (let i = 0; i < N; i++) {
  if (cycle[i]) {
    queue.push(i);
    visit[i] = true;
  }
}
bfs(queue);
console.log(path.join(" "));

//cycle 탐색
function dfs(node, parent) {
  let isCycle = false;
  visit[node] = true;
  for (let i = 0; i < adjArr[node].length; i++) {
    let next = adjArr[node][i];
    if (visit[next]) {
      if (cycleStart === -1 && next !== parent) {
        isCycle = true;
        cycleStart = next;
      }
      continue;
    }
    visit[next] = true;
    let result = dfs(next, node);
    if (result) isCycle = true;
  }
  cycle[node] = isCycle;
  if (node == cycleStart) {
    return false;
  }
  return isCycle;
}

// cycle에서 떨어진 거리 탐색
function bfs(q) {
  let size = q.length;
  let move = 0;
  while (q.length > 0) {
    for (let i = 0; i < size; i++) {
      let node = q.shift();
      path[node] = move;

      for (let i = 0; i < adjArr[node].length; i++) {
        let next = adjArr[node][i];
        if (visit[next]) continue;
        q.push(next);
        visit[next] = true;
      }
    }
    size = q.length;
    move++;
  }
}
