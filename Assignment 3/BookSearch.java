import java.util.ArrayList;
import java.util.Scanner;

public class BookSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> books = new ArrayList<>();

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input book titles
        for (int i = 0; i < n; i++) {
            System.out.print("Enter book title " + (i + 1) + ": ");
            String title = sc.nextLine();
            books.add(title);
        }

        // Search word
        System.out.print("Enter word to search: ");
        String searchWord = sc.nextLine().toLowerCase();

        boolean found = false;

        // Search operation
        for (String book : books) {
            if (book.toLowerCase().contains(searchWord)) {
                System.out.println("Book Found: " + book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found.");
        }

        sc.close();
    }
}
