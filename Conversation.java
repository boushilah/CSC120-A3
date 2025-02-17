import java.util.Random;
import java.util.Scanner;
class Conversation implements Chatbot {

  // Attributes 
  int n;
  String[] transcript;
  private static final String[] cannedResponses = {
    "Is that so?",
    "That's fascinating!",
    "I see. Can you elaborate?",
    "Interesting, tell me more.",
    "Why do you think that?"
};
// Mirror words mapping
private static final String[][] mirrorWords = {
    {"I", "you"}, {"me", "you"}, {"my", "your"}, {"your", "my"},
    {"you", "I"}, {"am", "are"}, {"are", "am"}
};
  /**
   * Constructor 
   */
  Conversation() {
    System.out.println("Welcome to the Chatbot.");
  }
  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    System.out.println(" Hi! My name is Mata. Nice to meet you! Who do I get the pleasure to talk to today?");
    Scanner input= new Scanner(System.in);
    String name= input.nextLine();
    System.out.println("Hi "+name+"! How many rounds would you like this chat to be?");
    n=input.nextInt();
    input.nextLine();
    transcript = new String[2 * n];
    System.out.println("Tell me about what's on your mind today.");
    String userResponse,botResponse;
    for(int i=0;i<n;i++){
      System.out.print("you: ");
      userResponse = input.nextLine();
      transcript[2 * i] = "you: " + userResponse;
      botResponse = respond(userResponse);
      transcript[2 * i + 1] = "Bot: " + botResponse;
      System.out.println("Bot: " + botResponse);
    }
    input.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nConversation Transcript:");
    System.out.println("");
    for (int i = 0; i < n * 2; i++) {
      System.out.println(transcript[i]);
  }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String[] words = inputString.split("\\s+"); // Split input words into phrases ie. "I am cold" to "I","am","cold".
    boolean hasMirrorWords = false;
    // For loop for checking and replacing mirror words
    for (int i = 0; i < words.length; i++) {
      for (String[] pair : mirrorWords) {
        if (words[i].equalsIgnoreCase(pair[0])) {
          words[i] = pair[1]; // Replace with mirrored word
          hasMirrorWords = true;
          break;
        }
      }
    }
    // If a mirror word is found, reconstruct the sentence
    if (hasMirrorWords) {
      return String.join( " ", words);
    }

    // Otherwise, return a random canned response
    Random rand = new Random();
    return cannedResponses[rand.nextInt(cannedResponses.length)];
  }
  public static void main(String[] arguments) {
    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
