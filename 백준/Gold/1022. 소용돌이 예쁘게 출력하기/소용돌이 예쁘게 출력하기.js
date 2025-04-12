const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let N = 5001;
let dr = [0, -1, 0, 1];
let dc = [1, 0, -1, 0];
let [r1, c1, r2, c2] = input.shift().split(" ").map(Number);

let map = Array.from({ length: r2 - r1 + 1 }, () =>
  Array.from({ length: c2 - c1 + 1 }, () => 0)
);
let [d, r, c, n, len] = [0, N, N, 1, 1];
if (-r1 >= 0 && -c1 >= 0 && -r1 <= r2 - r1 && -c1 <= c2 - c1) map[-r1][-c1] = 1;
while (true) {
  if (r > 10001 && c > 10001) break;
  r = r + dr[d];
  c = c + dc[d];
  d = (d + 1) % 4;
  n += 1;
  if (
    r - (r1 + N) >= 0 &&
    c - (c1 + N) >= 0 &&
    r - (r1 + N) <= r2 - r1 &&
    c - (c1 + N) <= c2 - c1
  )
    map[r - (r1 + N)][c - (c1 + N)] = n;

  for (let i = 0; i < len; i++) {
    r = r + dr[d];
    c = c + dc[d];
    n += 1;
    if (
      r - (r1 + N) >= 0 &&
      c - (c1 + N) >= 0 &&
      r - (r1 + N) <= r2 - r1 &&
      c - (c1 + N) <= c2 - c1
    )
      map[r - (r1 + N)][c - (c1 + N)] = n;
  }
  d = (d + 1) % 4;
  for (let i = 0; i < len; i++) {
    r = r + dr[d];
    c = c + dc[d];
    n += 1;
    if (
      r - (r1 + N) >= 0 &&
      c - (c1 + N) >= 0 &&
      r - (r1 + N) <= r2 - r1 &&
      c - (c1 + N) <= c2 - c1
    )
      map[r - (r1 + N)][c - (c1 + N)] = n;
  }
  len++;
}

let maxLen = 0;
for (let r = 0; r <= r2 - r1; r++) {
  for (let c = 0; c <= c2 - c1; c++) {
    let strLen = map[r][c].toString().length;
    if (maxLen < strLen) maxLen = strLen;
  }
}
for (let r = 0; r <= r2 - r1; r++) {
  answer = "";
  for (let c = 0; c <= c2 - c1; c++) {
    let str = map[r][c].toString();
    answer = answer
      .concat(" ".repeat(maxLen - str.length))
      .concat(str)
      .concat(" ");
  }
  console.log(answer);
}
