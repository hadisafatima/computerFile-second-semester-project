import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class DirectoryManager {
    private Directory directoryPath;

    public DirectoryManager(Directory directoryPath){
        this.directoryPath = directoryPath ;
    }

    // searches the file within a directory
    public void searchFile(String itemName) {
        ArrayList<File> items = this.directoryPath.listItems();
        boolean fileCheckFlag = false ;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                System.out.println("File exists at " + items.get(i).getAbsolutePath());
                fileCheckFlag = true ;
                break;
            }
        }
        if (!fileCheckFlag){
            System.out.println("The file named \"" + itemName + "\" does not exist in this directory!!!");
        }
    } // searchFile() ends

    //deletes the whole directory at a given path
    public void deleteDirectory(String directoryPath){
        File directory = new File(directoryPath);
//        Path dltDirectoryPath = Paths.get(directoryPath) ;
        try {
            if (directory.exists() && directory.isDirectory()) {
                if (dltitems(directory)) {
                    System.out.println("Directory \"" + directory.getName() + "\" is deleted successfully!!");
                } else {
                    System.out.println("Error, while deleting the file!!");
                }
            } else {
                System.out.println("Directory doesn't exist or is not a regular directory!!");
            }
        }catch (Exception e){
            e.getMessage() ;
        }
    } // deleteDirectory() ends
    private boolean dltitems(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (!dltitems(file)) {
                        return false;
                    }
                } else {
                    if (!file.delete()) {
                        return false;
                    }
                }
            }
        }
        return directory.delete(); // Delete the empty directory
    }

    // this method will copy the directory
    public void copyDirectory(String oldPath, String newPath) {
        Path oldFilePath = Paths.get(oldPath);
        Path newFilePath = Paths.get(newPath);
        try {
            Files.copy(oldFilePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Directory is copied successfully from " + oldPath + " to " + newPath);
        } catch (IOException e) {
            System.out.println("Error while copying the directory!!");
        }
    }// copyDirectory() ends

    // this method will rename the directory
    public void renameDirectory(String directoryPath, String directoryNewName) {
        Path oldPath = Paths.get(directoryPath).getParent();
        Path newPath = oldPath.resolve(directoryNewName);
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            if (directory.renameTo(newPath.toFile())) {
                System.out.println("Directory renamed successfully!!!");
            } else {
                System.out.println("Failed to rename the directory.");
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }// renameDirectory() ends


}