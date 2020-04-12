function capitalizeWords (inputArray) {
	if (!inputArray || inputArray.length === 0) {
		return [];
	}
	const capitalize = inputArray[0].toUpperCase();
	if (inputArray === 1) {
		return [ capitalize ];
	}
	
	inputArray.shift();
	return [ capitalize ].concat(capitalizeWords(inputArray));
}

let words = ['i', 'am', 'learning', 'recursion'];
const answer = capitalizeWords(words); // ['I', 'AM', 'LEARNING', 'RECURSION']
console.log(answer);