import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class FileManager extends ComputerFile {
    public FileManager(String filePath){
        super(filePath);
    }

    // deletes a file
    public void dltFile() {
        File fileToDlt = new File(super.getFilePath()) ;
        if (fileToDlt.exists() && fileToDlt.isFile()){
            if (fileToDlt.delete()){
                System.out.println("file deleted successfully!!");
            }else {
                System.out.println("Error deleting the file!!");
            }
        }else {
            System.out.println("File doesn't exist or is not a regular file!!");
        }
    } // dltFile() ends

    // renames the file
    public void renameFile(){
        Scanner sc = new Scanner(System.in);
        File file = new File(super.getFilePath());
        Path oldPath = Paths.get(super.getFilePath()).getParent() ; // will fetch the path till the parent directory of file to be renamed
        System.out.print("Provide new name : ");
        String newName = sc.nextLine() ;
        Path sourcePath = Paths.get(super.getFilePath()); // whole old path from drive to file name
        Path newPath = oldPath.resolve(newName) ; // whole new path from drive to new file name
        try{
            if (file.exists() && file.isFile()) {
                Files.move(sourcePath, newPath);
                System.out.println("File renamed successfully!!");
            }else {
                System.out.println("File does not exist or is not a regular file");
            }
        }catch (Exception e){
        System.out.println("Error while renaming the File Name!!");
        }
    }

    // copies the file
    public void copyFile(String sourcePath , String targetPath){
        Path fileSourcePath = Paths.get(sourcePath) ;
        Path fileTargetPath = Paths.get(targetPath) ;
        try{
            Files.copy(fileSourcePath , fileTargetPath , StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully!!");
        }catch (IOException e){
            System.out.println("Error while copying the file!!");
        }
    }

} // class ends

