package com.company;

import java.io.*;
import java.util.Objects;

class Library {
    static DynamicArray members = new DynamicArray();
    //    static Member[] members = new Member[200];
    static Manager[] theManager = new Manager[1];

    static DynamicArray booksList = new DynamicArray();
//    static Book[] booksList = new Book[1000];

    static DynamicArray journalsList = new DynamicArray();
    //    static Journal[] journalsList = new Journal[1000];
    static int currentMember = 0;
    static int counterM = 0;
    static int counterB = 0;
    static int counterJ = 0;

    static void printLine() {
        System.out.println("----------");
    }

    static boolean memAuth(String user, String pass) {
        for (int i = 0; i < Library.members.length; i++) {
            Member mem = (Member) members.getA_index(i);
            if (mem != null) {
                if (Objects.equals(user, mem.getUsername()) && Objects.equals(pass, mem.getPassword())) {
                    currentMember = mem.getId() - 1;
                    return true;
                }
            }
        }
        return false;
    }

    static void setCurrentMember(int id) {
        currentMember = id - 1;
    }

    static boolean manAuth(String user, String pass) {
        return Objects.equals(user, Library.theManager[0].getUsername()) && Objects.equals(pass, Library.theManager[0].getPassword());
    }

    public static void addMember(String username, String password) {
        Member m = new Member(username, password);
        members.add(m);
        counterM++;
    }

    public static void addMember2(String user, String pass, int id, int borrowC) {
        Member m = new Member(user, pass, id, borrowC);
        members.add(m);
        counterM++;
    }

    public static void setManager(String username, String password) {
        theManager[0] = new Manager(username, password);
    }

    public static void addBook(String name, String writer, String category, int id, int stockCount) {
        Book b = new Book(name, writer, category, id, stockCount);
        booksList.add(b);
        counterB++;
    }

    public static void addJournal(String name, int jNum, String date, String category, int id, int stockCount) {
        Journal j = new Journal(name, jNum, date, category, id, stockCount);
        journalsList.add(j);
        counterJ++;
    }

    public static void showAll() {
        System.out.println("Books:");
        for (int i = 0; i < Library.booksList.length; i++) {
            Book b = (Book) booksList.getA_index(i);
            if (b == null) {
                break;
            }
            System.out.println("name: " + b.getName() + " - ID: " + b.getId() + " - stock: " + b.getStock());
        }
        Library.printLine();
        System.out.println("Journals:");
        for (int i = 0; i < Library.journalsList.length; i++) {
            Journal j = (Journal) journalsList.getA_index(i);
            if (j == null) {
                break;
            }
            System.out.println("name: " + j.getName() + " - ID: " + j.getId() + " - stock: " + j.getStock());
        }
    }

    public static void borrowBook(int id) {
        for (int i = 0; i < booksList.length; i++) {
            try {
                Book b = (Book) booksList.getA_index(i);
                if (id == b.getId()) {
                    if (b.getStock() == 0) {
                        System.out.println("Book is out of stock! Can't be borrowed");
                        break;
                    }
                    Member mem = (Member) members.getA_index(currentMember);
                    mem.borrowBook(b);
                    b.setStockCount(b.getStock() - 1);
                    break;
                } else if (i == booksList.length - 1) {
                    System.out.println("no books with that id!");
                    System.out.println("try again");
                    Library.printLine();
                }
            } catch (NullPointerException e) {
                System.out.println("no books with that id!");
                System.out.println("try again");
                Library.printLine();
                break;
            }
        }
    }

    public static void borrowJournal(int id) {
        for (int i = 0; i < journalsList.length; i++) {
            try {
                Journal j = (Journal) journalsList.getA_index(i);
                if (id == j.getId()) {
                    if (j.getStock() == 0) {
                        System.out.println("Journal is out of stock! Can't be borrowed \n");
                        break;
                    }
                    Member mem = (Member) members.getA_index(currentMember);
                    mem.borrowJournal(j);
                    j.setStockCount(j.getStock() - 1);
                    break;
                } else if (i == journalsList.length - 1) {
                    System.out.println("no journals with that id!");
                    System.out.println("try again");
                    Library.printLine();
                }
            } catch (NullPointerException e) {
                System.out.println("no journals with that id!");
                System.out.println("try again");
                Library.printLine();
                break;
            }
        }
    }

    public static void borrowed() {
        for (int i = 0; i < 5; i++) {
            try {
                Member mem = (Member) members.getA_index(currentMember);
                if (mem.getBorrowedBooks(i) != null) {
                    System.out.println((i + 1) + " " + mem.getBorrowedBooks(i).getName() + " " + mem.getBorrowedBooks(i).getType());
                } else {
                    break;
                }
            } catch (NullPointerException e) {
                System.out.println("empty");
                break;
            }

        }
    }

    public static void returnBook(int id) {
        try {
            Member mem = (Member) members.getA_index(currentMember);
            mem.returnBook(id);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("wrong number or empty");
        }
    }

    public static void updateStock(Member mem, int id) {
        Incription b = mem.getBorrowedBooks(id);
        b.setStockCount(b.getStock() + 1);

    }

    public static void removeBook(int id) {
        boolean continueLoop = true;
        for (int i = 0; i < Library.booksList.length; i++) {
            boolean flag = true;
            if (!continueLoop) {
                break;
            }
            try {
                for (int j = 0; j < Library.members.length; j++) {
                    try {
                        Member mem = (Member) Library.members.getA_index(j);
                        if (mem == null) {
                            break;
                        }
                        for (int k = 0; k < 5; k++) {
                            if (mem.getBorrowedBooks(k) == null) {
                                break;
                            }
                            if (mem.getBorrowedBooks(k).getId() == id) {
                                System.out.println("This book is borrowed by " + mem.getUsername() + ", you can't delete it");
                                flag = false;
                                continueLoop = false;
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        break;
                    }
                }
                Book b = (Book) Library.booksList.getA_index(i);
                if (b.getId() == id && flag) {
                    Library.booksList.remove(b);
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println("Error");
                break;
            }
        }

    }

    public static void writeData() {
        try {
            FileWriter writer = new FileWriter("members.txt");
            for (int i = 0; i < Library.members.length; i++) {
                Member mem = (Member) Library.members.getA_index(i);
                if (mem == null) {
                    break;
                }
                writer.write(mem.getUsername() + "," + mem.getPassword() + "," + mem.getId() + "," + mem.getBorrowC() + ",");
                for (int j = 0; j < 5; j++) {
                    try {
                        if (mem.getBorrowedBooks(j) == null) {
                            break;
                        }
                        if (Objects.equals(mem.getBorrowedBooks(j).getType(), "(Book)")) {
                            writer.append("" + mem.getBorrowedBooks(j).getId() + "b&");
                        } else if (Objects.equals(mem.getBorrowedBooks(j).getType(), "(Journal)")) {
                            writer.append("" + mem.getBorrowedBooks(j).getId() + "j&");
                        }
                        updateStock(mem, j);
                    } catch (NullPointerException e) {
                        break;
                    }
                }
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("books.txt");
            for (int i = 0; i < Library.booksList.length; i++) {
                Book b = (Book) Library.booksList.getA_index(i);
                if (b == null) {
                    break;
                }
                writer.write(b.getName() + "," + b.getWriter() + "," + b.getCategory() + "," + b.getId() + "," + b.getStock());
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("journals.txt");
            for (int i = 0; i < Library.journalsList.length; i++) {
                Journal j = (Journal) Library.journalsList.getA_index(i);
                if (j == null) {
                    break;
                }
                writer.write(j.getName() + "," + j.getjNum() + "," + j.getpDate() + "," + j.getCategory() + "," + j.getId() + "," + j.getStock());
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {

        try {
            File read = new File("books.txt");
            read.createNewFile();
            FileReader reader = new FileReader("books.txt");
            int data = reader.read();
            while (data != -1) {
                String bookData = "";
                while (data != '\n') {
                    char d = (char) data;
                    bookData += d;
                    data = reader.read();
                }
                String[] books = bookData.split(",");
                String name = books[0];
                String writer = books[1];
                String category = books[2];
                int id = Integer.parseInt(books[3]);
                int stock = Integer.parseInt(books[4]);
                data = reader.read();
                Library.addBook(name, writer, category, id, stock);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File read = new File("journals.txt");
            read.createNewFile();
            FileReader reader = new FileReader("journals.txt");
            int data = reader.read();
            while (data != -1) {
                String bookData = "";
                while (data != '\n') {
                    char d = (char) data;
                    bookData += d;
                    data = reader.read();
                }
                String[] books = bookData.split(",");
                String name = books[0];
                int jNum = Integer.parseInt(books[1]);
                String date = books[2];
                String category = books[3];
                int id = Integer.parseInt(books[4]);
                int stock = Integer.parseInt(books[5]);
                data = reader.read();
                Library.addJournal(name, jNum, date, category, id, stock);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File read = new File("members.txt");
            read.createNewFile();
            FileReader reader = new FileReader("members.txt");
            int data = reader.read();
            while (data != -1) {
                String member = "";
                while (data != '\n') {
                    char d = (char) data;
                    member += d;
                    data = reader.read();
                }
                String[] datas = member.split(",");
                String user = datas[0];
                String pass = datas[1];
                int id = Integer.parseInt(datas[2]);
                int borrowC = 0;
                data = reader.read();

                Library.addMember2(user, pass, id, borrowC);
                Library.setCurrentMember(id);

                try {
                    if (datas[4] != null) {
                        String[] books = datas[4].split("&");
                        for (int j = 0; j < 5; j++) {
                            String book = books[j];
                            if (book != null && Objects.equals(book.charAt(book.length() - 1), 'b')) {
                                Library.borrowBook(Integer.parseInt(book.replace("b", "")));
                            } else if (book != null && Objects.equals(book.charAt(book.length() - 1), 'j')) {
                                Library.borrowJournal(Integer.parseInt(book.replace("j", "")));
                            } else {
                                break;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
