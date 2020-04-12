
function insertionSort(arr) {
    for (let i = 1; i < arr.length; i++) {
        let temp = arr[i];
        let j = i - 1;
        while(temp < arr[j] && j >= 0) {
            arr[j + 1] = arr[j]
            j--;
        }
        arr[j + 1] = temp;
    }
    return arr;
}

const data1 = [9,21,54,15,11,6,18,86,61,1,54,62,78,35,13,58,12,25,38,40,7];
const data2 = Array.apply(null, {length:100000}).map(Function.call, Math.random);
console.time('sort');
const sorted = insertionSort(data1);
console.timeEnd('sort');
console.log(sorted);
