const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let dr = [-1, 1, 0, 0];
let dc = [0, 0, -1, 1];
let arr = [];
let [N, M] = input[idx++].split(" ").map(Number);
let map1 = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
let map2 = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
let visit = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => false)
);

for (let r = 0; r < N; r++) {
  let row = input[idx++].split(" ").map(Number);
  for (let c = 0; c < M; c++) {
    map1[r][c] = row[c];
  }
}

for (let r = 0; r < N; r++) {
  let row = input[idx++].split(" ").map(Number);
  for (let c = 0; c < M; c++) {
    map2[r][c] = row[c];
  }
}

let start = null;
root: for (let r = 0; r < N; r++) {
  for (let c = 0; c < M; c++) {
    if (map1[r][c] !== map2[r][c]) {
      start = [r, c];
      break root;
    }
  }
}

if (start == null) {
  console.log("YES");
  return;
}
let value = map1[start[0]][start[1]];
bfs([start], visit, value);

for (let i = 0; i < arr.length; i++) {
  let [r, c] = arr[i];
  map1[r][c] = map2[start[0]][start[1]];
}

for (let r = 0; r < N; r++) {
  for (let c = 0; c < M; c++) {
    if (map1[r][c] !== map2[r][c]) {
      console.log("NO");
      return;
    }
  }
}
console.log("YES");

function bfs(q, visit, value) {
  while (q.length > 0) {
    let [r, c] = q.shift();
    arr.push([r, c]);
    for (let d = 0; d < 4; d++) {
      let nr = r + dr[d];
      let nc = c + dc[d];
      if (
        nr < 0 ||
        nc < 0 ||
        nr >= N ||
        nc >= M ||
        visit[nr][nc] ||
        map1[nr][nc] !== value
      )
        continue;
      q.push([nr, nc]);
      visit[nr][nc] = true;
    }
  }
}
