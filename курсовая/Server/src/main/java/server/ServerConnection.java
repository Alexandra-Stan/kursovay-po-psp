package server;

import constants.Constants;
import dataBase.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerConnection {
    public static LinkedList<ClientHandler> usersConnected = new LinkedList<>();
    private  int idUser=0;

    private ServerSocket server;

    public void startServer(){
        try {
            server = new ServerSocket(Constants.PORT);
            System.out.println("Сервер запущен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connectNewClientInToServer(){
        Database dataBaseHandler = new Database();

        try {
            while (true) {
                Socket socket = server.accept();

                usersConnected.add(new ClientHandler(socket,dataBaseHandler,idUser++));
                System.out.println("Клиент  подключен");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeAll(){
        try {

            server.close();
            System.out.println("Сервер остановлен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
