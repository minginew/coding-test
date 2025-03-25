const fs = require("fs");
const filePath =
  process.platform === "linux" ? "dev/stdin" : "baekjun/input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let arr = input.shift().split("");
let preElement = "";
let stack = [];
let count = 0;

for (let i = 0; i < arr.length; i++) {
  let currElement = arr[i];
  if (preElement == "(" && currElement == ")") {
    stack.pop();
    count += stack.length;
  } else if (preElement == ")" && currElement == ")") {
    stack.pop();
    count++;
  } else {
    stack.push(currElement);
  }
  preElement = currElement;
}

console.log(count);
