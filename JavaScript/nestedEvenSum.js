function nestedEvenSum (inputObject) {
  let sum = 0;
  for (const value of Object.values(inputObject)) {
		if (typeof value === 'object') {
			sum += nestedEvenSum(value);
		} else if (Number.isSafeInteger(value) && value % 2 === 0) {
			sum += value;
		}
  }
  return sum;
}

var obj1 = {
  outer: 2,
  obj: {
    inner: 2,
    otherObj: {
      superInner: 2,
      notANumber: true,
      alsoNotANumber: "yup"
    }
  }
}

var obj2 = {
  a: 2,
  b: {b: 2, bb: {b: 3, bb: {b: 2}}},
  c: {c: {c: 2}, cc: 'ball', ccc: 5},
  d: 1,
  e: {e: {e: 2}, ee: 'car'}
};

//const answer = nestedEvenSum(obj1); // 6
const answer = nestedEvenSum(obj2); // 10
console.log(answer);