let literal = {};

let person = {
    name : 'seungjo',
    age : 25
};

console.log(person);
console.log(person.name);
console.log(person['name']);
console.log(person.age);
console.log(person['age']);

let counter = {
    num : 0,
    increase : function() {
        this.num++;
    }
    /**
     * increase() {
     * this.num++;
     * }
     */
};

console.log(counter.num);
counter.increase();
console.log(counter['num']);

function Person(name, age) {
    this.name = name;
    this.age = age;
}

const newPerson = new Person('seungjo', 25);
console.log(newPerson);
console.log('name' in newPerson);
console.log('age' in newPerson);

for (key in newPerson) {
    console.log(key);
}

const newPerson2 = {};
for (key in newPerson) {
    newPerson2[key] = newPerson[key];
}

console.log("name : " + newPerson2.name);
console.log("age : " + newPerson2.age);

var objects = [{ 'a': 1 }, { 'b': 2 }];

var deep = _.cloneDeep(objects);
console.log(deep[0] === objects[0]);