const args = process.argv.slice(2);
// const answer = factorial(args[0]);
// console.log(answer);

const answer = collectOddValues(args);
console.dir(answer);


function factorial(num) {
	if (num < 0) return NaN;
	if (num <= 1) return 1;
	return (num * factorial(num - 1));
}

function collectOddValues(arr) {
	let newArr = [];
	if (arr.length === 0) {
		return newArr;
	}
	
	if(arr[0] % 2 === 1) {
		newArr.push(arr[0]);
	}
	
	newArr = newArr.concat(collectOddValues(arr.slice(1)));
	return newArr;
}