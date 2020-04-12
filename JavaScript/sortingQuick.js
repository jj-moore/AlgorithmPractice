
function quickSort(arr, left = 0, right = arr.length) {
    if (left < right) {
        let pivotIndex = pivot(arr, left, right);
        quickSort(arr, left, pivotIndex);
        quickSort(arr, pivotIndex + 1, right);
    }
    return arr;
}

function pivot(arr, left, right) {
    const swap = (arr, idx1, idx2) => {
        [arr[idx1], arr[idx2]] = [arr[idx2], arr[idx1]];
    }
    const pivotValue = arr[left];
    let leftIdx = left;
    let rightIdx = right;
    while (true) {
        while (arr[++leftIdx] < pivotValue);
        while (arr[--rightIdx] > pivotValue);
        if (leftIdx >= rightIdx) {
            break;
        } else {
            swap(arr, leftIdx, rightIdx);
        }
    }
    swap(arr, left, rightIdx);
    return rightIdx;

}

function pivot2(arr, start, end) {
    const swap = (arr, idx1, idx2) => {
        [arr[idx1], arr[idx2]] = [arr[idx2], arr[idx1]];
    }
    const pivotValue = arr[start];
    let swapIndex = start;
    for (let i = start + 1; i < end; i++) {
        if (pivotValue > arr[i]) {
            swapIndex++;
            swap(arr, swapIndex, i);
        }
    }
    swap(arr, start, swapIndex);
    return swapIndex;
}

const data3 = [6,3,4,2,8,7,1,5];
const data1 = [9,21,54,15,62,6,18,86,61,1,54,62,78,35,13,58,12,25,38,40,7,130];
const data2 = Array.apply(null, {length:100000}).map(Function.call, Math.random);
console.time('sort');
const sorted = quickSort(data2);
console.timeEnd('sort');
//console.log(sorted);
