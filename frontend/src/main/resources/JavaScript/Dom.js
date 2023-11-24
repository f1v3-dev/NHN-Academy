// create element , append element
const divElement = document.createElement("div");

divElement.id = "myDiv";
divElement.innerHTML = 'div element! <br>';
document.body.appendChild(divElement);

const textNode = document.createTextNode('text node!');
divElement.appendChild(textNode);

const myDiv = document.getElementById("myDiv");
myDiv.setAttribute("style", "color: red");
// getElementsById

const h1Element = document.createElement("h1");
h1Element.id = "heading";
h1Element.innerHTML = 'H1 element! <br>';
document.body.appendChild(h1Element);

const h1 = document.getElementById("heading");
console.log("h1: " + h1);

// getElementsByClassName
const brightElement1 = document.createElement("div");
brightElement1.setAttribute("class", "bright");
brightElement1.innerHTML = 'bright-Element1';
document.body.appendChild(brightElement1);

const brightElement2 = document.createElement("div");
brightElement2.setAttribute("class", "bright");
brightElement2.innerHTML = 'bright-Element2';
document.body.appendChild(brightElement2);

const brightDivList = document.getElementsByClassName("bright");
for (i = 0; i < brightDivList.length; i++) {
    console.log(brightDivList[i].innerHTML);
}

// getElementsByTagName
let helloWorld = `
    <p>hello world-1</p>
    <p>hello world-2</p>
    <p>hello world-3</p>
`;

document.body.innerHTML = helloWorld;
let pList = document.getElementsByTagName("p");
for (i = 0; i < pList.length; i++) {
    console.log(pList[i]);
}

// querySelector
document.querySelector("#heading")

// querySelectorAll
document.querySelectorAll(".bright");
document.querySelectorAll("p");