const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let count = 0;
let dr = [-1, 1, 0, 0];
let dc = [0, 0, -1, 1];
let [M, N] = input[idx++].split(" ").map(Number);
let answer = [0, 0];
let map = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => null)
);
let visit = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => false)
);

for (let r = 0; r < N; r++) {
  let row = input[idx++].split("");
  for (let c = 0; c < M; c++) {
    map[r][c] = row[c];
  }
}

for (let r = 0; r < N; r++) {
  for (let c = 0; c < M; c++) {
    if (visit[r][c]) continue;
    visit[r][c] = true;
    count = 0;
    dfs(r, c, map[r][c]);
    if (map[r][c] === "W") answer[0] += count * count;
    else answer[1] += count * count;
  }
}

console.log(answer.join(" "));

function dfs(r, c, target, move) {
  count++;
  for (let d = 0; d < 4; d++) {
    let nr = r + dr[d];
    let nc = c + dc[d];
    if (
      nr < 0 ||
      nc < 0 ||
      nr >= N ||
      nc >= M ||
      visit[nr][nc] ||
      map[nr][nc] !== target
    )
      continue;
    visit[nr][nc] = true;
    dfs(nr, nc, target);
  }
}
