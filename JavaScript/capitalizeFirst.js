function capitalizeFirst (inputArray) {
	if (!inputArray || inputArray.length === 0)
		return [];
	const firstWord = inputArray[0].charAt(0).toUpperCase() + inputArray[0].slice(1);
	if (inputArray.length === 1) {
		return [ firstWord ];
	}
	inputArray.shift();
	return [ firstWord ].concat(capitalizeFirst(inputArray));
}

const answer = capitalizeFirst(['car','taco','banana']); // ['Car','Taco','Banana']
console.dir(answer);