package Week8.Practice.P03_OnlineExaminationSystem;

import java.util.*;

abstract class Question {
    private int questionNumber;
    private String prompt;
    private int points;

    public Question(int questionNumber, String prompt, int points) {
        this.questionNumber = questionNumber;
        this.prompt = prompt;
        this.points = points;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getPrompt() {
        return prompt;
    }

    public int getPoints() {
        return points;
    }

    public abstract boolean isCorrect(String answer);
    public abstract String getQuestionType();
}

class MultipleChoiceQuestion extends Question {
    private String correctOption;

    public MultipleChoiceQuestion(int questionNumber, String prompt, int points, String correctOption) {
        super(questionNumber, prompt, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean isCorrect(String answer) {
        return answer != null && answer.trim().equalsIgnoreCase(correctOption.trim());
    }

    @Override
    public String getQuestionType() {
        return "MCQ";
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(int questionNumber, String prompt, int points, boolean correctAnswer) {
        super(questionNumber, prompt, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        if (answer == null) return false;
        boolean userVal = Boolean.parseBoolean(answer.trim());
        return userVal == correctAnswer;
    }

    @Override
    public String getQuestionType() {
        return "True/False";
    }
}

class ShortAnswerQuestion extends Question {
    private String expectedAnswer;

    public ShortAnswerQuestion(int questionNumber, String prompt, int points, String expectedAnswer) {
        super(questionNumber, prompt, points);
        this.expectedAnswer = expectedAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return answer != null && answer.trim().equalsIgnoreCase(expectedAnswer.trim());
    }

    @Override
    public String getQuestionType() {
        return "Short Answer";
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String title;
    private List<Question> questions;

    public Examination(String title, List<Question> questions) {
        this.title = title;
        this.questions = new ArrayList<>(questions);
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public int getTotalMaxScore() {
        int total = 0;
        for (Question q : questions) {
            total += q.getPoints();
        }
        return total;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        this.answers = new LinkedHashMap<>();
        this.submitted = false;
        System.out.printf("%s started by %s.%n", examination.getTitle(), student.getName());
    }

    public boolean recordAnswer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return false;
        }
        answers.put(questionNumber, answer);
        System.out.printf("Answer recorded for Question %d.%n", questionNumber);
        return true;
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination has already been submitted.");
            return;
        }
        this.submitted = true;

        int totalScore = 0;
        List<String> results = new ArrayList<>();

        for (Question q : examination.getQuestions()) {
            String ans = answers.get(q.getQuestionNumber());
            boolean correct = q.isCorrect(ans);
            int pts = correct ? q.getPoints() : 0;
            totalScore += pts;
            results.add(String.format("Question %d: %s (%d points)",
                    q.getQuestionNumber(), correct ? "Correct" : "Incorrect", pts));
        }

        System.out.printf("%s submitted by %s. Result: %s. Total score: %d/%d.%n",
                examination.getTitle(), student.getName(), String.join(", ", results),
                totalScore, examination.getTotalMaxScore());
    }

    public boolean isSubmitted() {
        return submitted;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        System.out.println("=== Problem 3: Online Examination System ===");

        Question q1 = new MultipleChoiceQuestion(1, "Which data structure uses LIFO?", 5, "C");
        Question q2 = new TrueFalseQuestion(2, "Array access is O(n).", 5, false);

        Examination examA = new Examination("Exam A", List.of(q1, q2));

        Student s1 = new Student("Student 1");

        // Student 1 starts Exam A
        Attempt attempt = new Attempt(s1, examA);

        // Student 1 answers Question 1 (MCQ) with option C
        attempt.recordAnswer(1, "C");

        // Student 1 answers Question 2 (TF) with True
        attempt.recordAnswer(2, "True");

        // Student 1 submits Exam A
        attempt.submit();

        // Student 1 attempts to change answer for Question 1
        attempt.recordAnswer(1, "A");

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                Student s2 = new Student("Student 2");
                Attempt att2 = new Attempt(s2, examA);
                System.out.print("Enter answer for Question 1 (A/B/C/D): ");
                String a1 = scanner.nextLine().trim();
                att2.recordAnswer(1, a1);
                System.out.print("Enter answer for Question 2 (true/false): ");
                String a2 = scanner.nextLine().trim();
                att2.recordAnswer(2, a2);
                att2.submit();
            }
        }
    }
}
