const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let idx = 0;
let N = Number(input[idx++]);
let maps = new Map();
let orders = [];
let answer = 0;
for (let i = 0; i < N; i++) {
  let number = input[idx++];
  maps.set(number, i);
}

for (let i = 0; i < N; i++) {
  let number = input[idx++];
  orders.push(number);
}

for (let i = 0; i < N - 1; i++) {
  for (let j = i + 1; j < N; j++) {
    if (maps.get(orders[i]) > maps.get(orders[j])) {
      answer++;
      break;
    }
  }
}
console.log(answer);
