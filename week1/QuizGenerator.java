import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public boolean isCorrect(int answer) {
        return answer == correctOption;
    }
}

class Quiz {
    private String title;
    private List<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getQuestionText());
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println("  " + (j + 1) + ": " + options.get(j));
            }
            System.out.print("Enter your answer (1-" + options.size() + "): ");
            int answer = scanner.nextInt();
            if (question.isCorrect(answer)) score++;
        }
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

public class QuizGenerator {
    private static List<Quiz> quizzes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create Quiz\n2. Add Question\n3. Take Quiz\n4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createQuiz();
                case 2 -> addQuestionToQuiz();
                case 3 -> takeQuiz();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void createQuiz() {
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        quizzes.add(new Quiz(title));
    }

    private static void addQuestionToQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }
        System.out.println("Select a quiz:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
        int quizChoice = scanner.nextInt();
        scanner.nextLine();
        Quiz quiz = quizzes.get(quizChoice - 1);

        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        System.out.print("Enter options separated by commas: ");
        List<String> options = Arrays.asList(scanner.nextLine().split(","));
        System.out.print("Enter the correct option number (1-" + options.size() + "): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine();
        quiz.addQuestion(new Question(questionText, options, correctOption));
    }

    private static void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }
        System.out.println("Select a quiz:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
        int quizChoice = scanner.nextInt();
        scanner.nextLine();
        quizzes.get(quizChoice - 1).takeQuiz();
    }
}
