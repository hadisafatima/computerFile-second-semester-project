import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date ;
import java.util.Scanner;

public class ComputerFile extends Thread{
    private String fileName ;
    private String filePath ;
    private int fileSize ;
    private long lastModified ;

    //constructorrrrr
    public ComputerFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && (file.isFile() || file.isDirectory())){
            this.filePath = filePath ;
            fileName = file.getName() ;
            fileSize = (int)file.length();
            lastModified = file.lastModified();
        }
    }

    //for achieved lastModified date in an understandable formattttttttt
    public String getLastModified() {
        Date date = new Date(lastModified);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    // to read the file.............
    @Override
    public void run(){

        File file = new File(filePath) ;
        if (file.isFile() && file.exists()) {
            String name = file.getName();
            System.out.println("\n\nReading \"" + name + "\" file...\n");
        }
        try (FileReader reader = new FileReader(filePath)) {
            int line;
                while ((line = reader.read()) != -1) {
                    System.out.print((char) line);
                    try{
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // to write in a fileeee
    public void writeInAFile(){
        File file = new File(filePath) ;

        if (file.exists() && file.isFile()){
        System.out.println("\n\nYou can start writing in " + file.getName());
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath , true));
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine() ;
            writer.write(" " + text);
            writer.flush(); // force the data to be written immediately
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // for converting computerFile to File
    public File toFile(){
        return new File(fileName) ;
    }

    public String getFilePath() {
        return filePath;
    }

    // having all info related to a particular fileeeee
    @Override
    public String toString() {
        return  "fileName = " + fileName  + " || " + "filePath = " + filePath + " || " + "fileSize = " + fileSize + " Bytes || " +
                "lastModified = " + getLastModified() + "\n";
    }
} // ComputerFile ends