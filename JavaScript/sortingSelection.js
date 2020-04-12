function selectionSort(arr) {
    const swap = (arr, idx1, idx2) => {
        [arr[idx1], arr[idx2]] = [arr[idx2], arr[idx1]];
    }
    for (let i = 0; i < arr.length; i++) {
        let smallest = i;
        for (let j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[smallest]) {
                smallest = j;
            }
        }
        if (i != smallest) {
            swap(arr, i, smallest);
        }
    }
    return arr;
}

const data1 = [9,21,54,15,11,6,18,86,61,1,54,62,78,35,13,58,12,25,38,40,7];
const data2 = Array.apply(null, {length:100000}).map(Function.call, Math.random);
console.time('sort');
const sorted = selectionSort(data1);
console.timeEnd('sort');
console.log(sorted);
