const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let answer = [];
while (input[idx]) {
  let [N, start] = input[idx++].split(" ").map(Number);
  let adjArr = Array.from({ length: N + 1 }, () => []);
  let visit = Array.from({ length: N + 1 }, () => false);

  for (let i = 0; i < N - 1; i++) {
    let [n1, n2, w] = input[idx++].split(" ").map(Number);
    adjArr[n1].push([n2, w]);
    adjArr[n2].push([n1, w]);
  }

  answer.push(dfs(start, 1000000));

  function dfs(node, currCost) {
    visit[node] = true;
    let sum = 0;

    for (let i = 0; i < adjArr[node].length; i++) {
      let [next, nextCost] = adjArr[node][i];
      if (visit[next]) continue;
      sum += dfs(next, nextCost);
    }

    if (sum === 0) return currCost;
    else return Math.min(currCost, sum);
  }
}

console.log(answer.join("\n"));
