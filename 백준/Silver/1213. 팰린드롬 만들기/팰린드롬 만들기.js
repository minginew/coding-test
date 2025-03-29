const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let input1 = input.shift().split("");
let maps = new Map();

for (let i = 0; i < input1.length; i++) {
  let key = input1[i];
  if (maps.has(key)) {
    maps.set(key, maps.get(key) + 1);
  } else {
    maps.set(key, 1);
  }
}

let oddNum = 0;
for (let key of maps.keys()) {
  if (maps.get(key) % 2 == 1) oddNum++;
}

let answer = "";
let keys = Array.from(maps.keys());
keys.sort();
if (oddNum > 1) {
  console.log("I'm Sorry Hansoo");
  return;
} else if (oddNum == 1) {
  let odd = "";
  for (let key of keys) {
    if (maps.get(key) % 2 == 1) {
      odd = key;
    }
    let count = Math.trunc(maps.get(key) / 2);
    answer = answer.concat(key.repeat(count));
  }
  answer = answer.concat(odd).concat(answer.split("").reverse().join(""));
} else {
  for (let key of keys) {
    let count = maps.get(key) / 2;
    answer = answer.concat(key.repeat(count));
  }
  answer = answer.concat(answer.split("").reverse().join(""));
}

console.log(answer);
