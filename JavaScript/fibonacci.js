function fibonacci(n, cache=[undefined, 1, 1]) {
  if (n < 1) {
    return 0;
  }
  if (!cache[n]) {
    cache[n] = fibonacci(n - 1, cache) + fibonacci(n - 2, cache);
  }
  return cache[n];
}
console.time('fib');
console.log(fibonacci(100));
console.timeEnd('fib');
