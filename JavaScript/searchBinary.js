const states = ['Alabama','Alaska','American Samoa','Arizona','Arkansas','California','Colorado','Connecticut','Delaware','District of Columbia','Federated States of Micronesia','Florida','Georgia','Guam','Hawaii','Idaho','Illinois','Indiana','Iowa','Kansas','Kentucky','Louisiana','Maine','Marshall Islands','Maryland','Massachusetts','Michigan','Minnesota','Mississippi','Missouri','Montana','Nebraska','Nevada','New Hampshire','New Jersey','New Mexico','New York','North Carolina','North Dakota','Northern Mariana Islands','Ohio','Oklahoma','Oregon','Palau','Pennsylvania','Puerto Rico','Rhode Island','South Carolina','South Dakota','Tennessee','Texas','Utah','Vermont','Virgin Island','Virginia','Washington','West Virginia','Wisconsin','Wyoming'];

const target = 'Wyomin';

function binarySearch(array, target) {
    return binarySearchAlgorithm(array, target, 0, array.length);
}

function binarySearchAlgorithm(array, target, low, high) {
    const mid = Math.floor((high + low)/2);
    if (array[mid] === target) {
        return mid;
    } else if (low === high) {
        return -1;
    } else if (array[mid] > target) {
        high = mid - 1;
    } else {
        low = mid + 1;
    }
    return binarySearchAlgorithm(array, target, low, high);
}

const index = binarySearch(states, target);
console.log(index);
