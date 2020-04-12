function stringifyNumbers(inputObject) {
	if (!inputObject) {
		return inputObject;
	}
	let newObject = {};
	
	for (const key of Object.keys(inputObject)) {
		const value = inputObject[key];
		if (typeof value === 'object' && !Array.isArray(value)) {
			newObject[key] = stringifyNumbers(value);
		} else if(Number.isSafeInteger(value)) {
			newObject[key] = value.toString();
		} else {
			newObject[key] = value;
		}
	}
	return newObject;
}


let obj = {
    num: 1,
    test: [],
    data: {
        val: 4,
        info: {
            isRight: true,
            random: 66
        }
    }
}

const answer = stringifyNumbers(obj)
console.dir(answer);

/*
{
    num: "1",
    test: [],
    data: {
        val: "4",
        info: {
            isRight: true,
            random: "66"
        }
    }
}
*/