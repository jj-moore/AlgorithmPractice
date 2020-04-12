

function mergeSort(arr) {
        if (arr.length <= 1) {
            return arr;
        }
        const mid = Math.floor(arr.length / 2);
        const left = mergeSort(arr.slice(0, mid));
        const right = mergeSort(arr.slice(mid));

        let i = 0;
        let j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[i + j] = left[i++];
            } else {
                arr[i + j] = right[j++];
            }
        }
        while (i < left.length) {
            arr[i + j] = left[i++];
        }
        while(j < right.length) {
            arr[i + j] = right[j++];
        }
        return arr;
}

const data1 = [9,21,54,15,11,6,18,86,61,1,54,62,78,35,13,58,12,25,38,40,7,130];
const data2 = Array.apply(null, {length:100000}).map(Function.call, Math.random);
console.time('sort');
const sorted = mergeSort(data2);
console.timeEnd('sort');
//console.log(sorted);
