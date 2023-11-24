let msg = "welcome";
msg = 100;

let num = 123;
num = 12.345678;

console.log(100 / 0);
console.log(Infinity);
console.log("Not a Number" / 100); // NaN
console.log(9007199254740991n); // BigInt

let myName = "seungjo";
let yourName = "Ann";
let phrase = `Hello ${myName} and ${yourName}`;

console.log(phrase);
console.log(` result = ${100+200}`);

let text = "seungjo";
console.log(text + " : " + typeof text);
text = 100;
console.log(text + " : " + typeof text);
text = '100' + 10;
console.log(text + " : " + typeof text);
text = '100' / 10;
console.log(text + " : " + typeof text);

console.log(100 > 200);
console.log(100 < 200);

let hour = 5;
console.log(hour > 4 && hour < 6);

let myName = null;

let myName;
console.log(myName);

let oldPeople = new Object();
let youngPeople = {};
let people = {
    name : "seungjo",
    age : 25
};

console.log(people.name);
console.log(people.age);
delete people.age;
console.log(people.age);

const map = new Map();

map.set('a', 1);
map.set('b', 2);
map.set('c', 3);

console.log(map.get('a'));

map.set('a', 97);

console.log(map.get('a'));

console.log(map.size);

map.delete('b');

console.log(map.size);