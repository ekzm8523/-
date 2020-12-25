package com.company;

import com.mysql.cj.protocol.x.XProtocol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppMain extends JFrame{


    public JPanel p1,p2,p3;
    public JLabel lblPrCode, lblPrice, lblPrName, lblManufacture, lblMessage;
    public JTextField txtPrice, txtPrName, txtManufacture;
    public JButton btnInsert, btnSearch, btnDelete;

    public JComboBox comboBox;
    public JTextArea txtArea;
    public JScrollPane scroll;

    public ProductDAO dao;
    public ArrayList<Product> dataList;
    public Product product;

    public boolean editmode;

    //public ManagementListener mangL;

    public String msg;

    public AppMain(){
        dataList = new ArrayList<Product>();
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(90,300));
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension( 200, 300));
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(700,70));

        msg = new String("##메시저 : ");
        lblMessage = new JLabel(msg + "프로그램이 시작되었습니다!!!");
        lblMessage.setFont(new Font("Serif", Font.BOLD, 30));

        //mangL = new ManagementListener();

        btnInsert = new JButton("등록");
        //btnInsert.addActionListener(mangL);
        btnDelete = new JButton("삭제");
        //btnDelete.addActionListener(mangL);
        btnSearch = new JButton("조회");
        //btnSearch.addActionListener(mangL);

        txtPrice = new JTextField();
        txtManufacture = new JTextField();
        txtPrName = new JTextField();
        txtArea = new JTextArea();

        lblPrCode = new JLabel("관리 번호");
        lblPrCode.setFont(new Font("Serif", Font.BOLD, 14));
        lblPrName = new JLabel("상품명");
        lblPrName.setFont(new Font("Serif", Font.BOLD, 14));
        lblPrice = new JLabel("단 가");
        lblPrice.setFont(new Font("Serif", Font.BOLD, 14));
        lblManufacture = new JLabel("제조사");
        lblManufacture.setFont(new Font("Serif", Font.BOLD, 14));

        comboBox = new JComboBox<String>(); // *******
        scroll = new JScrollPane();

        dao = new ProductDAO();
        product = new Product();

    }

    public JTextArea
    //startUI() // 메서드에서 화면 구성
    //actionPerformed() // 메서드에서 이벤트 처리
    //refreshData() // 메서드에서 화면 데이터 로딩 및 재로딩 처리
    //clearField() // 메서드에서 입력 양식 초기화


}
