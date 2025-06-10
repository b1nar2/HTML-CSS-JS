class Duck {
    quack(): void {
        console.log("꽥꽥!");
    }
    feathers(): void {
        console.log("오리에게 흰색, 회색 깃털이 있습니다.");
    }
}

class Person {
    quack(): void {
        console.log("사람이 오리를 흉내 냅니다.");
    }
    feathers(): void {
        console.log("사람은 깃털은 없지만 털이 있습니다.");
    }
}

function inTheForest(duck: Duck): void {
    duck.quack();
    duck.feathers();
}

// let duck : Duck = new Duck(); // OK
// let duck : Duck = new Person(); // 구조적 타이핑 (duck typing)
// let duck : Person = new Person(); // 구조적 타이핑 (duck typing)
let duck : Duck = new Duck(); // 명시적 타이핑(C++, Java의 경우)
inTheForest(duck);

console.log("\n-------------- 구조적 타이핑 (duck typing) -----------------\n");

// https://www.typescriptlang.org/ko/docs/handbook/typescript-in-5-minutes.html
// https://www.typescriptlang.org/ko/docs/handbook/typescript-in-5-minutes.html#%EA%B5%AC%EC%A1%B0%EC%A0%81-%ED%83%80%EC%9E%85-%EC%8B%9C%EC%8A%A4%ED%85%9C-structural-type-system

type DuckType = Duck;
type PersonType = Person;

var duckType : DuckType;

// duckType = new DuckType(); // (X) 타입 자체는 생성자가 없고, 클래스만 생성자를 가짐 !
duckType = new Duck();
duckType.feathers();
console.log("")

// var duckType : PersonType;

duckType = new Person(); // 구조적 타이핑(duck typing)
duckType.feathers();