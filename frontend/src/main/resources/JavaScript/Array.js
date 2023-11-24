const colorArr = [];
colorArr[0] = 'red';
colorArr[1] = 'blue';
colorArr[2] = 'green';
colorArr[3] = 'black';
colorArr[4] = 'white';

const color = ['red', 'blue', 'green', 'black', 'white'];

const colorArray = [,,,,,];

const colorArr2 = new Array();
colorArr2[0] = 'red';
colorArr2[1] = 'blue';
colorArr2[2] = 'green';
colorArr2[3] = 'black';
colorArr2[4] = 'white';

const color2 = new Array('red', 'blue', 'green', 'black', 'white');
const colorArray2 = new Array(5);

for (let i = 0; i < colorArr.length; i++) {
    console.log(colorArr[i]);
}

for (value of colorArr) {
    console.log(value);
}