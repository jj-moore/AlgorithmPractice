class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
  }
}

// push, pop, shift, unshift, get, set, insert, remove, reverse, print
class SinglyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  push(val) {
    const newNode = new Node(val);
    if (!this.head) {
      this.head = newNode;
      this.tail = this.head;
    }
    else {
      this.tail.next = newNode;
      this.tail = this.tail.next;
    }
    this.length++;
    return this;
  }

  pop() {
    if (!this.head) {
      return undefined;
    }

    let current = this.head;
    let previous = current;
    while (current.next) {
      previous = current;
      current = current.next;
    }

    this.tail = previous;
    previous.next = null;
    this.length--;

    if (this.length === 0) {
      this.head = this.tail = null;
    }
    return current;
  }

  shift() {
    if (!this.head) {
      return undefined;
    }
    const current = this.head;
    this.head = current.next;
    this.length--;
    if (this.length === 0) {
      this.tail = null;
    }
    return current;
  }

  unshift(val) {
    const newHead = new Node(val);
    newHead.next = this.head;
    this.head = newHead;
    ++this.length === 1 ? this.tail = newHead : null;
    return this;
  }

  get(index) {
    if (!Number.isInteger(index) || index < 0 || index >= list.length) {
      return undefined;
    }
    let current = this.head;
    while (index--) {
      current = current.next;
    }
    return current;
  }

  set(index, val) {
    const node = this.get(index);
    if (node) {
      node.val = val;
    }
    return !!node;
  }

  insert(index, val) {
    if (index < 0 || index > this.length) {
      return false;
    }
    if (index === 0) {
      return !!this.unshift(val);
    }
    if (index === this.length) {
      return !!this.push(val);
    }

    const newNode = new Node(val);
    const previous = this.get(index - 1);
    newNode.next = previous.next;
    previous.next = newNode;
    this.length++;
    return true;
  }

  remove(index) {
    if (index < 0 || index >= this.length) {
      return undefined;
    }
    if (index === 0) {
      return this.shift();
    }
    if (index === this.length - 1) {
      return this.pop();
    }
    const previous = this.get(index - 1);
    const removed = previous.next;
    previous.next = removed.next;
    this.length--;
    return removed;
  }

  reverse() {
    let current = this.head;
    let previous = null;
    let next = null;

    while (current) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    this.tail = this.head;
    this.head = previous;
    return this;
  }

  print() {
    let arr = [];
    let current = this.head;
    while(current) {
      arr.push(current.val);
      current = current.next;
    }
    console.log(arr);
  }
}

const list = new SinglyLinkedList();
list.push(100);
list.push(201);
list.push(250);
list.push(350);
list.push(999);
list.print();
list.reverse();
list.print();
