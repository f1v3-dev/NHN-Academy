console.log(square(4));

function square(number) {
    return number * number;
}

let square2 = function (number) { return number * number };
let x = square2(4);
console.log(x);

(function(number = 4) {
    return number * number;
})();

function sayHello() {
    return "Hello, ";
}

function greeting(helloMessage, name) {
    console.log(helloMessage() + name);
}

greeting(sayHello, "JavaScript!");

function sayHello2() {
    return function() {
        console.log("Hello!");
    }
}

sayHello2()();

const foo = function() {
    console.log("foobar");
}

foo();