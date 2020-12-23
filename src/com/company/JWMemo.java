package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class JWMemo extends JFrame {
    JTextArea area;
    String fileName = "";
    JWMemo(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("NULL");

        area = new JTextArea(10,10);
        area.setBounds(0,0,500,500);
        this.add(area);
        //--------------------------------------------------
        JMenuBar menuBar = new JMenuBar();
        ArrayList<JMenu> menuList = new ArrayList<>(10);
        menuList.add(new JMenu("파일"));
        menuList.add(new JMenu("편집"));

        ArrayList<JMenuItem> itemList = new ArrayList<>(10);
        itemList.add(new JMenuItem("새 문서"));
        itemList.add(new JMenuItem("열기"));
        itemList.add(new JMenuItem("저장"));
        itemList.add(new JMenuItem("닫기"));

        setJMenuBar(menuBar);
        for(int i=0;i<menuList.size();i++){
            menuBar.add(menuList.get(i));
        }
        for(int i=0;i<itemList.size();i++){
            menuList.get(0).add(itemList.get(i));
            if (i == itemList.size()-2)
                menuList.get(0).addSeparator();
        }
        // -------------------- 메뉴바 ---------------------------

        JToolBar toolBar = new JToolBar();
        JButton itemNew = new JButton("새 문서");
        JButton itemOpen = new JButton("열기");
        JButton itemSave = new JButton("저장");
        JButton itemDifSave = new JButton("다른 이름으로 저장");
        JButton itemExit = new JButton("종료");


        this.add(toolBar, BorderLayout.NORTH);
        toolBar.add(itemNew);
        toolBar.add(itemSave);
        toolBar.add(itemDifSave);
        toolBar.add(itemOpen);
        toolBar.add(itemExit);




        //---------------------- 툴바 -----------------------------
        itemList.get(itemList.size()-1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        MyListener listener = new MyListener(this);
        itemNew.addActionListener(listener.newListener);
        itemSave.addActionListener(listener.saveListener);
        itemDifSave.addActionListener(listener.difSaveListener);
        itemOpen.addActionListener(listener.openListener);
        itemExit.addActionListener(listener.exitListener);
        itemList.get(0).addActionListener(listener.newListener);
        itemList.get(1).addActionListener(listener.openListener);
        itemList.get(2).addActionListener(listener.saveListener);
        itemList.get(3).addActionListener(listener.exitListener);

        //------------------------리스너 -----------------------------
        setSize(500,500);
        setVisible(true);
    }



}
