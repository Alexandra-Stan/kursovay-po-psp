package server;

import dataBase.Database;
import repository.HandlerRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class DiscountHandlerRepositoryService implements HandlerRepository {
    private int countUser;
    private Socket socket;

    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    private Database database;

    public DiscountHandlerRepositoryService(int countUser, Socket socket, Database database, BufferedReader messageFromServer, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromServer = messageFromServer;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void showAll() {
        LinkedList<String> listCinemas = database.showAllDiscounts();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listCinemas.size()));
        for(String user:listCinemas){
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void delete() {

        try {
            database.deleteDiscountInDataBase(Integer.parseInt(messageFromServer.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addInDataBase() {
        try {
            String[] line = messageFromServer.readLine().split(" ");
            database.addDiscountInDataBase(line[0],(line[1]),(line[2]),line[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void redaction() {
        try {
            String[] line = messageFromServer.readLine().split(" ");
            database.redactionDiscountInDataBase(Integer.parseInt(line[0]),line[1],(line[2]),(line[3]),line[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
