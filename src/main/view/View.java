package main.view;

import main.model.Author;
import main.model.Book;

import java.util.Collection;
import java.util.List;

public interface View {
    /*
        ? - wild cards
         */
    void printAll (List<?> list);

    void printAllAuthors(List<Author> authors);

    void printAllBooks(Collection<Book> books);

    void showBook(Book book);

    void showMenu();
}
