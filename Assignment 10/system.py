# ---------------- Address Class ----------------
class Address:
    def __init__(self, street, city, zipCode):
        self.street = street
        self.city = city
        self.zipCode = zipCode

    def display(self):
        return f"{self.street}, {self.city} - {self.zipCode}"


# ---------------- Student Class ----------------
class Student:
    def __init__(self, name, age, address, courses=None):
        self.name = name
        self._age = None   # protected attribute
        self.age = age     # setter will validate
        self.address = address   # HAS-A relationship (Composition)
        self.courses = courses if courses is not None else []

    # -------- Property for age (Validation) --------
    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, value):
        if not isinstance(value, int) or value <= 0 or value > 120:
            raise ValueError("Age must be a valid positive integer (1–120)")
        self._age = value

    # -------- Add Course --------
    def add_course(self, course):
        self.courses.append(course)

    # -------- Display --------
    def display(self):
        print(f"Name: {self.name}")
        print(f"Age: {self.age}")
        print(f"Address: {self.address.display()}")
        print(f"Courses: {', '.join(self.courses) if self.courses else 'None'}")


# ---------------- ScholarshipStudent Class ----------------
class ScholarshipStudent(Student):
    def __init__(self, name, age, address, scholarshipAmount, courses=None):
        super().__init__(name, age, address, courses)
        self.scholarshipAmount = scholarshipAmount

    # -------- Override display --------
    def display(self):
        super().display()
        print(f"Scholarship Amount: ₹{self.scholarshipAmount}")


# ---------------- Testing ----------------
if __name__ == "__main__":
    # Create Address object
    addr = Address("MG Road", "Tezpur", "784001")

    # Create Student object
    s1 = Student("Gaurav", 20, addr)
    s1.add_course("Math")
    s1.add_course("Python")

    print("\n--- Student Details ---")
    s1.display()

    # Mutable behavior check
    print("\nAdding another course...")
    s1.add_course("Data Structures")
    print("Courses after update:", s1.courses)

    # Create ScholarshipStudent object
    s2 = ScholarshipStudent("Anwesha", 21, addr, 50000)
    s2.add_course("AI")
    s2.add_course("ML")

    print("\n--- Scholarship Student Details ---")
    s2.display()