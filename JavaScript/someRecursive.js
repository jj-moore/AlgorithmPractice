const isOdd = val => val % 2 !== 0;
const lessThan10 = val => val < 10;

function someRecursive(array, callback){
	if (!array || array.length === 0) return false;
	if (array.length === 1) return callback(array[0]);
  
	if (callback(array[0]) === true) {
		return true;
	} else {
		return someRecursive(array.slice(1), callback);
	}
}

//console.log(someRecursive([1,2,3,4], isOdd)); // true
//console.log(someRecursive([4,6,8,9], isOdd)); // true
//console.log(someRecursive([4,6,8], isOdd)); // false
//console.log(someRecursive([4,6,8], val => val > 10)); // false

const myArray = process.argv.slice(2);
const answer = someRecursive(myArray, lessThan10);
console.log(answer);