package com.company;

import com.mysql.cj.protocol.x.XProtocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppMain extends JFrame{


    Container contentPane = getContentPane();
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

    public ManagementListener mangL;

    public String msg;

    public AppMain(){
        dataList = new ArrayList<Product>();

        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(90,300));
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension( 200, 300));
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(700,70));

        msg = new String("<html><br>##메시저 : ");
        lblMessage = new JLabel(msg + "프로그램이 시작되었습니다!!!</html>");
        lblMessage.setFont(new Font("Serif", Font.BOLD, 20));

        mangL = new ManagementListener();

        btnInsert = new JButton("등록");
        btnInsert.addActionListener(mangL);
        btnDelete = new JButton("삭제");
        btnDelete.addActionListener(mangL);
        btnSearch = new JButton("조회");
        btnSearch.addActionListener(mangL);

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

        comboBox = new JComboBox<String>();
        scroll = new JScrollPane();

        dao = new ProductDAO();
        product = new Product();
    }




    //startUI() // 메서드에서 화면 구성
    public void startUI(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800,500);

        comboBox = new JComboBox(dao.getItems());

        txtArea = new JTextArea(10,40);
        txtArea.append("관리번호 \t 상품명 \t\t 단가\t 제조사\n");

        scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        p1.setLayout(new GridLayout(4,1));
        p1.add(lblPrCode);
        p1.add(lblPrName);
        p1.add(lblPrice);
        p1.add(lblManufacture);

        p2.setLayout(new GridLayout(4,1));
        p2.add(comboBox);
        p2.add(txtPrName);
        p2.add(txtPrice);
        p2.add(txtManufacture);

        p3.add(btnInsert);
        p3.add(btnSearch);
        p3.add(btnDelete);

        add(lblMessage, BorderLayout.PAGE_START);
        contentPane.add(p1,BorderLayout.LINE_START);
        contentPane.add(p2,BorderLayout.CENTER);
        contentPane.add(scroll,BorderLayout.LINE_END);
        contentPane.add(p3,BorderLayout.PAGE_END);

        pack();
        refreshData();

        setVisible(true);

    }


    //refreshData() // 메서드에서 화면 데이터 로딩 및 재로딩 처리
    public void refreshData(){
        txtArea.setText("");
        clearField();
        editmode = false;

        txtArea.setText("관리번호 \t 상품명 \t\t 단가\t 제조사\n");
        dataList = dao.getAll(); // dataList에 DB내용이 전부 담기게 된다.
        comboBox.setModel(new DefaultComboBoxModel(dao.getItems())); // 콤보박스 설정

        if(dataList != null){
            for(Product p : dataList){
                StringBuffer sb = new StringBuffer(); // StringBuffer를 사용하면 그냥 String에 += 를 하는것보다 효율적이다. 객체가 한번만 생기기 때문이다.
                sb.append(p.getPrCode() + "\t");
                sb.append(p.getPrName() + "\t\t");
                sb.append(p.getPrice() + "\t");
                sb.append(p.getManufacture() + "\n");
                txtArea.append(sb.toString());
            }
        }
    }

    //clearField() // 메서드에서 입력 양식 초기화
    public void clearField(){
        txtPrName.setText("");
        txtManufacture.setText("");
        txtPrice.setText("");
    }

    public class ManagementListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if(obj == btnInsert){
                product.setPrName(txtPrName.getText());
                product.setPrice(Integer.parseInt(txtPrice.getText()));
                product.setManufacture(txtManufacture.getText());
                if(editmode){ // 수정일떄
                    product.setPrCode(Integer.parseInt((String)comboBox.getSelectedItem()));
                    if(dao.updateProduct(product)){
                        lblMessage.setText(msg + "상품을 수정했습니다!!</html>");
                        clearField();
                        editmode = false;
                    }
                    else
                        lblMessage.setText(msg + "상품 수정이 실패했습니다!!</html>");
                }
                else{   // 등록일 때
                    if(dao.newProduct(product))
                        lblMessage.setText(msg + "상품을 등록했습니다!!</html>");
                    else
                        lblMessage.setText(msg + "상품 등록이 실패했습니다!!</html>");
                }
                refreshData();
            }
            else if(obj == btnSearch){
                String s = (String)comboBox.getSelectedItem();
                if(!s.equals("전체")){
                    product = dao.getProduct(Integer.parseInt(s));
                    if(product != null) {
                        lblMessage.setText(msg + "상품정보를 가져왔습니다!!</html>");
                        txtPrice.setText(String.valueOf(product.getPrice()));
                        txtPrName.setText(product.getPrName());
                        txtManufacture.setText(product.getManufacture());
                        editmode = true;
                    }
                    else{
                        lblMessage.setText(msg + "상품이 검색되지 않았습니다!!</html>");
                    }
                }
                else{
                    refreshData();
                }
            }
            else if(obj == btnDelete){
                String s = (String)comboBox.getSelectedItem();
                if(s.equals("전체"))
                    lblMessage.setText(msg + "전체 삭제는 되지 않습니다!!</html>");
                else{
                    if(dao.deleteProduct(Integer.parseInt(s)))
                        lblMessage.setText(msg + "상품이 삭제되었습니다.!!</html>");
                    else
                        lblMessage.setText(msg + "상품이 삭제되지 않았습니다.!!</html>");
                }
                refreshData();
            }
        }
    }

}
