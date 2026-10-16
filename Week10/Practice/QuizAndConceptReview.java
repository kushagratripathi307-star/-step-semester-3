/**
 * Week 10 - Category B - Practice Problems
 * Program: Quiz and Concept Questions Reviewer
 *
 * Task:
 * Provides an interactive summary of Part B (Quiz Questions 1-20)
 * and Part C (Concept Questions 1-10) with complete answers,
 * rationale, and core object-oriented principles.
 */
public class QuizAndConceptReview {

    public static void printQuizAnswers() {
        System.out.println("==================================================");
        System.out.println("PART B: QUIZ QUESTIONS (1 - 20) ANSWERS & REASONS");
        System.out.println("==================================================");

        String[][] quizData = {
                {"Q1", "Accessible members in Student (extends Person)", "B", "# age and + getName() (protected and public members)"},
                {"Q2", "Invalid class inheritance structure", "C", "Diagram 3 (Class S cannot extend both Q and R; Java supports single class inheritance)"},
                {"Q3", "Constructor invocation sequence for new Manager()", "B", "Person -> Employee -> Manager (base class constructors execute first)"},
                {"Q4", "Accessing Base members in Derived", "C", "Derived can directly access protectedField"},
                {"Q5", "Vehicle ref points to Bus object; fare() called", "B", "The fare() method defined in Bus (runtime polymorphism)"},
                {"Q6", "Overloading vs Overriding in Printer/SpecialPrinter", "B", "print(String)/print(int) in Printer is overloading; SpecialPrinter's print(String) is overriding"},
                {"Q7", "Shape s = new Circle(); accessible members", "A", "Only members defined in the Shape class (checked via reference type)"},
                {"Q8", "Shape[] shapes loop calling area()", "C", "Circle.area() called for shapes[0], Square.area() for shapes[1]"},
                {"Q9", "Casting Account a = new Current()", "B", "s = (Savings) a will fail, c = (Current) a will succeed"},
                {"Q10", "Processing Plan[] loop calling bill()", "B", "Allows uniform processing through base-type reference without type-specific conditional logic"},
                {"Q11", "Printer essential behavior vs hidden details", "A", "printDocument() is essential behavior; internalBuffer, loadBuffer(), sendToPrintHead() are hidden details"},
                {"Q12", "Vehicle abstract class and Car subclass", "B", "A Vehicle reference can refer to a Car object"},
                {"Q13", "SavingsAccount not implementing calculateFee()", "B", "SavingsAccount must also be declared as an abstract class"},
                {"Q14", "Report subclass scenario causing compilation error", "B", "MonthlyReport attempting to be concrete without implementing abstract method generateBody()"},
                {"Q15", "Payable interface specification", "A", "Specifies operations a type promises to provide, but not per-object state or direct implementation"},
                {"Q16", "Loggable interface implicit visibility and modifiers", "B", "logMessage() and getLogLevel() are implicitly public abstract"},
                {"Q17", "DataProcessor realizes Serializable & Resettable", "C", "Both serialize() from Serializable and reset() from Resettable"},
                {"Q18", "Protocol for unrelated classes without state/default impl", "C", "Box C (Interface)"},
                {"Q19", "Classifying relationships Dog/Animal and Eagle/Flyable", "B", "Dog IS-A Animal (generalization); Eagle CAN-DO Flyable (realization)"},
                {"Q20", "Common methods inherited from root Object class", "A", "equals(), hashCode(), toString()"}
        };

        for (String[] q : quizData) {
            System.out.println(q[0] + ": " + q[1]);
            System.out.println("   Option: " + q[2] + " | Reason: " + q[3]);
        }
    }

    public static void printConceptAnswers() {
        System.out.println("\n==================================================");
        System.out.println("PART C: CONCEPT QUESTIONS (1 - 10) SUMMARY");
        System.out.println("==================================================");

        String[] concepts = {
                "Q1 (Overloading vs Overriding): Overloading is resolved at compile time based on parameter signatures; Overriding is resolved at runtime via dynamic method dispatch.",
                "Q2 (Constructor Chaining): Superclass constructors execute first up the hierarchy. Derived constructors pass arguments to the base constructor using super(...).",
                "Q3 (Class vs Abstract vs Interface): Concrete classes can be instantiated and hold state; Abstract classes hold state and can have abstract methods; Interfaces define contracts without instance state.",
                "Q4 (Identical Interface Methods): A single method implementation in the concrete class simultaneously satisfies both interface contracts.",
                "Q5 (equals and hashCode Contract): Must both be overridden so equal objects hash to identical buckets in hash-based collections (HashSet, HashMap).",
                "Q6 (Shallow vs Deep Copy): Shallow copy duplicates object references, causing unintended shared mutation; Deep copy clones nested objects independently.",
                "Q7 (Inner Class vs Static Nested Class): Inner classes hold a reference to the outer instance; Static nested classes are independent and cannot access outer instance fields directly.",
                "Q8 (Array Time Complexity): Element access by index is O(1) via base + offset formula; Insertion/deletion in the middle is O(n) due to shifting elements.",
                "Q9 (Dynamic Method Dispatch): Allows an array of base-type references to call overridden methods on derived instances uniformly using runtime vtables.",
                "Q10 (IS-A vs CAN-DO): Use IS-A (inheritance) for specialized subtypes; Use CAN-DO (interfaces) for capabilities shared across unrelated hierarchies (e.g. Flyable)."
        };

        for (String c : concepts) {
            System.out.println("• " + c);
        }
    }

    public static void main(String[] args) {
        printQuizAnswers();
        printConceptAnswers();
    }
}
