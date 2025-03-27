const fs = require("fs");
const { tmpdir } = require("os");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let input1 = input.shift().split(" ").map(Number);
let N = input1[0];
let M = input1[1];
let r = input1[2];
let c = input1[3];
let K = input1[4];

let input2 = input.splice(0, N).map((ele) => ele.split(" "));
let map = [];
for (let i = 0; i < N; i++) {
  let row = input2[i].map(Number);
  map[i] = [];
  for (let j = 0; j < M; j++) {
    map[i][j] = row[j];
  }
}

//동 서 북 남
let dr = [0, 0, -1, 1];
let dc = [1, -1, 0, 0];

let dice = [1, 2, 3, 4, 5, 6];
let value = [0, 0, 0, 0, 0, 0, 0];
let input3 = input.shift().split(" ").map(Number);

value[dice[0]] = map[r][c];
map[r][c] = 0;
for (let k = 0; k < K; k++) {
  let dir = input3[k];
  nr = r + dr[dir - 1];
  nc = c + dc[dir - 1];
  if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
  r = nr;
  c = nc;

  //기본 123456
  //동 421653
  //서 326154
  //남 513462
  //북 263415
  switch (dir) {
    case 1:
      dice = [dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]];
      break;
    case 2:
      dice = [dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]];
      break;
    case 3:
      dice = [dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]];
      break;
    case 4:
      dice = [dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]];
      break;
  }

  if (map[r][c] == 0) {
    map[r][c] = value[dice[5]];
  } else {
    value[dice[5]] = map[r][c];
    map[r][c] = 0;
  }
  console.log(value[dice[0]]);
}
