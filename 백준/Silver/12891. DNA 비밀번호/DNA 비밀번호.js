const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let [N, M] = input.shift().split(" ").map(Number);
let DNA = input.shift().split("");
let condition = input.shift().split(" ").map(Number);
let count = Array.from({ length: 4 }, () => 0);
let left = 0;
let right = M - 1;

for (let i = 0; i < M; i++) {
  let ch = DNA[i];
  dnaPlus(ch, count);
}

let answer = 0;
while (right < N) {
  let possible = true;
  for (let i = 0; i < 4; i++) {
    if (count[i] < condition[i]) {
      possible = false;
      break;
    }
  }

  if (possible) {
    answer++;
  }

  let ch1 = DNA[left];
  let ch2 = DNA[right + 1];

  dnaMinus(ch1, count);
  dnaPlus(ch2, count);
  left++;
  right++;
}
console.log(answer);

function dnaPlus(ch, count) {
  switch (ch) {
    case "A":
      count[0]++;
      break;
    case "C":
      count[1]++;
      break;
    case "G":
      count[2]++;
      break;
    case "T":
      count[3]++;
      break;
  }
}

function dnaMinus(ch, count) {
  switch (ch) {
    case "A":
      count[0]--;
      break;
    case "C":
      count[1]--;
      break;
    case "G":
      count[2]--;
      break;
    case "T":
      count[3]--;
      break;
  }
}
