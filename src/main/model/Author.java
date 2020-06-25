package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {
    private int id;
    private String name;
    private String contacts;

    private final List<Book> books; // список книг автора

    public Author(int id, String name, String contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
        books = new ArrayList<>(); // задаем пустой список книг автора

    }

    public List<Book> getBooks() {
        return books;
    }

    public String getContacts() {
        return contacts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return id == author.id &&
                name.equals(author.name) &&
                Objects.equals(contacts, author.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contacts);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts='" + contacts + '\'' +
                '}';
    }
}
