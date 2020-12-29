package com.company;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MultiChatServer {

    private ServerSocket serverSocket = null;
    private Socket socket = null;

    ArrayList<ChatThread> chatThreadList = new ArrayList<ChatThread>();
    Logger logger;

    public static void main(String[] args){
        new MultiChatServer().start();
    }

    public void start(){
        logger = Logger.getLogger(this.getClass().getName());
        try{
            serverSocket = new ServerSocket(8888);
            logger.info("chat.server.MultiChatServer start");
            while(true){
                socket = serverSocket.accept();
                ChatThread chat = new ChatThread();
                chatThreadList.add(chat);
                chat.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    class ChatThread extends Thread{

        private String msgString; // 수신 메시지 및 파싱 메시지 처리를 위한 변수 선언
        private Message msg; // 메시지 객체 생성
        private Gson gson = new Gson(); // JSON 파서 초기화
        private final BufferedReader inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        private final PrintWriter outMsg = new PrintWriter(socket.getOutputStream(),true);
        private boolean status = true;
        public String id = null;

        ChatThread() throws IOException {
        }
        public void run(){
            while(status) {
                try {
                    System.out.println("server thread running!!");
                    msgString = inMsg.readLine();
                    msg = gson.fromJson(msgString,Message.class);
                    if(msg.getType().equals("logout")){
                        chatThreadList.remove(this);
                        msgSendAll(gson.toJson(new Message(msg.getId(),"","님이 종료했습니다.","server"))); // id,pw,msg,type
                        status = false;
                    }
                    else if(msg.getType().equals("login")){
                        if(id == null) id = msg.getId();
                        msgSendAll(gson.toJson(new Message(msg.getId(),"",msg.getMsg(),"server")));
                    } else if(msg.getType().equals("notice")){

                        /*

                         */

                    } else{
                        msgSendAll(msgString);
                    }
                } catch (IOException e){
                    logger.info("[Chat Thread] run() IOException 발생 !!");
                    e.printStackTrace();
                    status = false;
                }
            }
        }
    }
    void msgSendAll(String msg){
        for(ChatThread ct : chatThreadList){
            ct.outMsg.println(msg);
        }
    }


}
