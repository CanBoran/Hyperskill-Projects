package readability;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            String text = readFileAsString("./" + args[0]);
            int wordCount = 0;
            int polySyllableCount = 0;
            String[] sentenceArray = text.split("[\\.\\!\\?]");
            int sentenceCount = sentenceArray.length;
            int characterCount = text.replaceAll("\\s", "").length();
            int syllableCount = 0;

            Pattern syllablePattern = Pattern.compile("[bcdfghjklmnpqrstvwxz]*[aeiouy]+[bcdfghjklmnpqrstvwxz]*");
            Matcher m = syllablePattern.matcher(text);
            while (m.find()) {
                syllableCount++;
            }
            String vowelReducedText = text.replaceAll("[aeiouy]+", "*");
            Pattern polySyllablePattern = Pattern.compile("([bcdfghjklmnpqrstvwxz]*[*]+[bcdfghjklmnpqrstvwxz]*){3,}");
            Matcher polyMatcher = polySyllablePattern.matcher(vowelReducedText);
            while (polyMatcher.find()) {
                polySyllableCount++;
            }

            for (String sentence : sentenceArray) {
                String[] words = sentence.trim().split("\\s");
                wordCount += words.length;
            }
            
            System.out.println("The text is:");
            System.out.println(text);
            System.out.println("Words: " + wordCount);
            System.out.println("Sentences: " + sentenceCount);
            System.out.println("Characters: " + characterCount);
            System.out.println("Syllables: " + syllableCount);
            System.out.println("Polysyllables: " + polySyllableCount);
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
            Scanner scanner = new Scanner(System.in);
            String scoreType = scanner.next();

            if (scoreType.equals("all")) {
                double ARIScore = automatedReadabilityScore(wordCount, sentenceCount, characterCount);
                double FKScore = fleschKincaidReadabilitScore(wordCount, sentenceCount, syllableCount);
                double SMOGScore = SMOGReadabilityScore(polySyllableCount, sentenceCount);
                double CLScore = colemanLiauReadabilityScore(wordCount, sentenceCount, characterCount);

                int ARIAge = getAgePerScore(ARIScore);
                int FKAge = getAgePerScore(FKScore);
                int SMOGAge = getAgePerScore(SMOGScore);
                int CLAge = getAgePerScore(CLScore);
                double avgAge = (ARIAge + FKAge + SMOGAge + CLAge) / 4;

                System.out.println("Automated Readability Index: " + ARIScore + " (about " + ARIAge + " year olds).");
                System.out.println("Flesch–Kincaid readability tests: " + FKScore + " (about " + FKAge + " year olds).");
                System.out.println("Simple Measure of Gobbledygook: " + SMOGScore + " (about " + SMOGAge + " year olds).");
                System.out.println("Coleman–Liau index: " + CLScore + " (about " + CLAge + " year olds).");
                System.out.println("\nThis text should be understood in average by " + avgAge + " year olds.");
            } else if (scoreType.equals("ARI")) {
                double ARIScore = automatedReadabilityScore(wordCount, sentenceCount, characterCount);
                System.out.println("Automated Readability Index: " + ARIScore + " (about " + getAgePerScore(ARIScore) + " year olds).");
            } else if (scoreType.equals("FK")) {
                double FKScore = fleschKincaidReadabilitScore(wordCount, sentenceCount, syllableCount);
                System.out.println("Flesch–Kincaid readability tests: " + FKScore + " (about " + getAgePerScore(FKScore) + " year olds).");
            } else if (scoreType.equals("SMOG")) {
                double SMOGScore = SMOGReadabilityScore(polySyllableCount, sentenceCount);
                System.out.println("Simple Measure of Gobbledygook: " + SMOGScore + " (about " + getAgePerScore(SMOGScore) + " year olds).");
            } else if (scoreType.equals("CL")) {
                double CLScore = colemanLiauReadabilityScore(wordCount, sentenceCount, characterCount);
                System.out.println("Coleman–Liau index: " + CLScore + " (about " + getAgePerScore(CLScore) + " year olds).");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double colemanLiauReadabilityScore(int wordCount, int sentenceCount, int characterCount) {
        double L = characterCount / wordCount * 100;
        double S = sentenceCount / wordCount * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

    private static double SMOGReadabilityScore(int polySyllableCount, int sentenceCount) {
        return 1.043 * Math.sqrt(polySyllableCount * 30 / sentenceCount) + 3.1291;
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static double fleschKincaidReadabilitScore(int wordCount, int sentenceCount, int syllableCount) {
        return 0.39 * wordCount / sentenceCount + 11.8 * syllableCount / wordCount - 15.59;
    }
    
    public static double automatedReadabilityScore(int wordCount, int sentenceCount, int characterCount) {
        double score = 4.71 * characterCount / wordCount + 0.5 * wordCount / sentenceCount - 21.43;
        return Math.round(score * 100d) / 100d;
    }
    
    public static int getAgePerScore(double score) {
        int age = 0;
        switch ((int) Math.ceil(score)) {
            case 1:
                age = 6;
                break;
            case 2:
                age = 7;
                break;
            case 3:
                age = 9;
                break;
            case 4:
                age = 10;
                break;
            case 5:
                age = 11;
                break;
            case 6:
                age = 12;
                break;
            case 7:
                age = 13;
                break;
            case 8:
                age = 14;
                break;
            case 9:
                age = 15;
                break;
            case 10:
                age = 16;
                break;
            case 11:
                age = 17;
                break;
            case 12:
                age = 18;
                break;
            case 13:
            case 14:
                age = 24;
                break;
        }
        return age;
    }
}
