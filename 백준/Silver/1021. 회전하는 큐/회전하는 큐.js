const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
const input1 = input.shift().split(" ");
let N = Number(input1[0]);
let M = Number(input1[1]);
let arr = [];
let deq = [];
for (let i = 1; i <= N; i++) deq.push(i);

const input2 = input.shift().split(" ");
for (let i = 0; i < M; i++) {
  let n = Number(input2[i]);
  arr.push(n);
}

let count = 0;
for (let i = 0; i < arr.length; i++) {
  let target = arr[i];
  let idx = deq.findIndex((element) => target === element);
  if (idx == 0) {
    deq.shift();
  } else if (idx <= deq.length - 1 - idx) {
    while (idx != 0) {
      deq.push(deq.shift());
      count++;
      idx--;
    }
    deq.shift();
  } else {
    while (idx != 0) {
      deq.unshift(deq.pop());
      count++;
      idx = ++idx % deq.length;
    }
    deq.shift();
  }
}
console.log(count);
