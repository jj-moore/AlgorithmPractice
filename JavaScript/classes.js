class Student {
  constructor(firstName, lastName, year) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.grade = year;
    this.tardies = 0;
  }

  markLate() {
      this.tardies += 1;
      console.log(this.firstName + ' ' + this.lastName + ' ' + this.tardies);
  }

  toString() {
    return this.firstName + ' ' + this.lastName + ' ' + this.tardies;
  }


}

const firstStudent = new Student('Jermy','Moore', 15);
firstStudent.markLate();
firstStudent.markLate();
