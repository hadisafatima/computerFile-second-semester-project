import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Directory{
    private String baseDirectoryPath ;
    private ArrayList<File> items = new ArrayList<>();
    // constructor

    public Directory(String baseDirectoryPath){
        this.baseDirectoryPath = baseDirectoryPath ;
    }

    // this method returns the list of all items into it, either files or sub-directories
    public ArrayList<File> listItems() {
        File directory = new File(baseDirectoryPath);

        items.clear(); // this will clear the ArrayList at each call to this method ...

        if (directory.exists() && directory.isDirectory()) {
            File[] subDirs = directory.listFiles(File::isDirectory);
            File[] files = directory.listFiles(File::isFile);

            if (subDirs != null) {
                for (File subDir : subDirs) {
                    items.add(subDir);
                }
            }

            if (files != null) {
                for (File file : files) {
                    items.add(file);
                }
            }
        }

        return items;
    }

    // this method iterates all the items of the directory
    public void iterateItemsOfDirectory() {
        ArrayList<File> itemsOfDirectory = new ArrayList(listItems()) ;
        for (int i = 0; i < itemsOfDirectory.size(); i++) {
        ComputerFile file = new ComputerFile(itemsOfDirectory.get(i).getAbsolutePath()) ;
            System.out.println((i+1) + ") " + file);
        }
    }

    // this method creates a sub-directory within the current directory
    public void createDirectory(String directoryPath) {
        System.out.print("Provide name for the Directory : ");
        Scanner scanner = new Scanner(System.in);
        String newDirectoryName = scanner.nextLine() ;
        Path parentDirectory = Paths.get(directoryPath ) ;
//        }
        String subDirectoriesPath = directoryPath  ;
        Path childDirectory = parentDirectory.resolve(newDirectoryName) ;
        File newDirectory = new File(String.valueOf(childDirectory)) ;
            if (newDirectory.mkdir()) {
                System.out.println("Directory created successfully!!!");
            } else if (newDirectory.exists() && newDirectory.isDirectory()) {
                System.out.println("Directory already exist!!!");
            }else {
                System.out.println("Error while creating the directory!!!");
            }
    }

    // this method creates new file within the current directory
    public void createNewFile(String newFileName) throws IOException {
        String newFilePath = baseDirectoryPath + File.separator + newFileName ;
        Path newPath = Path.of(newFilePath) ;
        ComputerFile newComputerFile = new ComputerFile(newFilePath) ;
        File newFile = newComputerFile.toFile();
        items.add(newFile) ;
        try {
            if (!newFile.exists()) {
                Files.createFile(newPath);
                 System.out.println("File \"" + newFile.getName() + "\" created Successfully!!");
            }else {
                System.out.println("File can't be created!!!");
            }
        }catch (FileAlreadyExistsException e ){
            System.out.println("Error while creating the file!!!" + e.getMessage());
        }
    }

} // directory ends