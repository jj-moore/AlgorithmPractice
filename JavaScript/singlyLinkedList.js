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
}

const list = new SinglyLinkedList();
//console.log(list.pop(), list.length);
list.push("Yay!");
console.log(list.pop(), list.length);
list.push("Yay!");
//console.log(list.head, list.length);
list.push('Alright!');
console.log(list.head, list.length);
console.log(list.pop(), list.length);
console.log(list.pop(), list.length);
console.log(list.pop(), list.length);
