package com.company;
// session1 - Did the whole project but coded it way cleaner and done a better design than before
// session1 - Deployed Dynamic Array and changed some code for it to work
// session1 - Fixed some bugs of the previous version of the project
// session2 - Deployed Read and Write data, it has some bugs in borrowed books
// session3 - Fixed the bugs, everything works fine
// session4 - Added (Delete a book by ID), it checks if the book is borrowed by anyone first, if not, it'll delete it!
// session5 - Moved the programs running code to a class named Executions for a better design
// session6 - Added Get class for better input and outputs
// session6 - Added an exit to user pass authentication
// session7 - Added journals, writing them and reading them
// session8 - Fixed a lot of bugs, Project is complete

public class Main {
    public static void main(String[] args){
        Library.readData();

        Library.setManager("admin", "admin");
        while (true){
            System.out.println("1.Login as a member");
            System.out.println("2.Login as manager");
            System.out.println("3.Exit");
            int p = Get.getInt();

            if (p == 1) {
                Execution.loginAsMember();
            } else if (p == 2) {
                Execution.loginAsManager();
            } else if (p == 3){
                Library.writeData();
                break;
            } else {
                System.out.println("!please enter a valid Number!\n");
            }
        }
    }
}


