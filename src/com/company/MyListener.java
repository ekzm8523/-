package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyListener {
    JWMemo memo;
    MyListener(JWMemo memo){
        this.memo = memo;
    }
    ActionListener newListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            memo.setTitle("NULL");
            memo.area.setText("");
        }
    };
    ActionListener openListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(memo);
            //파일을 읽어서 ta에 출력
            File f = fc.getSelectedFile();
            memo.fileName = f.getPath();
            memo.setTitle(f.getName());
            //System.out.println("getPath : " + fileName);
            fileRead(memo.fileName); //실제로 파일을 읽어오는 메소드
        }
    };
    ActionListener saveListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (memo.getTitle().equals("NULL")) {
                JFileChooser fc = new JFileChooser();
                int a = fc.showSaveDialog(memo);
                if(a==JFileChooser.CANCEL_OPTION) {return;}
                File f = fc.getSelectedFile();
                memo.fileName = f.getPath();
                memo.setTitle(f.getName());
            } else {
                fileSave(memo.fileName);
            }
        }
    };
    ActionListener difSaveListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int a = fc.showSaveDialog(memo);

            if (a==JFileChooser.CANCEL_OPTION) {return;}
                File f = fc.getSelectedFile();
            memo.fileName = f.getPath();
            System.out.println("저장파일 : " + memo.fileName);
            //ta에 있는 내용 파일로 출력
            fileSave(memo.fileName);
            memo.setTitle(f.getName());
        }
    };

    ActionListener exitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
    //파일읽기
    private void fileRead(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            StringWriter sw = new StringWriter();
            while(true) {
                int ch = fr.read();
                if (ch==-1) break;
                sw.write(ch);
            }
            sw.close();
            memo.area.setText(sw.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //파일 저
    private void fileSave (String fileName) {
        try {
            PrintStream ps = new PrintStream(fileName);
            ps.println(memo.area.getText());
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
