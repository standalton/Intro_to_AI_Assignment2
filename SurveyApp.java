import java.util.List;
import java.util.Scanner;

public class SurveyApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<String[]> previousResponses = DataStorage.loadResponses();
        NaiveBayes classifier = new NaiveBayes();
        classifier.train(previousResponses);

        Question q1 = new Question("What resonates with you the most?", 
            new String[]{"Magic and Spellcraft", "Dragons and Damsels", "Knowledge and Learning", "Building and Crafting"}, input);

        Question q2 = new Question("What item calls your attention the most?", 
            new String[]{"Magic Wand", "Sword", "Book", "Chisel"}, input);

        Question q3 = new Question("Out of these figures, who inspires you the most?", 
            new String[]{"Merlin", "King Arthur", "Albert Einstein", "MichaelAngelo"}, input);

        Question q4 = new Question("In your free time, what would you most likely find yourself doing?", 
            new String[]{"Learning a Magic Trick", "Exploring new lands", "Learning about a new topic", "Designing a building or invention"}, input);

        String[] userResponses = new String[4];

        q1.displayQuestion();
        userResponses[0] = q1.getUserResponse();

        q2.displayQuestion();
        userResponses[1] = q2.getUserResponse();

        q3.displayQuestion();
        userResponses[2] = q3.getUserResponse();

        q4.displayQuestion();
        userResponses[3] = q4.getUserResponse();

        String predictedParty = classifier.predict(userResponses);
        System.out.println("Based on your answers, we predict you might affiliate with: " + (predictedParty != null ? predictedParty : "No prediction"));

        System.out.println("Which of these political parties do you actually affiliate with?");
        System.out.println("1. Sorcerer's party\n2. Adventurer's Party\n3. Scholar's Party\n4. Mason's Party");

        String party = "";
        while (true) {
            try {
                int partyChoice = Integer.parseInt(input.nextLine().trim());
                switch (partyChoice) {
                    case 1:
                        party = "Sorcerer's party";
                        break;
                    case 2:
                        party = "Adventurer's Party";
                        break;
                    case 3:
                        party = "Scholar's Party";
                        break;
                    case 4:
                        party = "Mason's Party";
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid party.");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        DataStorage.saveResponse(userResponses, party);

        input.close();
    }
}
