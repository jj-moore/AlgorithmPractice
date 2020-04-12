const myString = "harold said haha in hamburg";

function searchString(source, target) {
    let matches = 0;
    let temp = 0;
    for (var char of source) {
        if (char == target[temp++]) {
            if (temp == target.length) {
                matches++;
                temp = 0;
            }
        } else {
            temp = 0;
        }
    }
    return matches;
}

const count = searchString(myString, "ha");
console.log(count);
