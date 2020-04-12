function minSubArrayLen(myArray, num) {
  let start = 0;
  let end= 0;
  let minLength = Number.POSITIVE_INFINITY;
  
  let sum = myArray[0];
  
  while (start <= myArray.length && end < myArray.length) {
    if (sum >= num) {
      let newLength = end - start + 1;
      if (newLength == 1) {
        return 1;
      }
      
      if (minLength > newLength) {
        minLength = newLength;
      }
      sum = sum - myArray[start];
      start++;
    } else {
      end++;
      sum = sum + myArray[end];
    }
  }
  return minLength == Number.POSITIVE_INFINITY ? 0 : minLength;
}
  
console.log(minSubArrayLen([2,3,1,2,4,3], 7));
/*
2
*/
minSubArrayLen([2,1,6,5,4],9);
/*
2
*/
minSubArrayLen([3,1,7,11,2,9,8,21,62,33,19], 52);
/*
1
*/
minSubArrayLen([1,4,16,22,5,7,8,9,10],39);
/*
3
*/
minSubArrayLen([1,4,16,22,5,7,8,9,10],55);
/*
5
*/
minSubArrayLen([4,3,3,8,1,2,3],11);
/*
2
*/
minSubArrayLen([1,4,16,22,5,7,8,9,10], 95);
/*
0
*/
