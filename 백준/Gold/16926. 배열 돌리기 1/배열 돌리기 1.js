const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let dr = [1, 0, -1, 0];
let dc = [0, 1, 0, -1];
let [N, M, R] = input.shift().split(" ").map(Number);
let map = input.map((ele) => {
  return ele.split(" ").map(Number);
});

//작은거 기준으로 반 나누기
for (let i = 0; i < R; i++) {
  let S = Math.min(N, M);
  for (let r = 0, c = 0; r < S / 2; r++, c++) {
    let nr = r;
    let nc = c;
    let d = 0;
    let num = map[r][c];
    while (true) {
      nr += dr[d];
      nc += dc[d];
      if (nr < r || nc < c || nr >= N - r || nc >= M - c) {
        nr -= dr[d];
        nc -= dc[d];
        d = (d + 1) % 4;
        continue;
      }
      let temp = map[nr][nc];
      map[nr][nc] = num;
      num = temp;
      if (nr == r && nc == c) break;
    }
  }
}

let answer = "";
map.forEach((ele) => {
  ele.forEach((n) => {
    answer = answer.concat(n).concat(" ");
  });
  answer = answer.concat("\n");
});
console.log(answer);
