import java.util.Scanner;

public class Question {
    private String questionText;
    private String[] options;
    private Scanner input;

    public Question(String questionText, String[] options, Scanner input) {
        this.questionText = questionText;
        this.options = options;
        this.input = input;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public String getUserResponse() {
        int userInput = -1;

        while (userInput < 1 || userInput > options.length) {
            System.out.print("Please select an option (1 to " + options.length + "): ");
            if (input.hasNextInt()) {
                userInput = input.nextInt();
                if (userInput < 1 || userInput > options.length) {
                    System.out.println("Invalid option. Please select a number between 1 and " + options.length + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        }
        input.nextLine();
        return options[userInput - 1];
    }
}
