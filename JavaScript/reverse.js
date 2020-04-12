function reverse(word){
	if (!word || word.length === 1) return word;
	let reversed = word.slice(-1);
	reversed = reversed.concat(reverse(word.slice(0,-1)));
	return reversed;
}

	let args = process.argv[2];
	console.log(reverse(args));
// reverse('rithmschool') // 'loohcsmhtir'