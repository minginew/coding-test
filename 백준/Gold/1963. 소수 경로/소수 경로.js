const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let T = Number(input.shift());
let decimal = Array.from({ length: 10000 }, () => true);
for (let i = 1000; i < 10000; i++) {
  for (let j = 2; j <= Math.sqrt(i); j++) {
    if (i % j === 0) {
      decimal[i] = false;
      break;
    }
  }
}
for (let t = 0; t < T; t++) {
  let [start, end] = input[t].split(" ");
  let visit = Array.from({ length: 10000 }, () => false);
  let queue = [start];
  let size = queue.length;
  let move = 0;
  let impossible = true;
  visit[start] = true;
  while (queue.length > 0) {
    for (let i = 0; i < size; i++) {
      let curr = queue.shift();
      if (curr === end) {
        impossible = false;
        console.log(move);
      }

      for (let n = 0; n <= 3; n++) {
        for (let d = 0; d < 10; d++) {
          if (n === 0 && d === 0) continue;
          let currArr = curr.split("");
          currArr[n] = d;
          let next = Number(currArr.join(""));
          if (visit[next] || !decimal[next]) continue;
          queue.push(next.toString());
          visit[next] = true;
        }
      }
    }
    size = queue.length;
    move++;
  }
  if (impossible) {
    console.log("Impossible");
  }
}
