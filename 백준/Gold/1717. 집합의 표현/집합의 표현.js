const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  function find(node) {
    if (parent[node] === node) return node;
    else return (parent[node] = find(parent[node]));
  }

  function union(f1, f2) {
    if (f1 === f2) return;
    if (rank[f1] < rank[f2]) {
      parent[f1] = f2;
    } else {
      parent[f2] = f1;
      if (rank[f1] == rank[f2]) rank[f1]++;
    }
  }

  let [input1, ...input2] = input;
  input1 = input1.split(" ");
  let parent = [];
  let rank = [];
  let answer = "";

  let N = Number(input1[0]);
  let M = Number(input1[1]);

  for (let i = 0; i <= N; i++) {
    parent[i] = i;
    rank[i] = 0;
  }

  for (let i = 0; i < M; i++) {
    let arr = input2[i].split(" ");
    let work = Number(arr[0]);
    let n1 = Number(arr[1]);
    let n2 = Number(arr[2]);
    let f1 = find(n1);
    let f2 = find(n2);

    if (work === 0) {
      union(f1, f2);
    } else if (work === 1) {
      if (f1 == f2) answer = answer.concat("YES").concat("\n");
      else answer = answer.concat("NO").concat("\n");
    }
  }

  console.log(answer);
  process.exit();
});
