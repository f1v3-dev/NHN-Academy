// if-else
if (condition_1) {
    statement_1;
} else if (condition_2) {
    statement_2;
} else if (condition_n) {
    statement_n;
} else {
    statement_last;
}

// switch
switch (expression) {
    case label_1:
        statements_1
        break;
    case label_2:
        statements_2
        break;
    default:
        statements_def
        break;
}

const expr = "Papayas";
switch (expr) {
    case 'Oranges':
        console.log('Oranges are $0.59 a pound.');
        break;
    case 'Mangoes':
    case 'Papayas':
        console.log('Mangoes and papayas are $2.79 a pound.');
        break;
    default:
        console.log(`Sorry, we are out of ${expr}.`);
}

let count = 1;
while (count <= 10) {
    console.log(count++);
}

for (let i = 0; i < 10; i++) {
    console.log(i);
}