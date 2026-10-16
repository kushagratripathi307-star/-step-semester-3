# Week 10 - Category B: Practice Solutions (Quiz & Concept Questions)

---

## Part B — Quiz Answers & Detailed Explanations

### Q1. Accessible Members in Derived Class
- **Question:** If `Student` is a derived type of `Person` (`- name: String`, `# age: int`, `+ getName(): String`), which members are directly accessible within `Student`?
- **Correct Answer:** **B. `# age` and `+ getName()`**
- **Explanation:** In object-oriented programming (specifically Java), `private` members (`- name`) are accessible only within the declaring class. `protected` members (`# age`) and `public` members (`+ getName()`) are directly accessible by derived subclasses.

---

### Q2. Invalid Class Inheritance Structure
- **Question:** Which diagram illustrates an invalid class inheritance structure, assuming a language that supports single class inheritance?
- **Correct Answer:** **C. Diagram 3, where a class attempts to extend two different classes.**
- **Explanation:** In Java and standard single-inheritance OOP models, a class cannot directly inherit from more than one class (`Class S extends Q, R` is illegal). Interfaces must be used to achieve multiple interface realization.

---

### Q3. Constructor Invocation Sequence
- **Question:** In what order will constructors execute when creating `new Manager()`, where `Manager extends Employee` and `Employee extends Person`?
- **Correct Answer:** **B. Person -> Employee -> Manager**
- **Explanation:** Constructor chaining ensures that the root base class constructor executes first (`Person`), initializing base state, followed by each intermediate class (`Employee`), and finally the leaf class (`Manager`).

---

### Q4. Member Access & Overriding from Derived Class
- **Question:** Which statement about accessing Base members or modifying Base operations from within Derived is correct?
- **Correct Answer:** **C. Derived can directly access protectedField.**
- **Explanation:** Protected fields are directly accessible to subclasses. Private fields cannot be accessed directly, `final` methods cannot be overridden, and access modifiers cannot be weakened when overriding.

---

### Q5. Dynamic Method Dispatch with Base Reference
- **Question:** `Vehicle` reference points to a `Bus` object. Both define `fare(): double`. Which version runs?
- **Correct Answer:** **B. The fare() method defined in Bus.**
- **Explanation:** Through runtime polymorphism (dynamic method dispatch), the method executed is determined by the actual runtime type of the object (`Bus`), not the reference type (`Vehicle`).

---

### Q6. Overloading vs Overriding
- **Question:** Distinction between `Printer`'s overloaded `print` methods and `SpecialPrinter`'s redefinition:
- **Correct Answer:** **B. print(text: String) and print(data: int) in Printer are examples of overloading, and SpecialPrinter's print(text: String) is an example of overriding.**
- **Explanation:** Overloading occurs when methods in the same class share a name but differ in parameter signatures. Overriding occurs when a subclass redefines an inherited method with identical signature.

---

### Q7. Member Access After Upcasting
- **Question:** If `Shape s = new Circle();` (upcasting), which members can be accessed directly using `s`?
- **Correct Answer:** **A. Only members defined in the Shape class.**
- **Explanation:** The compiler checks accessible members using the reference type (`Shape`). Subclass-specific members (like `radius` in `Circle`) require explicit downcasting.

---

### Q8. Polymorphic Array Processing
- **Question:** Iterating through `Shape[] shapes = { new Circle(), new Square() };` calling `area()`:
- **Correct Answer:** **C. The area() method specific to Circle will be called for shapes[0], and the area() method specific to Square will be called for shapes[1].**
- **Explanation:** Virtual method invocation ensures that the overridden method corresponding to the actual instance type (`Circle` or `Square`) is executed at runtime.

---

### Q9. Type Casting Evaluation
- **Question:** `Account a = new Current();` Evaluating `s = (Savings) a;` and `c = (Current) a;`:
- **Correct Answer:** **B. s = (Savings) a; will fail, c = (Current) a; will succeed.**
- **Explanation:** The underlying object is an instance of `Current`. Casting `a` to `Current` succeeds. Casting `a` to `Savings` throws a `ClassCastException` at runtime because `Current` is not a `Savings` instance.

---

### Q10. Benefits of Polymorphic Collections
- **Question:** What is the benefit of processing an array of `Plan[]` polymorphically calling `bill()`?
- **Correct Answer:** **B. It allows uniform processing of different derived objects through a base-type reference, without needing type-specific conditional logic.**
- **Explanation:** Polymorphism eliminates tight coupling and messy `instanceof` or `switch` chains, making the code extensible (Open-Closed Principle).

---

### Q11. Encapsulation and Information Hiding
- **Question:** For `Printer` with `printDocument()`, `internalBuffer`, `loadBuffer()`, and `sendToPrintHead()`:
- **Correct Answer:** **A. printDocument() is essential behavior; internalBuffer, loadBuffer(), and sendToPrintHead() are hidden details.**
- **Explanation:** Public methods represent the visible contract/interface (essential behavior), while private fields and private helper methods represent internal implementation details that are encapsulated.

---

### Q12. Abstract Class Instantiation & Referencing
- **Question:** `Vehicle` is an abstract class and `Car` is a concrete subclass:
- **Correct Answer:** **B. A Vehicle reference can refer to a Car object.**
- **Explanation:** Abstract classes cannot be instantiated directly with `new`, but abstract class references can point to instances of concrete subclasses.

---

### Q13. Subclass of Abstract Class Without Implementation
- **Question:** `Account` has abstract operation `calculateFee()`. If `SavingsAccount` does not implement it:
- **Correct Answer:** **B. SavingsAccount must also be declared as an abstract class.**
- **Explanation:** Any concrete subclass must provide implementations for all inherited abstract methods; otherwise, the subclass remains incomplete and must be declared `abstract`.

---

### Q14. Compilation Error in Subclasses
- **Question:** Given abstract `Report` with abstract `generateBody()`, which leads to a compilation error?
- **Correct Answer:** **B. MonthlyReport attempting to be a concrete class without implementing the abstract method generateBody().**
- **Explanation:** A concrete class cannot inherit unimplemented abstract methods without triggering a compiler error.

---

### Q15. Interface Specification
- **Question:** Based on the `Payable` interface, what does an interface fundamentally specify and typically not provide?
- **Correct Answer:** **A. It specifies operations a type promises to provide, but not per-object state or direct implementation.**
- **Explanation:** Traditional interfaces represent pure contracts. They define method signatures that implementing classes promise to fulfill, without instance variables (per-object state).

---

### Q16. Implicit Modifiers in Interfaces
- **Question:** For `Loggable` interface members (`MAX_LOG_SIZE: int = 1024`, `logMessage(...)`, `getLogLevel()`):
- **Correct Answer:** **B. logMessage() and getLogLevel() are implicitly public abstract.**
- **Explanation:** In Java interfaces, methods without bodies are implicitly `public abstract`. Fields are implicitly `public static final`.

---

### Q17. Realizing Multiple Interfaces
- **Question:** `DataProcessor` realizes both `Serializable` (`serialize()`) and `Resettable` (`reset()`):
- **Correct Answer:** **C. Both serialize() from Serializable and reset() from Resettable.**
- **Explanation:** A concrete class realizing multiple interfaces must implement all abstract methods declared across all realized interfaces.

---

### Q18. Protocol Definition for Unrelated Classes
- **Question:** Which construct defines a common protocol for unrelated classes without default implementation or per-object state?
- **Correct Answer:** **C. Box C (Interface)**
- **Explanation:** Interfaces are designed to define capabilities across unrelated class hierarchies without imposing single-inheritance restrictions or instance state.

---

### Q19. Classifying UML Relationships
- **Question:** Classify `Dog --|> Animal` and `Eagle ..|> Flyable`:
- **Correct Answer:** **B. Dog IS-A Animal (generalization); Eagle CAN-DO Flyable (realization).**
- **Explanation:** Generalization (`--|>`) denotes class inheritance ("IS-A"), while realization (`..|>`) denotes interface implementation ("CAN-DO").

---

### Q20. Methods Inherited from Root `Object` Class
- **Question:** Which operations are commonly inherited by default from root `Object` and redefined for custom behavior?
- **Correct Answer:** **A. equals(), hashCode(), toString()**
- **Explanation:** Every Java class inherits `equals()`, `hashCode()`, and `toString()` from `java.lang.Object`, which are commonly overridden to provide content equality and readable string output.

---

## Part C — Concept Questions Answers

### Question 1: Method Overloading vs. Method Overriding
- **Method Overloading:**
  - Occurs within the same class (or across subclasses) where methods share the same name but have different parameter lists (count, types, or order).
  - **Resolution:** Compile-time (Static binding / early binding). The compiler selects the exact method based on the reference type and declared arguments.
- **Method Overriding:**
  - Occurs when a subclass provides a specific implementation of a method already declared in its superclass, with the exact same name, return type, and parameters.
  - **Resolution:** Run-time (Dynamic binding / late binding). The Java Virtual Machine resolves the method based on the actual runtime type of the object pointed to by the reference.

---

### Question 2: Constructor Invocation Sequence & `super()`
- **Sequence:** When an object of a derived class is instantiated, the constructor of the highest superclass (`java.lang.Object`) executes first, followed by each descending class constructor down to the target class constructor.
- **Passing Values:** If a derived class constructor needs to pass values to its base class constructor, it invokes `super(arg1, arg2, ...)` as the very first statement of the derived constructor.

---

### Question 3: Concrete Classes vs. Abstract Classes vs. Interfaces
- **Concrete Class:** Can be instantiated directly; can maintain mutable instance state (fields); provides full implementations for all methods. Chosen when modeling specific, complete domain entities.
- **Abstract Class:** Cannot be directly instantiated; can hold instance state and constructors; can provide both concrete methods and abstract method signatures. Chosen for base models sharing core state and common logic across closely related subclasses (IS-A relationship).
- **Interface:** Cannot be directly instantiated; does not hold per-object instance state (only `public static final` constants); specifies method contracts. Chosen for defining capabilities or protocols across unrelated classes (CAN-DO relationship).

---

### Question 4: Identical Method Signatures in Multiple Interfaces
- When a class implements two interfaces that both declare an abstract method with identical signatures (same name, parameters, and compatible return type), the implementing class provides **a single concrete implementation** of that method.
- **Demonstration:** This demonstrates that interface implementation is contract fulfillment: as long as the concrete class supplies the required method signature, it satisfies both interface contracts simultaneously without conflict.

---

### Question 5: Crucial Role of `equals()` and `hashCode()` Contract
- **Contract Requirement:** The Java specification requires that if two objects are equal according to `equals(Object)`, they **must** return the same integer value from `hashCode()`.
- **Consequences of Violation:**
  - Hash-based collections (e.g., `HashSet`, `HashMap`) determine bucket locations using `hashCode()`.
  - If `equals()` is overridden but `hashCode()` is not, two logically equal objects will produce different hash codes and land in different buckets.
  - As a result, operations like `set.contains(obj)`, `map.get(key)`, and duplicate detection will fail unpredictably, causing memory leaks and duplicate entries.

---

### Question 6: Shallow Copy vs. Deep Copy
- **Shallow Copy:** Duplicates the top-level object, but copies references to nested objects rather than the nested objects themselves. Both copies share references to the same internal mutable objects.
  - *Risk Scenario:* Modifying a nested list or address in the copied object silently mutates the original object.
- **Deep Copy:** Recursively clones the top-level object along with all nested mutable objects, creating completely independent object graphs. Essential when mutations to one instance must never corrupt the other.

---

### Question 7: Inner Type vs. Static Nested Type
- **Inner Class (Non-static nested class):**
  - Associated with an instance of the outer class.
  - Has an implicit reference to the outer instance and can directly access private fields and methods of the enclosing object.
  - *Use Case:* Iterators, event listeners, or helper objects intrinsically bound to a specific outer object.
- **Static Nested Class:**
  - Associated with the outer class itself, not any particular instance.
  - Cannot access instance variables or methods of the enclosing class without an explicit object reference. Can be instantiated independently (`new Outer.Nested()`).
  - *Use Case:* Builder patterns, static helper data transfer objects (DTOs), or node structures (e.g., `Map.Entry`, linked list `Node`).

---

### Question 8: Time Complexity of Array Operations
- **Element Access by Index: $O(1)$**
  - Arrays are stored in contiguous memory blocks. The address of any element at index $i$ is calculated instantaneously via the formula:
    $$\text{Address}(i) = \text{BaseAddress} + (i \times \text{ElementSize})$$
  - Since memory lookup takes constant time regardless of array size, access is $O(1)$.
- **Insertion/Deletion in the Middle: $O(n)$**
  - A fixed-size array requires all subsequent elements to be shifted forward (insertion) or backward (deletion) to maintain contiguous ordering.
  - In the average or worst case, shifting takes $O(n)$ time proportional to the number of elements moved.

---

### Question 9: Uniform Polymorphic Processing & Dynamic Dispatch
- An array of base-type references (e.g., `Vehicle[] fleet`) can store references to any derived type (`Car`, `Truck`, `Bike`).
- In a loop calling `fleet[i].service()`, dynamic method dispatch queries the object's virtual method table (vtable) at runtime to locate and execute the concrete implementation specific to that derived class.
- This allows clean, uniform processing without cumbersome `if-else` or `switch` type checks.

---

### Question 10: IS-A (Inheritance) vs. CAN-DO (Interface) Selection
- **IS-A (Inheritance):** Used when the derived class is a specialized subtype of the base class sharing core identity, attributes, and default behavior (e.g., `Dog IS-A Animal`).
- **CAN-DO (Interface):** Used when defining a capability, behavior, or role that can be adopted by completely unrelated classes across different hierarchies.
- *Example Scenario:* A `Bird` and an `Airplane` are completely different domain models that should not share a common base class, but both CAN-DO flying. Modeling `Flyable` as an interface allows both classes to implement flight capabilities cleanly without artificial inheritance hierarchies.
