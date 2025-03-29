const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let input1 = input.shift().split(" ").map(Number);
let [N, M] = input1;
let input2 = input.splice(0, N);

let dr1 = [0, 0, -1, -1, -1, 0, 1, 1, 1];
let dc1 = [0, -1, -1, 0, 1, 1, 1, 0, -1];
let dr2 = [-1, 1, -1, 1];
let dc2 = [-1, -1, 1, 1];

let maps = Array.from({ length: N }, () => Array.from({ length: N }, () => 0));
let cloud = Array.from({ length: N }, () => Array.from({ length: N }, () => 0));
cloud[N - 2][0] = 1;
cloud[N - 2][1] = 1;
cloud[N - 1][0] = 1;
cloud[N - 1][1] = 1;

for (let r = 0; r < N; r++) {
  let row = input2[r].split(" ").map(Number);
  for (let c = 0; c < N; c++) {
    maps[r][c] = row[c];
  }
}

for (let i = 0; i < M; i++) {
  let [d, s] = input[i].split(" ").map(Number);
  let movedCloud = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => 0)
  );

  // 움직인 구름 만들기
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < N; c++) {
      if (cloud[r][c] == 0) continue;
      else cloud[r][c] = 0;

      let nr = r + dr1[d] * s;
      let nc = c + dc1[d] * s;
      while (nr < 0) nr = N + nr;
      while (nr >= N) nr = nr - N;
      while (nc < 0) nc = N + nc;
      while (nc >= N) nc = nc - N;
      movedCloud[nr][nc] = 1;
    }
  }

  // 구름이 해당위치에 물을 뿌림
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < N; c++) {
      if (movedCloud[r][c] == 1) maps[r][c] += 1;
    }
  }

  // 물이 뿌려진 각 자리의 물이있는 대각선 수만큼 물의양 추가
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < N; c++) {
      if (movedCloud[r][c] == 0) continue;
      let isWater = 0;
      for (let d = 0; d < 4; d++) {
        let nr = r + dr2[d];
        let nc = c + dc2[d];
        if (nr < 0 || nc < 0 || nr >= N || nc >= N || maps[nr][nc] == 0)
          continue;
        isWater++;
      }
      maps[r][c] += isWater;
    }
  }

  // 물의 양이 2 이상인, 구름을 새로운 구름으로 지정
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < N; c++) {
      if (maps[r][c] >= 2 && movedCloud[r][c] == 0) {
        maps[r][c] -= 2;
        cloud[r][c] = 1;
      }
    }
  }
}

let answer = 0;
for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    answer += maps[r][c];
  }
}
console.log(answer);
