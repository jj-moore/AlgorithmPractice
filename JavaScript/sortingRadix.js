function radixSort(arr) {
    const most = mostDigits(arr);
    for (let i = 0; i < most; i++) {
        let buckets = Array.from({length: 10}, () => []);
        for (let j = 0; j < arr.length; j++) {
            let digit = getDigit(arr[j], i);
            buckets[digit].push(arr[j]);
        }
        arr = [].concat(...buckets);
    }
    return arr;
}

function getDigit(num, i) {
    return Math.floor(Math.abs(num) / Math.pow(10, i)) % 10;
}
function digitCount(num) {
    if (num === 0) return 1;
    return Math.floor(Math.log10(Math.abs(num))) + 1;
}
function mostDigits(arr) {
    return Math.max(...arr.map(x => digitCount(x)));
}

const data3 = [6,3,4,2,8,7,1,5];
const data1 = [9,21,54,15,62,6,18,86,61,1,54,62,78,35,13,58,12,25,38,40,7,130];
const data2 = Array.apply(null, {length:100000}).map(Function.call, () => Math.floor(Math.random * 100000));
console.time('sort');
const sorted = radixSort(data1);
console.timeEnd('sort');
console.log(sorted);
