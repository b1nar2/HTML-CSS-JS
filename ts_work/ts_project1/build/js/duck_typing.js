"use strict";
class Duck {
    quack() {
        console.log("꽥꽥!");
    }
    feathers() {
        console.log("오리에게 흰색, 회색 깃털이 있습니다.");
    }
}
class Person {
    quack() {
        console.log("사람이 오리를 흉내 냅니다.");
    }
    feathers() {
        console.log("사람은 깃털은 없지만 털이 있습니다.");
    }
}
function inTheForest(duck) {
    duck.quack();
    duck.feathers();
}
// let duck : Duck = new Duck(); // OK
// let duck : Duck = new Person(); // 구조적 타이핑 (duck typing)
// let duck : Person = new Person(); // 구조적 타이핑 (duck typing)
let duck = new Duck(); // 명시적 타이핑(C++, Java의 경우)
inTheForest(duck);
console.log("\n-------------- 구조적 타이핑 (duck typing) -----------------\n");
var duckType;
// duckType = new DuckType(); // (X) 타입 자체는 생성자가 없고, 클래스만 생성자를 가짐 !
duckType = new Duck();
duckType.feathers();
console.log("");
// var duckType : PersonType;
duckType = new Person(); // 구조적 타이핑(duck typing)
duckType.feathers();
