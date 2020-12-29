package com.company;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiChattingController extends Thread{

    private final MultiChattingUI view;
    private final MultiChattingData chatData;
    private Socket socket;
    private BufferedReader inMsg = null;
    private PrintWriter outMsg = null;
    private Gson gson = new Gson();
    private Message msg = new Message("","","","");
    private boolean status = true;
    String ip = "127.0.0.1";
    Thread thread;
    Logger logger;
    public MultiChattingController(MultiChattingUI view, MultiChattingData chatData) {
        this.view = view;
        this.chatData = chatData;
    }

    public void appMain(){
        chatData.addObj(view.C_msgOut); // update

        view.addButtonActionListener(e -> {
            Object obj = e.getSource();
            if (obj == view.B_exitButton) {
                System.exit(0);
            } else if(obj == view.T_loginButton){
                view.setId(view.T_idInput.getText());
                view.T_outLabel.setText(" 대화명 : " + view.getId());
                view.cardLayout.show(view.T_container,"logout");
                connectServer();
            } else if(obj == view.T_logoutButton){
                outMsg.println(gson.toJson(new Message(view.getId(),"","","logout")));
                view.C_msgOut.setText("");
                view.cardLayout.show(view.T_container, "login");
                outMsg.close();
                try{
                    inMsg.close();
                    socket.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                status = false;
            } else if(obj == view.B_msgInput){
                outMsg.println(gson.toJson(new Message(view.getId(),"",view.B_msgInput.getText(),"server")));
                view.B_msgInput.setText("");
            }

        });

    }
    public void connectServer(){
        logger = Logger.getLogger(this.getClass().getName());

        try {
            socket = new Socket(ip,8888);
            logger.log(Level.INFO,"[Client]Server 연결 성공!");

            inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outMsg = new PrintWriter(socket.getOutputStream(),true);

            msg = new Message(view.getId(),"","","login");
            outMsg.println(gson.toJson(msg));

            thread = new Thread(this);
            thread.start();

        } catch (Exception e){
            logger.log(Level.WARNING, "[MultiChatUI] connectServer() Exception 발생!!");
            e.printStackTrace();
        }
    }
    public void run(){
        String msgString;
        status = true;

        while(status){
            try{
                msgString = inMsg.readLine();
                msg = gson.fromJson(msgString,Message.class);

                chatData.refreshData(msg.getId() + ">" + msg.getMsg() + "\n");
                view.C_msgOut.setCaretPosition(view.C_msgOut.getDocument().getLength());

            }catch(IOException e){
                logger.log(Level.WARNING,"MultiChatUI]메시지 스트림 종료!!");
            }
        }
        logger.info("[MultiChatUI]" + thread.getName() + " 메시지 수신 스레드 종료됨!!");


    }


}
