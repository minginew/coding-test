const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let broken = 0;
let N = Number(input[idx++]);
let set = new Set();
for (let i = 0; i < N; i++) {
  let [n1, n2, n3] = input[idx++].split(" ").map(Number);
  let [f1, f2, f3] = [set.has(n1), set.has(n2), set.has(n3)];
  if (f1 || f2 || f3) {
    set.add(n1);
    set.add(n2);
    set.add(n3);
  } else {
    broken++;
    set.add(n1);
    set.add(n2);
    set.add(n3);
  }
}
console.log(broken);
