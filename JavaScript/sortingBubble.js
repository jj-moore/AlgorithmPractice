function bubbleSort(arr) {
    let swaps = true;
    for (let i = arr.length; i > 0 && swaps; i--) {
        let swaps = false;
        for (let j = 1; j < i; j++) {
            if (arr[j] < arr[j - 1]) {
                swaps = true;
                let temp = arr[j];
                arr[j] = arr[j - 1];
                arr [j - 1] = temp;
            }
        }
    }
    return arr;
}

const data1 = [9,21,54,15,11,6,18,86,61,1,54,62,78,35,13,58,12,25,38,40,7];
const data2 = Array.apply(null, {length:100000}).map(Function.call, Math.random);
console.time('sort');
const sorted = bubbleSort(data1);
console.timeEnd('sort');
console.log(sorted);
