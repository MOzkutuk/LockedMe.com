import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MehmetBaranOzkutuk on 04/06/2021
 */

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        openingMessage();
        lockedMe();

    }

    public static void openingMessage() {
        //It displays the oppening message

        System.out.println("******************************\n"
                + "Welcome to the LockedMe                                   --Designed by Mehmet Baran Ozkutuk\n"
                + "To see the menu please enter a number...\n"
                + "****************************************\n"
                + "Your number :\n");
    }

    public static void lockedMe() {
        while (true) {                      //a while loop for user to give us the right input
            try {
                int numara = scn.nextInt();
                scn.nextLine();
                break;
            } catch (InputMismatchException e) {                    //an expection occures when user enteres a wrong type of  input
                System.out.println("You have entered an udidentified data\nPlease enter a number :");
                scn.next();
            }
        }
        //outer loop
        outerLoop:
        //We created a loop, so user won't have to restart the application over
        while (true) {
            menuyuGoster2();                    //The main menu will be displayed
            int secim1 = 0;

            try {
                secim1 = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("You have entered an udidentified data\nPlease use numbers..");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                scn.next();
            }


            switch (secim1) {                       //switch-case method to get efficient answer
                case 1:
                    System.out.println("This operation will list all the file folders and files you have specified in that particular directory..\nWould you like to continue (Y / N) :");
                    listLoop:
                    // list loop for option 1 in the main menu
                    while (true) {
                        String cevap2 = scn.nextLine();             //we are giving user options to go back or go further to the operation
                        if (cevap2.toLowerCase().equals("y")) {
                            System.out.println("Enter the root directory if you want to see the current files :");
                            String rootDirectory = scn.nextLine();
                            File file = new File(rootDirectory);
                            if (file.exists() && file.isDirectory()) {
                                File[] listOfFiles = file.listFiles();              //creating an array which will be filled with files
                                String[] directories = file.list(new FilenameFilter() {
                                    @Override
                                    public boolean accept(File dir, String name) {
                                        return new File(dir, name).isDirectory();
                                    }
                                });


                                if (listOfFiles.length != 0 || directories.length != 0) {
                                    System.out.println("Files/Folders :\n" + toString(directories));
                                    Arrays.sort(listOfFiles);   //Sorting the files
                                    for (int i = 0; i < listOfFiles.length; i++) {      //basic for loop to list the name of the files
                                        if (listOfFiles[i].isFile()) {
                                            System.out.println(listOfFiles[i].getName());
                                        }
                                    }
                                } else {
                                    System.out.println("The folder is empty..");
                                }


                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("I am taking you back to the main menu..");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                continue outerLoop;
                            } else if (!file.exists() || !file.isDirectory()) {       //checking if the input is correct form
                                System.out.println("The directory does not exist or it is not a directory..\nWould you like to try again ? (Y / N) :");
                                //String cevap = scn.nextLine();
                                if (cevap2.equalsIgnoreCase("y")) {
                                    continue listLoop;
                                } else if (cevap2.toLowerCase().equals("n")) {
                                    System.out.println("I am taking you to the main menu..");
                                    continue outerLoop;
                                } else {
                                    System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                                }
                            }
                        } else if (cevap2.toLowerCase().equals("n")) {
                            System.out.println("I am taking you back to the main menu..");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue outerLoop;
                        } else {                                                                                             // giving user an option to try again
                            System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                        }

                    }

                case 2:
                    String mesaj = "You may only create a txt. file, would you like to continue to this operation ? (Y / N)";
                    System.out.println(mesaj);
                    innerLoop:
                    //inner loop
                    while (true) {
                        String cevap = scn.nextLine();

                        if (cevap.toLowerCase().equals("y")) {          //getting the directory and the file name from user
                            System.out.println("Enter the name of the file you are about to create :");
                            String fileName = scn.nextLine();
                            System.out.println("Enter the file path :");
                            String filePath = scn.nextLine();
                            File file2 = new File(filePath + fileName);
                            File file1 = new File(filePath + "\\" + fileName + ".txt"); //creating the file
                            file1.getParentFile().mkdirs();

                            if (file1.exists()) {                               //a basic if-else block to check whether the file is already created or not
                                System.out.println("This file already exists...\nWould you like to try again ? (Y / N) :");
                                continue innerLoop;
                            }
                            if(!file2.isDirectory()){
                                System.out.println("The directory does not exist..\nWould you like to try again ? (Y / N) :");
                                continue innerLoop;
                            }

                            try {                           //using file writer class to create the new file(can use try-with resources)
                                FileWriter writer = new FileWriter(file1, true);
                                System.out.println("The file '" + fileName + "' has been succesfully created...");
                                writer.close();                 //must close the file in order to proceed the further operations
                            } catch (IOException e) {
                                System.out.println("Unexpected error occured..\nPlease check your file path");
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("We are taking you back to the main menu please wait...");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue outerLoop;
                        } else if (cevap.toLowerCase().equals("n")) {
                            System.out.println("I am sending you back to the main menu..");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue outerLoop;
                        } else {
                            System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                        }
                    }
                case 3:
                    String message = "This operation will delete the file and it is impossible to undo.\nWould you like to continue ? (Y / N) :";
                    System.out.println(message);
                    //deleting a file
                    deleteWhile:
                    //created a while loop for delete option only
                    while (true) {
                        String cevap2 = scn.nextLine();

                        if (cevap2.toLowerCase().equals("y")) {
                            System.out.println("Enter the name of the file :");
                            String fileInputName = scn.nextLine();
                            System.out.println("Enter the file path :");
                            String fileInputPath = scn.nextLine();
                            File tempFile = new File(fileInputPath + "\\" + fileInputName + ".txt");
                            boolean exists = tempFile.exists();
                            //creating a temporary file ro check whether the input file does exist or not
                            if (exists) {
                                tempFile.delete();      //if it does,delete
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("The file has been successfully deleted..");
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("I am taking you back to the main menu..");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                continue outerLoop;
                            } else if (exists == false) {           //if not giving user options to choose
                                System.out.println("The file does not exist or the file path is incorrect..\nWould you like to try again ? (Y / N) :");
                                if (cevap2.toLowerCase().equals("y")) {
                                    continue deleteWhile;
                                } else if (cevap2.toLowerCase().equals("n")) {
                                    continue outerLoop;
                                } else {
                                    System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                                }
                            }
                        } else if (cevap2.toLowerCase().equals("n")) {
                            System.out.println("I am taking you back to the main menu..");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue outerLoop;
                        } else {
                            System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                        }
                    }

                case 4:
                    System.out.println("This operation will search for the file you are about yo give the details of..\nWould you like to continue ? (Y / N) :");
                    //searching for a file
                    searchLoop:
                    // created a search loop for searching option only
                    while (true) {
                        String cevap3 = scn.nextLine();
                        if(cevap3.equalsIgnoreCase("y")){
                            System.out.println("Enter the name of the file you want to look for :");
                            String nameFile = scn.nextLine();
                            nameFile.trim();
                            System.out.println("Enter the directory :");
                            String directoryFile = scn.next();              //creating a temporary file to check whether the file does exist or not
                            File tempFile = new File(directoryFile + "\\" + nameFile );
                            boolean exists = tempFile.exists();
                            Desktop desktop = Desktop.getDesktop();
                            if (exists) {                               //if so further operations
                                System.out.println("The file has been found..\nWould you like to open it ? (Y / N) :");
                                String cevap = scn.next();
                                if (cevap.toLowerCase().equals("y")) {
                                    System.out.println("We are openning the file for you..");
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        desktop.open(tempFile);
                                        System.out.println("I am taking you back to the main menu..\nYou can finish your work peacefully..");
                                        Thread.sleep(2000);
                                    } catch (IOException e) {
                                        System.out.println("Unexpected error occures,please exit the program and try again..");
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    continue outerLoop;
                                } else if (cevap.toLowerCase().equals("n")) {
                                    System.out.println("I am taking you back to the main menu..");
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    continue outerLoop;
                                } else {
                                    System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                                }
                            } else if (!exists) {           //if doesn't exist further operations
                                System.out.println("We coulnt find the file for you,would you like to try again (Y / N) :");
                                scn.nextLine();
                                continue searchLoop;
                        }
                        }else if(cevap3.equalsIgnoreCase("n")){
                            System.out.println("I am taking you back to the main menu..");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue outerLoop;
                        }else{
                            System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                        }
                    }
                case 5:
                    System.out.println("This operation will add a new directory in a given directory\nWould you like to proceed ? (Y / N) :");
                    folderLoop:
                    while (true){
                        String cevap = scn.next();
                        if(cevap.equalsIgnoreCase("y")){
                            System.out.println("Enter the path to create a directory: ");
                            Scanner sc = new Scanner(System.in);
                            String path = sc.next();
                            System.out.println("Enter the name of the desired a directory: ");
                            String folerPath = scn.next();
                            //Creating a File object
                            File file = new File(path);
                            //Creating the directory
                            if(file.isDirectory()){
                                File file1 = new File(path+folerPath);
                                boolean bool = file1.mkdir();
                                if(bool){
                                    System.out.println(file1.getName() + "has been created successfully..");
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("I am taking you back to the main menu..");
                                    continue outerLoop;
                                }else if(file.exists()){
                                    System.out.println(file1.getName() + " already exists..\nWould you like to try again ? (Y / N) :");
                                    continue folderLoop;
                            }else{
                                    System.out.println("An unexpected error occured..\nWould you like to try again ? (Y / N) :");
                                    continue folderLoop;
                            }
                            }else if (!file.isDirectory()){
                                System.out.println("The path you have entered does not exist..\nWould you like to try again ? (Y / N) :");
                                continue folderLoop;
                            }
                        }else if(cevap.equalsIgnoreCase("n")){
                            System.out.println("I am taking you back to the main menu..");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue outerLoop;
                        }else{
                            System.out.println("You have entered an udidentified data\nPlease enter your answer (Y / N) :");
                        }
                    }


                case 6:
                    //exiting the program
                    System.out.print("Thank you for using LocekdMe\nWe hope to see you again...\n");
                    for (int i = 3; i > 0; i--) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Exiting in " + i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Goodbye");
                    System.exit(1);
            }
        }
    }


    public static void menuyuGoster2() {                //The main menu
        System.out.println("LOCKEDME");
        System.out.println("__________________\n"
                + "1. Show Files\n"
                + "2. Add File\n"
                + "3. Delete File\n"
                + "4. Search File\n"
                + "5. Add Directory\n"
                + "6. Exit\n"
                + "__________________\n"
                + "Please select an option by entering number :");
    }

    public static <T> String toString(T arr[]) {
        return Arrays.stream(arr).map(s -> "0" + s).collect(Collectors.joining("\n"));
    }
}

