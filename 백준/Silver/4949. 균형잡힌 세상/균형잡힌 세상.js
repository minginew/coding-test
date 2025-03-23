const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let answer = "";
for (let i = 0; i < input.length - 1; i++) {
  let stack = [];
  let str = input[i].split("");
  for (let j = 0; j < str.length; j++) {
    if (str[j] !== "(" && str[j] !== ")" && str[j] !== "[" && str[j] !== "]")
      continue;
    if (stack.length === 0) {
      stack.push(str[j]);
    } else if (stack[stack.length - 1] === "[" && str[j] === "]") {
      stack.pop();
    } else if (stack[stack.length - 1] === "(" && str[j] === ")") {
      stack.pop();
    } else {
      stack.push(str[j]);
    }
  }
  if (stack.length === 0) answer = answer.concat("yes").concat("\n");
  else answer = answer.concat("no").concat("\n");
}
console.log(answer);
