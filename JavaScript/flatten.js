function flatten(inputArray){
	if (!inputArray || inputArray.length === 0) {
	return [];
	}
	let result = [];
	for(const element of inputArray) {
		if (Array.isArray(element)) {
			result = result.concat(flatten(element));
		} else {
			result.push(element);
		}
	}
	return result;
}

const answer = flatten([1, 2, 3, [4, 5] ]) // [1, 2, 3, 4, 5]
// const answer = flatten([1, [2, [3, 4], [[5]]]]) // [1, 2, 3, 4, 5]
// const answer = flatten([[1],[2],[3]]) // [1,2,3]
// const answer = flatten([[[[1], [[[2]]], [[[[[[[3]]]]]]]]]]) // [1,2,3]
console.log(answer);


