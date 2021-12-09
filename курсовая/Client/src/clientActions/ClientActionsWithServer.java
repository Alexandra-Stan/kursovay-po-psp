package clientActions;


import javafx.scene.control.Button;
import model.Discount;
import model.User;
import windowsAlert.AlertWindow;
import windowsAlert.NewWindowOpen;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ClientActionsWithServer {

    private BufferedReader acceptMessage;
    private BufferedWriter sendMessage;

    public ClientActionsWithServer(Socket clientSocket) {
        try {
            acceptMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMessage = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void send(String message) {
        try {
            sendMessage.write(message + "\n");
            sendMessage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chekUserInDatabase(Button button){
        try {
            String answer = acceptMessage.readLine();
            if(!answer.equals("false")){

                String[] string = answer.split(" ");
                User.currentUser = new User(string[0],string[1],string[2]);
                if(User.currentUser.getRoll().equals("admin")) {
                    NewWindowOpen.newWindowOpen.openWindow(button, "../views/adminWindow.fxml");

                }else{
                    NewWindowOpen.newWindowOpen.openWindow(button, "../views/userWindow.fxml");

                }
            }else{

               AlertWindow.alertWindow.alertWindow("Неверный логин или пароль");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LinkedList<Discount> allDiscounts() throws IOException {

        send("вывести все скидки");
        LinkedList<Discount> discounts = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addDiscountInList(acceptMessage.readLine(), discounts);
        }

        return discounts;
    }

    private void addDiscountInList(String string, LinkedList<Discount> discounts){
        String[] product;
        product = string.split(" ");
        discounts.add(new Discount(Integer.parseInt(product[0]),product[1],product[2],product[3],product[4]));

    }


    public void redactionDiscount(int id, String name, String surname, String dnumber, String shopName) {
        send("редактировать скидку");
        send(id+" "+name+" "+surname+" "+dnumber+" "+shopName);
    }



    public void deleteDiscount(int id) {
        send("удалить скидку");
        send(String.valueOf(id));
    }

    public void addNewDiscountInDataBase(String name, String surname, String dnumber, String shopName) {
        send("добавить скидку");
        send(name+" "+surname+" "+dnumber+" "+shopName);
    }
    public void addNewUserInDataBase(String name, String lastname, String login, String password) {
        send("добавить пользователя");
        send(login+" "+name+" "+lastname+" "+password);
    }
}
