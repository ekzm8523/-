package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class phoneCallView extends JFrame {

    JTextArea area;
    static String fname = "/Users/macbook/eclipse-workspace/OOD/text.txt";
    JTextField txtName;
    JTextField txtAge;
    JTextField txtPhone;
    static class Address {
        String name;
        String age;
        String phone;
        Address(String name, String age, String phoneNum){
            this.name = name;
            this.age = age;
            this.phone = phoneNum;
        }
    }

    phoneCallView(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("연락처 프로그램");

        setLayout(new BorderLayout());


        add(new MyCenterPanel(), BorderLayout.CENTER);
        setSize(450,500);
        setVisible(true);
    }
    class MyCenterPanel extends JPanel{
        MyCenterPanel(){

            JButton btnPrint = new JButton("출력");
            JButton btnReg = new JButton("등록");
            JButton btnDelete = new JButton("삭제");
            JButton btnExit = new JButton("끝내기");
            txtName = new JTextField(10);
            txtAge = new JTextField(10);
            txtPhone = new JTextField(10);
            txtName.setBounds(100,100,10,10);
            btnPrint.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        area.setText("");
                        view_juso();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            btnReg.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        area.setText("");
                        add_juso();
                        initTextFields();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        area.setText("");
                        delete_juso();
                        initTextFields();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            add(btnPrint);
            add(btnReg);
            add(btnDelete);
            add(btnExit);
            add(txtName);
            add(txtAge);
            add(txtPhone);

            area = new JTextArea("hello",25,30);

            add(area);
        }
    }
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
            area.setText(sw.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void view_juso() throws IOException {
        String str = "";

        File f = new File(fname);
        if (!f.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
            bw.close();
        }

        BufferedReader br = new BufferedReader(new FileReader(fname));
        int i;

        for (i = 1;; i++) {
            if (!br.ready())
                break;
            else {
                str = br.readLine();
                area.append(str + "\n");
            }
        }

        if (i == 1) {
            area.append("\n ** 연락처 파일에 전화번호가 하나도 없어요. **\n");
        }

        br.close();
    }
    public void add_juso() throws IOException{
        Address adr = new Address("", "", "");

        String wstr = "";

        BufferedWriter bw = new BufferedWriter(new FileWriter(fname, true));

        adr.name = txtName.getText();
        adr.age = txtAge.getText();
        adr.phone = txtPhone.getText();

        wstr = adr.name + " " + adr.age + " " + adr.phone;

        bw.write(wstr);
        bw.newLine();
        area.append(wstr);

        bw.close();
    }
    public void delete_juso() throws IOException{
        String[] read_str = new String[50];
        String strName = txtName.getText();
        String strAge = txtAge.getText();
        String strPhone = txtPhone.getText();
        String str = "", cstr = strName + " " + strAge + " " + strPhone;
        int i, count = 0;

        BufferedReader br = new BufferedReader(new FileReader(fname));

        if (!br.ready()) {
            area.append("\n!! 연락처 파일이 없습니다. !!\n");
            return;
        }

        while((str = br.readLine()) != null) {
            if (!str.equals(cstr)) {
                read_str[count] = str;
                count++;
            } else {
                area.append("삭제되었습니다.\n");
            }
        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(fname));

        for (i = 0; i < count; i++) {
            bw.write(read_str[i]);
            bw.newLine();
        }

        bw.close();
    }
    public void initTextFields() {
        txtName.setText("");
        txtAge.setText("");
        txtPhone.setText("");
    }
}
