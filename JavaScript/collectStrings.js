function collectStrings(inputObject) {
	if (!inputObject || inputObject.length === 0) {
		return inputObject;
	}

	let returnObject = [];
	for(const value of Object.values(inputObject)) {
		if (typeof value === 'string') {
			
			returnObject.push(value);
		} else if (typeof value === 'object') {
			returnObject = returnObject.concat(collectStrings(value));
		}
	}
	return returnObject;
}

const obj = {
    stuff: "foo",
    data: {
        val: {
            thing: {
                info: "bar",
                moreInfo: {
                    evenMoreInfo: {
                        weMadeIt: "baz"
                    }
                }
            }
        }
    }
}

const answer = collectStrings(obj); // ["foo", "bar", "baz"])
console.dir(answer);