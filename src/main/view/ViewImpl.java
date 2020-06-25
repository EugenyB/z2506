package main.view;


import main.model.Author;
import main.model.Book;

import java.util.Collection;
import java.util.List;

public class ViewImpl implements View {

/*
? - wild cards
 */
    @Override
    public void printAll(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Override
    public void printAllAuthors(List<Author> authors) {
        for (Author author : authors) {
            System.out.println(author);

        }
    }

    @Override
    public void printAllBooks(Collection<Book> books) {
        for (Book book : books) {
            System.out.println(book);

        }
    }

    @Override
    public void showBook(Book book) {
        System.out.println("----- Book --------");
        System.out.println(book);
        System.out.println("-------------------");
    }

    @Override
    public void showMenu() {
        System.out.println("1. Show Authors");
        System.out.println("2. Show Books");
        System.out.println("3. Add Author");
        System.out.println("4. Save Authors");
        System.out.println("5. Add Book");
        System.out.println("6. Save Books");
        System.out.println("7. Find Book by id");
        System.out.println("8. Store to CSV");
        System.out.println("0 exit");
    }
}
