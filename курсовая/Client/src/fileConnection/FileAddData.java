package fileConnection;

import model.Discount;

import java.io.FileWriter;
import java.io.IOException;

public class FileAddData {
   public static FileAddData fileAddData = new FileAddData();
    private FileAddData(){}

    public void addInFile(Discount discount) throws IOException {


        FileWriter writer = new FileWriter(discount.getSurname()+".txt", false);
            writer.write(discount.toString());
            writer.append('\n');
            writer.flush();

    }
}
