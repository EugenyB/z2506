package main;

import main.model.Author;
import main.model.Book;
import main.view.View;
import main.view.ViewImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
	List<Author> authors;
	List<Book> books;
	View view = new ViewImpl();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        authors = readAuthorsFromFile("authors.txt");
        /* переменные authors and books используются в разных частях программы и в разных методах, поэтому их лучше объявлять
		в начале программы */
      //  view.printAll(authors);
        books = readBooksFromFile("books.txt");
       // view.printAll(books);
		for (;;) { // вечный цикл
			switch (menu()) {
				case 1:
					view.printAllAuthors(authors);
					break;
				case 2:
					view.printAllBooks(books);
					break;
				case 3:
					addAuthor();
					break;
				case 4:
					saveAuthors();
					break;
				case 5:
					addBook();
					break;
				case 6:
					saveBooks();
					break;
				case 7:
					int bookId = inputBookId();
					Optional<Book> book = findBookbyId(bookId);
					book.ifPresent(b->view.showBook(b));
//					if (book==null) {
//						System.out.println("not found");
//					} else {
//						view.showBook(book);
//					}
					break;
				case 8:
					writeAuthorsToCSV("authors.csv");
					//writeBooksToCSV("books.csv");
					break;
				case 0:
					return;
			}
		}
    }

	private void writeAuthorsToCSV(String fileName) {
		try (PrintWriter out = new PrintWriter(fileName)) {
			for (Author author : authors) {
				String line = author.getId()+","+author.getName()+","+author.getContacts();
				out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private int inputBookId() {
		System.out.println("Input book id");

		return new Scanner(System.in).nextInt();
	}

	private void saveAuthors() {
		try (PrintWriter printWriter = new PrintWriter("authors.txt")) {
			for (Author author : authors) {
				printWriter.println(author.getId());
				printWriter.println(author.getName());
				printWriter.println(author.getContacts());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	private void addAuthor() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Name: ");
		String name = scanner.nextLine();
		System.out.println(": ");
		String contacts = scanner.nextLine();
		Author author = new Author(id,name,contacts);
		authors.add(author);
	}

	private int menu() {
		view.showMenu();

		return new Scanner(System.in).nextInt();
    }



	private List<Book> readBooksFromFile(String filename) {
        List<Book> books = new ArrayList<>();
		try {
			try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

			  String line; //
				while ((line = reader.readLine()) != null) { // прочитать строку и присвоить ее значение переменной line
					// != null - проверить, не равна ли она 0
			        int id = Integer.parseInt(line);
					String title = reader.readLine();
					int pages = Integer.parseInt(reader.readLine()); // строку в  число
					int idAuthor = Integer.parseInt(reader.readLine());
					Author author = findAuthorbyId(idAuthor);
					Book book = new Book(id, title, pages, author);

					books.add(book);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return books;

	}

	private void addBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Title: ");
		String title = scanner.nextLine();
		System.out.println("Pages: ");
		int pages = scanner.nextInt();
		System.out.println(": ");
		String author = scanner.nextLine();
		Book book = new Book(id,title,pages);
		books.add(book);
	}

	private void saveBooks() {
		try (PrintWriter printWriter = new PrintWriter("books.txt")) {
			for (Book book : books) {
				printWriter.println(book.getId());
				printWriter.println(book.getPages());
				printWriter.println(book.getTitle());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Optional<Book> findBookbyId(int id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return Optional.of(book);
			}
		}
		return Optional.empty();
	}

    private Author findAuthorbyId(int id) {
		for (Author author : authors) { // достать автора из списка Author
			if (author.getId() == id) { // проверить, если у автора id автора равен
				// тому id, который мы ищем
				return author; // если нашли, его надо вернуть
			}
		}
        return null; // если дошли до конца цикла и не нашли нужный id автора
    }

    private List<Author> readAuthorsFromFile(String filename) {
        List<Author> authors = new ArrayList<>();
        // прочитать файл
	/*	File f = new File(filename); // объект, который позволяет узнать, где находится файл, его размер и имя
	но не читать из него или записывать в него
		FileReader fileReader = new FileReader(f); // объявляем объект, который читает из файла
		BufferedReader bufferedReader = new BufferedReader(fileReader); // нужен для того, чтоб указать, что при
		чтении из файла нужно будет считывать не символ, а целые строки
		также создает буфер в памяти для того, чтоб читать большие объемы из файла, тк File - это медленная структура
*/
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
		/* нужно прочитать файл до конца, для этого нужна переменная line - куда будем считывать
		while - считываем строку line из reader.readLine, проверяем, не равна ли она null
		то есть, когда дочитаем до конца файла, то проверка возвращает null
		но если он не null, то заходим внутрь, объявляем переменную int = line,
		преобразовать эту строку в число  */
                String line;
                while ((line = reader.readLine()) != null) {
                    int id = Integer.parseInt(line);
                    String name = reader.readLine();
                    String contacts = reader.readLine();
                    Author author = new Author(id, name, contacts);
                    /* созданный объект Author мы добавляем в список авторов */
                    authors.add(author);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
