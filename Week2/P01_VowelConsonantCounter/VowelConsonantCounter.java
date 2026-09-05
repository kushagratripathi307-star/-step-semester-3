package Week2.P01_VowelConsonantCounter;

public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char lower = Character.toLowerCase(ch);
                if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.printf("Vowels: %d | Consonants: %d\n", vowels, consonants);
    }

    public static void main(String[] args) {
        String text = "Java Programming";
        System.out.printf("\"%s\" -> ", text);
        countVowelsAndConsonants(text);
    }
}
