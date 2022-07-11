package com.company;

import java.util.Objects;

class Execution {
    public static void loginAsMember() {
        System.out.println("Enter your username and password");
        String muser = Get.getString();
        String mpass = Get.getString();
        boolean logout = true;
        if (!Library.memAuth(muser, mpass)) {
            while (true) {
                System.out.println("Username or password incorrect \n (Retry or Enter '0' to Exit)");
                muser = Get.getString();
                if (Objects.equals(muser, "0")) {
                    logout = false;
                    break;
                }
                mpass = Get.getString();
                if (Library.memAuth(muser, mpass)) {
                    break;
                }
            }
        }
        while (logout) {
            System.out.println("1.Logout");
            System.out.println("2.Borrow a book/journal");
            System.out.println("3.Return a book/journal");
            System.out.println("4.View all borrowed books");
            int x = Get.getInt();
            switch (x) {
                case 1:
                    logout = false;
                    break;
                case 2:
                    Library.printLine();
                    Library.showAll();
                    Library.printLine();
                    System.out.println("1.Borrorw a book");
                    System.out.println("2.Borrorw a journal");
                    int u = Get.getInt();
                    if (u != 1 && u != 2) {
                        System.out.println("!not a valid number!\n");
                        break;
                    }
                    System.out.println("Enter book/journal ID:");
                    int y = Get.getInt();
                    if (u == 1)
                        Library.borrowBook(y);
                    else if (u == 2)
                        Library.borrowJournal(y);
                    else
                        System.out.println("!not a valid number!\n");
                    break;
                case 3:
                    Library.printLine();
                    System.out.println("(Enter the NUMBER of the book in this list:)");
                    Library.borrowed();
                    Library.printLine();
                    System.out.println("Enter the book/journal Number:");
                    int z = Get.getInt();
                    Library.returnBook(z);
                    break;
                case 4:
                    Library.printLine();
                    Library.borrowed();
                    Library.printLine();
                    break;
            }
        }
    }

    public static void loginAsManager() {
        System.out.println("Enter your username and password");
        String muser = Get.getString();
        String mpass = Get.getString();
        boolean logout2 = true;
        if (!Library.manAuth(muser, mpass)) {
            while (true) {
                System.out.println("Username or password incorrect");
                System.out.println("(Retry or Enter '0' to Exit)");
                muser = Get.getString();
                if (Objects.equals(muser, "0")) {
                    logout2 = false;
                    break;
                }
                mpass = Get.getString();
                if (Library.manAuth(muser, mpass)) {
                    break;
                }
            }
        }
        while (logout2) {
            System.out.println("1.Add a book or journal");
            System.out.println("2.Add a member");
            System.out.println("3.View all members");
            System.out.println("4.View all books/journals");
            System.out.println("5.Delete a book by id");
            System.out.println("6.Logout");
            int x = Get.getInt();
            switch (x) {
                case 1:
                    System.out.println("1.Add a book");
                    System.out.println("2.Add a journal");
                    int k = Get.getInt();

                    if (k == 1)
                        Execution.addBook();
                    else if (k == 2)
                        Execution.addJournal();
                    else
                        System.out.println("!not a valid number!");
                    break;
                case 2:
                    Execution.addMember();
                    break;
                case 3:
                    Library.printLine();
                    Execution.viewAllMembers();
                    Library.printLine();
                    break;
                case 4:
                    Library.printLine();
                    Library.showAll();
                    Library.printLine();
                    break;
                case 5:
                    Library.printLine();
                    Library.showAll();
                    Library.printLine();
                    System.out.println("Enter book ID:");
                    int z = Get.getInt();
                    Library.removeBook(z);
                    Library.printLine();
                    break;
                case 6:
                    logout2 = false;
            }
        }
    }

    public static void addBook() {
        System.out.println("Enter new book's name");
        String newBname = Get.getString();
        System.out.println("Enter new book's writer's name");
        String newBwriter = Get.getString();
        System.out.println("Enter new book's category");
        String newBcategory = Get.getString();
        System.out.println("Enter new book's ID");
        int newBid = Get.getInt();
        System.out.println("Enter new book's stock count");
        int newBstock = Get.getInt();
        Library.addBook(newBname, newBwriter, newBcategory, newBid, newBstock);
    }

    public static void addJournal() {
        System.out.println("Enter new journal's name");
        String newJname = Get.getString();
        System.out.println("Enter new journal's number");
        int newJnum = Get.getInt();
        System.out.println("Enter new journal's publishing date");
        String newJdate = Get.getString();
        System.out.println("Enter new journal's category");
        String newJcat = Get.getString();
        System.out.println("Enter new journal's ID");
        int newJid = Get.getInt();
        System.out.println("Enter new journal's stock count");
        int newJstock = Get.getInt();
        Library.addJournal(newJname, newJnum, newJdate, newJcat, newJid, newJstock);
    }

    public static void addMember() {
        System.out.println("Enter new member's username");
        String newMuser = Get.getString();
        System.out.println("Enter new member's password");
        String newMpass = Get.getString();
        Library.addMember(newMuser, newMpass);
    }

    public static void viewAllMembers() {
        for (int i = 0; i < Library.members.length; i++) {
            Member mem = (Member) Library.members.getA_index(i);
            if (mem == null) {
                break;
            }
            System.out.println("name: " + mem.getUsername() + " - ID: " + mem.getId());
        }
    }
}
