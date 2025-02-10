let player = 'John';
let points = 144;
let minPoints = 100;

if(points>=minPoints){
    console.log(`Well done, ${player}!`);
}

let color ="yellow";

switch(color) {
  case "blue": 
    console.log("This is blue.");
    break;
  case "red": 
    console.log("This is red.");
    break;
  default: 
    console.log("Color not found.");
}

let age = 42;
let isAdult = (age < 18) ? "Too young": "Old enough";
console.log(isAdult);

//la boucle for a 3 composants : l'initialisateur, la condition et l'expression finale