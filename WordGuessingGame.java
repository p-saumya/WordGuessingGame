import java.util.Scanner;

class KnightPosition {
    int x, y, distance;

    public KnightPosition(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

public class WordGuessingGame {

    static String randomWord;
    static int remainingGuesses = 10;
    static boolean won = false;
    static StringBuilder hiddenWord;
    static String[] movies = {"morbius", "moonknight", "eternals", "wandavision", "venom", "deadpool", "logan", "daredevil", "avengers", "elektra", "flash", "aquaman", "superman", "antman", "spiderman"};
    static String[] animals = {"lion", "tiger", "monkey", "giraffe", "deer", "cow", "goat", "cheetah", "buffalo", "camel", "bear", "panda", "elephant", "rabbit", "hippopotamus"};
    static String[] food = {"burger", "sandwich", "pizza", "waffle", "pancake", "pastry", "jalebi", "samosa", "manchurian", "noodles", "biryani", "dhokla", "vadapav", "paratha", "panipuri"};
    static String[] places = {"adipur", "rajkot", "surat", "ahmedabad", "gandhinagar", "kutch", "mumbai", "delhi", "kolkata", "goa", "junagadh", "pune", "banglore", "hyderabad", "indore"};

    static void printRules() {
        System.out.println("The rules of the game are:");
        System.out.println("1. A word will be randomly selected from our database, and you need to guess the word.");
        System.out.println("2. The vowels of the word will be visible to you.");
        System.out.println("3. You will have 10 tries to guess the word.");
        System.out.println("4. You will get a hint after 5 wrong guesses if you want, but if you take a hint, your remaining guesses will reduce by three.");
    }

    // Start method generates a random word from the array
    static void start() {
        Scanner sc = new Scanner(System.in);
        int category = 0;
        do {
            System.out.println("What do you want to guess?");
            System.out.println("Enter 1 for guessing a movie");
            System.out.println("Enter 2 for guessing an animal");
            System.out.println("Enter 3 for guessing a food dish");
            System.out.println("Enter 4 for guessing a city");
            category = sc.nextInt();
        } while (category < 1 || category > 4);

        String[] selectedArray;
        switch (category) {
            case 1 -> {
                System.out.println("Nice, you have selected a random movie");
                selectedArray = movies;
            }
            case 2 -> {
                System.out.println("Nice, you have selected a random animal");
                selectedArray = animals;
            }
            case 3 -> {
                System.out.println("Nice, you have selected a random food item");
                selectedArray = food;
            }
            default -> {
                System.out.println("Nice, you have selected a random city");
                selectedArray = places;
            }
        }

        randomWord = selectedArray[(int) (Math.random() * selectedArray.length)];
        hiddenWord = new StringBuilder(randomWord.replaceAll("[^aeiou]", "_")); // Display vowels
        System.out.println("The word has " + randomWord.length() + " letters.");
        System.out.println("You have " + remainingGuesses + " guesses left.");
    }

    // Method to update and display the current state of the guessed word
    static void updateHiddenWord(char guessedLetter) {
        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == guessedLetter) {
                hiddenWord.setCharAt(i, guessedLetter);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printRules();
        start();

        while (remainingGuesses > 0 && !won) {
            System.out.println("Current word: " + hiddenWord);
            System.out.println("Guess a letter:");
            char guess = sc.next().toLowerCase().charAt(0);

            if (randomWord.indexOf(guess) != -1) {
                System.out.println("Correct!");
                updateHiddenWord(guess);
            } else {
                remainingGuesses--;
                System.out.println("Wrong! You have " + remainingGuesses + " guesses remaining.");
            }

            if (hiddenWord.toString().equals(randomWord)) {
                won = true;
                System.out.println("Congratulations, you won! The word was " + randomWord);
                break;
            }

            if (remainingGuesses == 5) {
                System.out.println("Do you want a hint? (y/n)");
                char hintChoice = sc.next().toLowerCase().charAt(0);
                if (hintChoice == 'y') {
                    System.out.println("You have chosen to take a hint");
                    remainingGuesses -= 3;
                    for (int i = 0; i < hiddenWord.length(); i++) {
                        if (hiddenWord.charAt(i) == '_') {
                            hiddenWord.setCharAt(i, randomWord.charAt(i));
                            break;
                        }
                    }
                    System.out.println("Your remaining guesses are reduced by 3. Now you have " + remainingGuesses + " guesses remaining.");
                } else {
                    System.out.println("You have chosen not to take a hint");
                }
            }
        }

        if (!won) {
            System.out.println("Sorry, you lost. The word was " + randomWord);
        }
    }
}
