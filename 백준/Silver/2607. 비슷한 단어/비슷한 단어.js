const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
let N = Number(input[idx++]);
let firstWord = input[idx++].split("");
let map = new Map();
let answer = 0;
for (let i = 0; i < firstWord.length; i++) {
  let count = map.get(firstWord[i]);
  if (count) {
    map.set(firstWord[i], count + 1);
  } else {
    map.set(firstWord[i], 1);
  }
}

for (let i = 0; i < N - 1; i++) {
  let newWord = input[idx++].split("");
  let diffLen = firstWord.length - newWord.length;
  let diffCount = 0;
  let newMap = new Map();
  for (let i = 0; i < newWord.length; i++) {
    let count = newMap.get(newWord[i]);
    if (count) {
      newMap.set(newWord[i], count + 1);
    } else {
      newMap.set(newWord[i], 1);
    }
  }
  if (diffLen === 0) {
    for (let word of map.keys()) {
      if (!newMap.has(word)) diffCount++;
      else if (Math.abs(map.get(word) - newMap.get(word)) > 1) diffCount++;
    }
    if (diffCount <= 1) answer++;
  } else if (diffLen === 1) {
    for (let word of newMap.keys()) {
      if ((map.get(word) ?? 0) < newMap.get(word)) diffCount++;
    }
    if (diffCount === 0) answer++;
  } else if (diffLen === -1) {
    for (let word of map.keys()) {
      if (map.get(word) > (newMap.get(word) ?? 0)) diffCount++;
    }
    if (diffCount === 0) answer++;
  }
}

console.log(answer);
