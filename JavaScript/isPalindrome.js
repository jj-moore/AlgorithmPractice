// isPalindrome('awesome') // false
// isPalindrome('foobar') // false
// isPalindrome('tacocat') // true
// isPalindrome('toot') // true
// isPalindrome('amanaplanacanalpanama') // true
// isPalindrome('amanaplanacanalpandemonium') // false

function isPalindrome(word){
	if (!word) return word;
	if (word.length <= 1) return true;
	
	if (word[0] == word[word.length - 1]) {
		return isPalindrome(word.slice(1, word.length - 1));
	} else {
		return false;
	}
}

const myWord = process.argv[2];
console.log(isPalindrome(myWord));