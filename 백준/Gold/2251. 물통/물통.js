const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
const capacity = input[idx++].split(" ").map(Number);
let visit = createArr(false, capacity);
let queue = [[0, 0, capacity[2]]];
let set = new Set();

visit[capacity[0]][capacity[1]][capacity[2]] = true;
while (queue.length > 0) {
  let curr = queue.shift();
  if (curr[0] === 0) set.add(curr[2]);

  for (let i = 0; i < 3; i++) {
    if (curr[i] === 0) continue;
    for (let j = 0; j < 3; j++) {
      if (i === j) continue;
      let diff = capacity[j] - curr[j];
      let water = curr[i];
      let next = [...curr];
      if (water >= diff) {
        next[i] = water - diff;
        next[j] = capacity[j];
      } else {
        next[i] = 0;
        next[j] += water;
      }
      if (visit[next[0]][next[1]][next[2]]) continue;
      visit[next[0]][next[1]][next[2]] = true;
      queue.push([...next]);
    }
  }
}

let answer = Array.from(set)
  .sort((a, b) => a - b)
  .join(" ");
console.log(answer);

function createArr(initial, capacity) {
  let [A, B, C] = capacity;
  let arr = Array.from({ length: A + 1 }, () =>
    Array.from({ length: B + 1 }, () =>
      Array.from({ length: C + 1 }, () => initial)
    )
  );
  return arr;
}
