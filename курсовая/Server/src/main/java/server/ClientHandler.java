package server;


import dataBase.Database;
import repository.HandlerRepository;

import java.io.*;
import java.net.Socket;


public class ClientHandler extends Thread{

    private UserHandlerRepositoryService usersHandlerUser;
    private HandlerRepository handlerRepository;
    private Socket socket;
    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;
    public ClientHandler(Socket socket, Database database,int count) {


        this.socket = socket;
        try {
            messageFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            usersHandlerUser = new UserHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
            handlerRepository = new DiscountHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            userHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void userHandler() throws IOException {
        while (true) {
            switch (messageFromServer.readLine()) {

                case "авторизация":
                    usersHandlerUser.authorization();
                    break;

                case "добавить пользователя":
                    usersHandlerUser.addUserDataBase();
                    break;

                case "вывести всех пользователей":
                    usersHandlerUser.showAll();
                    break;

                case "удалить пользователя":
                    usersHandlerUser.delete();
                    break;

                case "редактировать пользователя":
                    usersHandlerUser.redaction();
                    break;

                case "добавить скидку":
                    handlerRepository.addInDataBase();
                    break;

                case "вывести все скидки":
                    handlerRepository.showAll();
                    break;

                case "удалить скидку":
                    handlerRepository.delete();
                    break;

                case "редактировать скидку":
                    handlerRepository.redaction();
                    break;


            }
        }
     }


    public void sendMessage(String msg) {
        try {
            writeMessage.write(msg + "\n");
            writeMessage.flush();
        } catch (IOException ignored) {}
    }
}


