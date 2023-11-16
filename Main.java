import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main{
        public int ans ;
        public String continuee = "yes";

        public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Directory directory = new Directory("E:");
        Main main = new Main();
        while(main.continuee.equalsIgnoreCase("yes")){
        main.options();
            switch (main.ans){
                case 1 :
                    System.out.print("Provide name for the file : ");
                    String name = sc.nextLine();
                    System.out.print("Provide path at which yo want to create file : ");
                    String newFilePath = sc.nextLine() ;
                    Directory directory1 = new Directory(newFilePath);
                    directory1.createNewFile(name);
                    System.out.println("Do you want to write in this file? (yes/no)");
                    String reply = sc.nextLine();
                    if (reply.equalsIgnoreCase("yes")){
                        System.out.print("Provide path to this file : ");
                        String filePath = sc.nextLine() ;
                        ComputerFile file = new ComputerFile(filePath);
                        file.writeInAFile();
                    }else {
                        System.out.println("OK , you can proceed!!");
                    }
                    break;
                case 2 :
                    System.out.print("Provide Path at which you want to create the directory : ");
                    String path = sc.nextLine() ;
                    directory.createDirectory(path);
                    break;
                case 3 :
                    directory.iterateItemsOfDirectory();
                    System.out.print("\n\nProvide path to the file you want to read : ");
                    String filePath = sc.nextLine() ;
                    File file = new File(filePath);
                    if (file.exists()){
                        ComputerFile readFileThread = new ComputerFile(filePath);
                        readFileThread.start();
                        try{
                            readFileThread.join();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("This file does not exist!!!");
                    }
                    break;
                case 4 :
                    directory.iterateItemsOfDirectory();
                    System.out.print("\n\nprovide Path to the file you want to edit : ");
                    String fileToEditPath = sc.nextLine();
                    File file2 = new File(fileToEditPath) ;
                    if (file2.exists()) {
                        ComputerFile fileToEdit = new ComputerFile(fileToEditPath);
                        fileToEdit.writeInAFile();
                    }else {
                        System.out.println("This file does not exist!!");
                    }
                    break;
                case 5 :
                    System.out.println("Do you want to delete a \"file\" or \"directory\" ? ");
                    String reply2 = sc.nextLine();
                    if (reply2.equalsIgnoreCase("file")) {
                        System.out.print("Provide Path to file you want to delete : ");
                        String fileToDltPath = sc.nextLine();
                        FileManager fileToDlt = new FileManager(fileToDltPath);
                        fileToDlt.dltFile();
                    } else if (reply2.equalsIgnoreCase("directory")) {
                        System.out.println("This will delete each item of the directory, do you want to continue ? (yes/no)");
                        String reply3 = sc.nextLine();
                        if (reply3.equalsIgnoreCase("yes")) {
                            DirectoryManager directoryToDlt = new DirectoryManager(directory);
                            System.out.print("Provide path to the directory you want to delete : ");
                            String directoryToDltPath = sc.nextLine();
                            directoryToDlt.deleteDirectory(directoryToDltPath);
                        }else{
                            System.out.println("OK , You can proceed!!");
                        }
                    }else {
                        System.out.println("Incorrect input");
                    }
                    break;
                case 6 :
                    System.out.println("Do you want to rename a \"file\" or \"directory\" ? ");
                    String reply4 = sc.nextLine();
                    if (reply4.equalsIgnoreCase("file")){
                        System.out.print("Provide path to the file you want to rename : ");
                        String fileRenamePath = sc.nextLine() ;
                        FileManager fileRename = new FileManager(fileRenamePath);
                        fileRename.renameFile();
                    } else if (reply4.equalsIgnoreCase("directory")) {
                        DirectoryManager directoryToRename = new DirectoryManager(directory);
                        System.out.print("Provide path to the directory you want to rename : ");
                        String directoryRenamePath = sc.nextLine() ;
                        System.out.print("Now provide new name for the directory : ");
                        String directoryNewName = sc.nextLine();
                        directoryToRename.renameDirectory(directoryRenamePath , directoryNewName);
                    }else {
                        System.out.println("Incorrect input");
                    }
                    break;
                case 7 :
                    System.out.println("Do you want to copy a \"file\" or \"directory\" ? ");
                    String reply5 = sc.nextLine();
                    if (reply5.equalsIgnoreCase("file")){
                        System.out.print("Provide the path of the file that you want to copy : ");
                        String fileToCopyPath = sc.nextLine() ;
                        FileManager fileToCopy = new FileManager(fileToCopyPath);
                        System.out.print("Now provide the path where you want to copy the file : ");
                        String copiedFilePath = sc.nextLine();
                        fileToCopy.copyFile(fileToCopyPath ,copiedFilePath);
                    } else if (reply5.equalsIgnoreCase("directory")) {
                        System.out.print("Provide the path of the directory that you want to copy : ");
                        String directoryToCopyPath = sc.nextLine() ;
                        DirectoryManager directoryToCopy = new DirectoryManager(directory);
                        System.out.print("Now Provide the path where you want to copy the directory : ");
                        String copiedDirectoryPath = sc.nextLine() ;
                        directoryToCopy.copyDirectory(directoryToCopyPath , copiedDirectoryPath);
                    }else {
                        System.out.println("Incorrect input");
                    }
                    break;
                case 8 :
                    System.out.print("Provide the name of either a file/directory that you want to search within the current" +
                            " directory : ");
                    String searchItemName = sc.nextLine() ;
                    DirectoryManager searchItem = new DirectoryManager(directory);
                    searchItem.searchFile(searchItemName);
                    break;
                default :
                    System.out.println("Incorrect input!!!");
                    break;
            }

            System.out.println("\n\nDo you want to continue ? (yes/no)");
            main.continuee = sc.nextLine() ;
        }

        if (main.continuee.equalsIgnoreCase("no")){
            System.out.println("\n\n--------Thank you for using our filing system!!--------");
        }

    }
    public void options(){

        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t\t-Press 1 for creating a file");
        System.out.println("\n\t\t\t-Press 2 for creating a directory");
        System.out.println("\n\t\t\t-Press 3 to read any file of the current folder");
        System.out.println("\n\t\t\t-Press 4 for editing a file of he current folder");
        System.out.println("\n\t\t\t-Press 5 for deleting a file/directory");
        System.out.println("\n\t\t\t-Press 6 for renaming either a file/directory");
        System.out.println("\n\t\t\t-Press 7 for copying a file/directory to another Path");
        System.out.println("\n\t\t\t-Press 8 for searching a file or sub-directory within the current directory");
        ans = sc.nextInt() ;

    }

} // main() ends