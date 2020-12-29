package com.company;

import com.sun.jdi.connect.Connector;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionListener;

public class MultiChattingUI extends JFrame{

    protected Container T_container;
    private JPanel T_loginPanel; // 로그인 패널
    private JLabel T_inLabel; // 대화명 라벨
    protected JTextField T_idInput; // 대화명 입력 텍스트필드
    protected JButton T_loginButton; // 로그인 버튼

    private JPanel T_logoutPanel; // 로그아웃 패널
    protected JLabel T_outLabel; // 대화명 출력 라벨
    protected JButton T_logoutButton; // 로그아웃 버튼

    protected Container C_container;
    protected JPanel C_noticePanel;
    protected JLabel C_noticeLabel;
    protected JTextField C_notice;
    protected JTextArea C_msgOut;

    private JPanel B_msgPanel; // 메시지 입력 패널 구성
    protected JTextField B_msgInput; // 메시지 입력 텍스트필드
    protected JButton B_exitButton; // 종료 버튼

    protected CardLayout cardLayout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public MultiChattingUI() {
        //메인 프레임 구성
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800,500);
        setTitle("::멀티채팅::");
// **********************************************
// ****************** 상위패널 ********************

        T_loginPanel = new JPanel();
        T_loginPanel.setLayout(new BorderLayout());

        T_inLabel = new JLabel("대화명");
        T_idInput = new JTextField(15);
        T_loginButton = new JButton("로그인");

        T_loginPanel.add(T_inLabel, BorderLayout.WEST);
        T_loginPanel.add(T_idInput, BorderLayout.CENTER);
        T_loginPanel.add(T_loginButton, BorderLayout.EAST);

        T_logoutPanel = new JPanel();
        T_logoutPanel.setLayout(new BorderLayout());

        T_outLabel = new JLabel();
        T_logoutButton = new JButton("로그아웃");

        T_logoutPanel.add(T_outLabel, BorderLayout.CENTER);
        T_logoutPanel.add(T_logoutButton, BorderLayout.EAST);

        T_container = new JPanel();
        cardLayout = new CardLayout();
        T_container.setLayout(cardLayout);
        T_container.add(T_loginPanel, "login");
        T_container.add(T_logoutPanel, "logout");
        cardLayout.show(T_container,"login");
        add(T_container,BorderLayout.PAGE_START);

// **********************************************
// ****************** 센터패널 ********************


        C_noticePanel = new JPanel();
        C_noticePanel.setLayout(new BorderLayout());
        C_noticeLabel = new JLabel("**공지** : ");

        C_noticePanel.add(C_noticeLabel,BorderLayout.PAGE_START);

        C_msgOut = new JTextArea("",10,30);
        C_msgOut.setEditable(false);

        JScrollPane jsp = new JScrollPane(C_msgOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        C_noticePanel.add(jsp,BorderLayout.CENTER);
        add(C_noticePanel,BorderLayout.CENTER);


// **********************************************
// ****************** 하위패널 ********************

        B_msgPanel = new JPanel();
        B_msgInput = new JTextField(15);
        B_exitButton = new JButton("종료");
        B_msgPanel.add(B_msgInput,BorderLayout.CENTER);
        B_msgPanel.add(B_exitButton, BorderLayout.EAST);
        add(B_msgPanel,BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }
    public void addButtonActionListener(ActionListener listener){
        T_loginButton.addActionListener(listener);
        T_logoutButton.addActionListener(listener);
        B_exitButton.addActionListener(listener);
        B_msgInput.addActionListener(listener);
    }
}
