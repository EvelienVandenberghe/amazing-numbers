package numbers;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {      
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        
        printInstructions();                  // Show Instructions
        
        while (true) {                        // Main loop
            System.out.print("Enter a request: ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                printInstructions();
                continue;
            }
            
            String[] parts = input.split(" ");
            
            long firstNumber;
            try {
                firstNumber = Long.parseLong(parts[0]);                // If parsing fails, show error
            } catch (NumberFormatException e) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
                System.out.println();
                continue;
            }
            
            if (firstNumber == 0) {                         // If O, exit
                System.out.println("\nGoodbye!");
                break;
            }
            
            if (firstNumber < 0) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
                System.out.println();
                continue;
            }
            
            if (parts.length == 1) {                 // Check number of parameters
                printProperties(firstNumber);
            } else if (parts.length == 2) {
                long count;
                try {
                    count = Long.parseLong(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("\nThe second parameter should be a natural number.");
                    System.out.println();
                    continue;
                }
                
                if (count <= 0) {
                    System.out.println("\nThe second parameter should be a natural number.");
                    System.out.println();
                    continue;
                }
                
                printList(firstNumber, count);           // Print list of numbers with properties
            } else {                                   // 3+ parameters 
                long count;                               
                try {
                    count = Long.parseLong(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("\nThe second parameter should be a natural number.");
                    System.out.println();
                    continue;
                }
                
                if (count <= 0) {
                    System.out.println("\nThe second parameter should be a natural number.");
                    System.out.println();
                    continue;
                }
                
                ArrayList<String> includeProperties = new ArrayList<>();          // Get all properties from parts[2] onward
                ArrayList<String> excludeProperties = new ArrayList<>();
                HashSet<String> uniqueInclude = new HashSet<>();         // Use HashSet to remove duplicates
                HashSet<String> uniqueExclude = new HashSet<>();
                
                for (int i = 2; i < parts.length; i++) {
                    String prop = parts[i].toUpperCase();
                    if (prop.startsWith("-")) {
                        uniqueExclude.add(prop.substring(1));                   // Exclude property, remove -
                    } else {
                        uniqueInclude.add(prop);
                    }
                }
                
                includeProperties.addAll(uniqueInclude);
                excludeProperties.addAll(uniqueExclude);
                
                ArrayList<String> invalidProperties = new ArrayList<>();       // Validate all properties
                for (String prop : includeProperties) {
                    if (!isValidProperty(prop)) {
                        invalidProperties.add(prop);
                    }
                }
                for (String prop : excludeProperties) {
                    if (!isValidProperty(prop)) {
                        invalidProperties.add(prop);
                    }
                }
                
                if (!invalidProperties.isEmpty()) {
                    if (invalidProperties.size() == 1) {
                        System.out.println("\nThe property " + invalidProperties + " is wrong.");
                    } else {
                        System.out.println("\nThe properties " + invalidProperties + " are wrong.");
                    }
                    System.out.println("Available properties:");
                    System.out.println("[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                    System.out.println();
                    continue;
                }
                
                String exclusiveError = checkMutuallyExclusive(includeProperties, excludeProperties);                  // Check for mutually exclusive properties
                if (exclusiveError != null) {
                    System.out.println("\n" + exclusiveError);
                    System.out.println("There are no numbers with these properties.");
                    System.out.println();
                    continue;
                }
                
                printListWithProperties(firstNumber, count, includeProperties, excludeProperties);           // Print list filtered by properties
            }
        }
    }
    
    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }
    
    private static boolean isValidProperty(String property) {
        String[] validProperties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD", "EVEN", "ODD"};
        for (String valid : validProperties) {
            if (valid.equals(property)) {
                return true;
            }
        }
        return false;
    }
    
    private static String checkMutuallyExclusive(ArrayList<String> includeProperties, ArrayList<String> excludeProperties) {
        for (String prop : includeProperties) {
            if (excludeProperties.contains(prop)) {
                return "The request contains mutually exclusive properties: [-" + prop + ", " + prop + "]";
            }
        }
        
        if (includeProperties.contains("EVEN") && includeProperties.contains("ODD")) {
            return "The request contains mutually exclusive properties: [EVEN, ODD]";
        }
        
        if (includeProperties.contains("DUCK") && includeProperties.contains("SPY")) {
            return "The request contains mutually exclusive properties: [DUCK, SPY]";
        }
        
        if (includeProperties.contains("SUNNY") && includeProperties.contains("SQUARE")) {
            return "The request contains mutually exclusive properties: [SQUARE, SUNNY]";
        }
        
        if (includeProperties.contains("HAPPY") && includeProperties.contains("SAD")) {
            return "The request contains mutually exclusive properties: [HAPPY, SAD]";
        }
        
        if (excludeProperties.contains("EVEN") && excludeProperties.contains("ODD")) {
            return "The request contains mutually exclusive properties: [-EVEN, -ODD]";
        }
        
        if (excludeProperties.contains("DUCK") && excludeProperties.contains("SPY")) {
            return "The request contains mutually exclusive properties: [-DUCK, -SPY]";
        }
        
        if (excludeProperties.contains("SUNNY") && excludeProperties.contains("SQUARE")) {
            return "The request contains mutually exclusive properties: [-SQUARE, -SUNNY]";
        }
        
        if (excludeProperties.contains("HAPPY") && excludeProperties.contains("SAD")) {
            return "The request contains mutually exclusive properties: [-HAPPY, -SAD]";
        }
        
        return null;
    }
    
    private static void printProperties(long number) {
        boolean isEven = isEven(number);
        boolean isOdd = isOdd(number);
        boolean isBuzz = isBuzz(number);
        boolean isDuck = isDuck(number);
        boolean isPalindromic = isPalindromic(number);
        boolean isGapful = isGapful(number);
        boolean isSpy = isSpy(number);
        boolean isSquare = isSquare(number);
        boolean isSunny = isSunny(number);
        boolean isJumping = isJumping(number);
        boolean isHappy = isHappy(number);
        boolean isSad = !isHappy(number);
        
        System.out.println("\nProperties of " + number);
        System.out.println("        buzz: " + isBuzz);
        System.out.println("        duck: " + isDuck);
        System.out.println(" palindromic: " + isPalindromic);
        System.out.println("      gapful: " + isGapful);
        System.out.println("         spy: " + isSpy);
        System.out.println("      square: " + isSquare);
        System.out.println("       sunny: " + isSunny);
        System.out.println("     jumping: " + isJumping);
        System.out.println("       happy: " + isHappy);
        System.out.println("         sad: " + isSad);
        System.out.println("        even: " + isEven);
        System.out.println("         odd: " + isOdd);
        System.out.println();
    }
    
    private static void printList(long start, long count) {
        System.out.println();
        for (long i = 0; i < count; i++) {             // Loop through count x consecutive numbers from start onward
            long number = start + i;
            printNumberLine(number);
        }
        System.out.println();
    }

    // Print list of numbers filtered by multiple properties
    private static void printListWithProperties(long start, long count, ArrayList<String> includeProperties, ArrayList<String> excludeProperties) {
        System.out.println();
        long found = 0;
        long number = start;
        
        while (found < count) {         // Loop until we find count x numbers 
            if (matchesProperties(number, includeProperties, excludeProperties)) {
                printNumberLine(number);
                found++;
            }
            number++;
        }
        System.out.println();
    }
    
    private static void printNumberLine(long number) {
        ArrayList<String> properties = new ArrayList<>();         // List to store property names
        
        if (isEven(number)) properties.add("even");
        if (isOdd(number)) properties.add("odd");
        if (isBuzz(number)) properties.add("buzz");
        if (isDuck(number)) properties.add("duck");
        if (isPalindromic(number)) properties.add("palindromic");
        if (isGapful(number)) properties.add("gapful");
        if (isSpy(number)) properties.add("spy");
        if (isSquare(number)) properties.add("square");
        if (isSunny(number)) properties.add("sunny");
        if (isJumping(number)) properties.add("jumping");
        if (isHappy(number)) properties.add("happy");
        if (!isHappy(number)) properties.add("sad");
        
        System.out.printf("%16d is ", number);      // Print number with right alignment
        
        for (int j = 0; j < properties.size(); j++) {
            System.out.print(properties.get(j));
            if (j < properties.size() - 1) {            // Add comma if not the last property
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    private static boolean matchesProperties(long number, ArrayList<String> includeProperties, ArrayList<String> excludeProperties) {
        for (String property : includeProperties) {          // Check all include properties
            if (!hasProperty(number, property)) {
                return false;
            }
        }
        
        for (String property : excludeProperties) {         // Check all exclude properties 
            if (hasProperty(number, property)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean hasProperty(long number, String property) {
        switch (property) {
            case "BUZZ":
                return isBuzz(number);
            case "DUCK":
                return isDuck(number);
            case "PALINDROMIC":
                return isPalindromic(number);
            case "GAPFUL":
                return isGapful(number);
            case "SPY":
                return isSpy(number);
            case "SQUARE":
                return isSquare(number);
            case "SUNNY":
                return isSunny(number);
            case "JUMPING":
                return isJumping(number);
            case "HAPPY":
                return isHappy(number);
            case "SAD":
                return !isHappy(number);
            case "EVEN":
                return isEven(number);
            case "ODD":
                return isOdd(number);
            default:
                return false;
        }
    }
    
    private static boolean isEven(long number) {
        return number % 2 == 0;
    }
    
    private static boolean isOdd(long number) {
        return number % 2 != 0;
    }
    
    private static boolean isBuzz(long number) {
        return (number % 7 == 0) || (number % 10 == 7);
    }
    
    private static boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }
    
    private static boolean isPalindromic(long number) {
        String numberStr = String.valueOf(number);
        String reversed = new StringBuilder(numberStr).reverse().toString();
        return numberStr.equals(reversed);
    }
    
    private static boolean isGapful(long number) {
        String numberStr = String.valueOf(number);
        
        if (numberStr.length() < 3) {
            return false;
        }
        
        char firstDigit = numberStr.charAt(0);
        char lastDigit = numberStr.charAt(numberStr.length() - 1);
        
        String concatenated = "" + firstDigit + lastDigit;
        int divisor = Integer.parseInt(concatenated);
        
        return number % divisor == 0;
    }
    
    private static boolean isSpy(long number) {
        String numberStr = String.valueOf(number);
        
        int sum = 0;
        int product = 1;
        
        for (int i = 0; i < numberStr.length(); i++) {
            int digit = numberStr.charAt(i) - '0';
            sum += digit;
            product *= digit;
        }
        
        return sum == product;         // Spy number = sum equals product
    }
    
    private static boolean isSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }
    
    private static boolean isSunny(long number) {
        return isSquare(number + 1);           // A number is sunny if N+1 is a perfect square
    }
    
    private static boolean isJumping(long number) {       
        String numberStr = String.valueOf(number);
        
        if (numberStr.length() == 1) {           // Single-digit numbers are Jumping numbers
            return true;
        }
        
        for (int i = 0; i < numberStr.length() - 1; i++) {               // Check each adjacent pair of digits
            int digit1 = numberStr.charAt(i) - '0';
            int digit2 = numberStr.charAt(i + 1) - '0';
            
            int diff = Math.abs(digit1 - digit2);
            
            if (diff != 1) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isHappy(long number) {
        HashSet<Long> seen = new HashSet<>();             // Use HashSet to detect cycles
        
        while (number != 1 && !seen.contains(number)) {
            seen.add(number);
            number = sumOfSquares(number);
        }
        
        return number == 1;
    }
    
    private static long sumOfSquares(long number) {
        long sum = 0;
        while (number > 0) {
            long digit = number % 10;              // Get last digit
            sum += digit * digit;
            number /= 10;               // Remove last digit
        }
        return sum;
    }
}
