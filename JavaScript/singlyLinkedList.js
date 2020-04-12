class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
  }
}

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
}

const list = new SinglyLinkedList();
list.unshift('1');
list.push('2');
list.push('3');
console.log(list);
console.log(list.set('1', "joe"));
console.log(list);
