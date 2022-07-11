package com.company;

class Member extends User {
    static int counter = 1;
    private final int id;
    private int borrowC = 0;
    //    private Book[] borrowedBooks = new Book[5];
    private Incription[] borrowedBooks = new Incription[5];

    public Member(String user, String pass) {
        super(user, pass);
        this.id = counter;
        Member.counter += 1;
    }

    public Member(String user, String pass, int id, int borrowC) {
        super(user, pass);
        this.id = id;
        this.borrowC = borrowC;
        Member.counter += 1;
    }

    public void borrowBook(Book borrowedBook) {
        if (borrowC < 5) {
            this.borrowedBooks[borrowC] = borrowedBook;
            this.borrowC += 1;
        } else {
            System.out.println("Too many borrowed books! \n");
        }
    }

    public void returnBook(int id) {
        if (id <= 0 || id > 5) {
            System.out.println("Enter a valid number");
        } else if (borrowedBooks[id - 1] != null) {
            Incription b = borrowedBooks[id - 1];
            b.setStockCount(b.getStock() + 1);
            for (int i = id - 1; i < borrowC - 1; i++) {
                borrowedBooks[i] = borrowedBooks[i + 1];
            }
            borrowedBooks[borrowC - 1] = null;
            this.borrowC -= 1;
        } else
            System.out.println("Enter a valid number");
    }

    public void borrowJournal(Journal borrowedBook) {
        if (borrowC < 5) {
            this.borrowedBooks[borrowC] = borrowedBook;
            this.borrowC += 1;
        } else {
            System.out.println("Too many borrowed books!");
        }
    }

    public Incription getBorrowedBooks(int id) {
        return borrowedBooks[id];
    }

    public int getId() {
        return id;
    }

    public int getBorrowC() {
        return borrowC;
    }
}
