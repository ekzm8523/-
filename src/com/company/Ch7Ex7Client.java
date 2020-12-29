package com.company;

import java.io.Externalizable;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Principal;
import java.util.SortedMap;
import java.util.concurrent.ExecutionException;

public class Ch7Ex7Client {
    public static void main(String[] args){
        try {
            Socket s = new Socket("127.0.0.1",5000);
            System.out.println("## client start");
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            pw.println("hello? from client transfer");
            pw.close();
            s.close();
            System.out.println("## 클라이언트 종료..");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
