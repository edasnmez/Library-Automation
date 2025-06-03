import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Reader;
import com.library.service.Library;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = Library.getInstance();



    public static void main(String[] args) {
        initializeData();
        boolean exit = false;

        while (!exit) {
            printMenu();
            System.out.print("Bir seçenek giriniz: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // dummy

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    selectBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    listBooksByCategory();
                    break;
                case 6:
                    listBooksByAuthor();
                    break;
                case 7:
                    lendBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 9:
                    addReader();
                    break;
                case 10:
                    exit = true;
                    System.out.println("Çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }


    }
    private static void printMenu() {
        System.out.println("\n--- Kütüphane Sistemi ---");
        System.out.println("0. Ana Menüye Dön");
        System.out.println("1. Yeni kitap ekle");
        System.out.println("2. Kitap seç (ID, isim, yazar ile)");
        System.out.println("3. Kitap bilgilerini güncelle");
        System.out.println("4. Kitap sil");
        System.out.println("5. Kategoriye göre kitapları listele");
        System.out.println("6. Yazara göre kitapları listele");
        System.out.println("7. Kitap ödünç al");
        System.out.println("8. Kitap iade et");
        System.out.println("9. Üye Kayıt");
        System.out.println("10. Çıkış");
    }

    private static void addNewBook() {
        System.out.print("Kitap ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Kitap Adı: ");
        String name = scanner.nextLine();
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Baskı: ");
        String edition = scanner.nextLine();

        Author author = new Author(id,authorName,new ArrayList<>());
        Book book = new Book(id, name, author, price, edition);

        author.getBooks().add(book);

        if (library.newBook(book)) {
            System.out.println("Kitap başarıyla eklendi!");
        } else {
            System.out.println("Bu ID'ye sahip bir kitap zaten var.");
        }
    }


    private static void selectBook() {
        System.out.println("Kitap seçme yöntemi:");
        System.out.println("1. ID ile");
        System.out.println("2. İsim ile");
        System.out.println("3. Yazar ile");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 0) {
            return;
        }

        switch (option) {
            case 1:
                System.out.print("Kitap ID: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                Book bookById = library.showBook(id);
                if (bookById != null) {
                    bookById.display();
                } else {
                    System.out.println("Kitap bulunamadı.");
                }
                break;
            case 2:
                System.out.print("Kitap Adı: ");
                String name = scanner.nextLine();
                List<Book> booksByName = library.getBooks().stream()
                        .filter(b -> b.getName().equalsIgnoreCase(name))
                        .toList();
                booksByName.forEach(Book::display);
                break;
            case 3:
                System.out.print("Yazar Adı: ");
                String authorName = scanner.nextLine();
                List<Book> booksByAuthor = library.getBooks().stream()
                        .filter(b -> b.getAuthor().getName().equalsIgnoreCase(authorName))
                        .toList();
                booksByAuthor.forEach(Book::display);
                break;
            default:
                System.out.println("Geçersiz seçim.");
        }
    }


    private static void updateBook() {
        System.out.print("Güncellenecek Kitap ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Book book = library.showBook(id);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        System.out.print("Yeni Adı (" + book.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) book.setName(newName);

        System.out.print("Yeni Fiyat (" + book.getPrice() + "): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) book.setPrice(Double.parseDouble(priceInput));

        System.out.print("Yeni Baskı (" + book.getEdition() + "): ");
        String edition = scanner.nextLine();
        if (!edition.isEmpty()) book.setEdition(edition);

        System.out.println("Kitap güncellendi!");
        System.out.println("Güncel Hali : " + book);
    }

    private static void deleteBook() {
        System.out.print("Silinecek Kitap ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Book removed = library.showBook(id);
        if (removed != null) {
            library.getBooks().remove(removed);
            System.out.println("Kitap silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }


    private static void listBooksByCategory() {
        System.out.println("Kategori Seçiniz:");
        System.out.println("1. Magazines");
        System.out.println("2. Journals");
        System.out.println("3. Study Books");
        System.out.print("Seçiminiz: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        String selectedCategory ;

        switch (option) {
            case 1:
                selectedCategory = "Magazines";
                break;
            case 2:
                selectedCategory = "Journals";
                break;
            case 3:
                selectedCategory = "Study Books";
                break;
            default:
                System.out.println("Geçersiz seçim.");
                return;
        }

        String finalSelectedCategory = selectedCategory;
        List<Book> booksByCategory = library.getBooks().stream()
                .filter(b -> b.getEdition().equalsIgnoreCase(selectedCategory))
                .sorted(Comparator.comparing(Book::getName))
                .toList();
        if (booksByCategory.isEmpty()) {
            System.out.println(selectedCategory + " kategorisinde kitap bulunamadı.");
        } else {
            System.out.println(selectedCategory + " kategorisindeki kitaplar:");
            booksByCategory.forEach(book -> System.out.println("- " + book.getName()));
        }
    }

    private static void listBooksByAuthor() {
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();
        List<Book> booksByAuthor = library.getBooks().stream()
                .filter(b -> b.getAuthor().getName().equalsIgnoreCase(authorName))
                .sorted(Comparator.comparing(Book::getName))
                .toList();
        booksByAuthor.forEach(book -> System.out.println("- " + book.getName()));
    }

    private static void lendBook() {
        System.out.print("Kitap ID: ");
        Long bookId = scanner.nextLong();
        System.out.print("Kullanıcı ID: ");
        Long readerId = scanner.nextLong();
        scanner.nextLine();

        boolean result = library.lendBook(bookId, readerId);
        if (result) {
            System.out.println("Kitap başarıyla ödünç verildi!");
            Book book = library.showBook(bookId);
            Reader reader = library.getReader(readerId);

            reader.borrowBook(book);

            System.out.println("----- FATURA -----");
            System.out.println(reader.toString());
        } else {
            System.out.println("Ödünç verilemedi.");
        }
    }

    private static void returnBook() {
        System.out.print("Kitap ID: ");
        Long bookId = scanner.nextLong();
        System.out.print("Kullanıcı ID: ");
        Long readerId = scanner.nextLong();
        scanner.nextLine();

        boolean result = library.takeBackBook(bookId, readerId);
        if (result) {
            System.out.println("Kitap başarıyla iade edildi!");
            Reader reader = library.getReader(readerId);
            Book book = library.showBook(bookId);
            if (reader != null && book != null) {
                reader.purchaseBook(book);
            }
        } else {
            System.out.println("İade işlemi başarısız.");
        }
    }

    private static void addReader() {
        System.out.print("Yeni üye ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Ad Soyad: ");
        String name = scanner.nextLine();

        Reader reader = new Reader(id, name, new HashSet<>(), 0.0);
        library.addReader(reader);

        System.out.println("Üye başarıyla eklendi: " + reader);
    }

    private static void initializeData() {

        Reader reader1 = new Reader(1L, "Ahmet", new HashSet<>(), 0.0);
        Reader reader2 = new Reader(2L, "Ayşe", new HashSet<>(), 0.0);
        Reader reader3 = new Reader(3L, "Mehmet", new HashSet<>(), 0.0);
        Reader reader4 = new Reader(4L, "Zeynep", new HashSet<>(), 0.0);

        library.addReader(reader1);
        library.addReader(reader2);
        library.addReader(reader3);
        library.addReader(reader4);


        Author author1 = new Author(1L, "Yaşar Kemal", new ArrayList<>());
        Author author2 = new Author(2L, "Orhan Pamuk", new ArrayList<>());
        Author author3 = new Author(3L, "Sabahattin Ali", new ArrayList<>());

        Book book1 = new Book(101L, "İnce Memed", author1, 45.0, "Magazines");
        Book book2 = new Book(102L, "Kar", author2, 50.0, "Journals");
        Book book3 = new Book(103L, "Kuyucaklı Yusuf", author3, 40.0, "Study Books");
        Book book4 = new Book(104L, "Kürk Mantolu Madonna", author3, 55.0, "Magazines");
        Book book5 = new Book(105L, "Sırça Köşk", author3, 50.0, "Magazines");
        Book book6 = new Book(106L, "Kırmızı Saçlı Kadın", author2, 50.0, "Journals");

        author1.getBooks().add(book1);
        author2.getBooks().add(book2);
        author3.getBooks().add(book3);
        author3.getBooks().add(book4);
        author3.getBooks().add(book5);
        author2.getBooks().add(book6);

        library.newBook(book1);
        library.newBook(book2);
        library.newBook(book3);
        library.newBook(book4);
        library.newBook(book5);
        library.newBook(book6);

        System.out.println("Test verileri başarıyla yüklendi!");
    }



}