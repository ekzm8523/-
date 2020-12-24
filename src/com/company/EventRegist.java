package com.company;

import java.sql.*;
import java.util.Scanner;

public class EventRegist { // jdbc practice
    Scanner scanner = new Scanner(System.in);

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/javada?&serverTimezone=Asia/Seoul&useSSL=false";
    Connection conn;

    PreparedStatement pstmt;
    ResultSet rs;

    public EventRegist(){
        System.out.println("## 이벤트 등록을 위해 이름과 이메일을 입력하세요");
        System.out.print("id: ");
        String id = scanner.next();
        System.out.print("username: ");
        String username = scanner.next();

        connectDB();
        registUser(id,username);
        printList();
        closeDB();
    }
    public void connectDB(){
        try{
            // 1단계 : JDBC 드라이버 로드
            Class.forName(jdbcDriver);
            // 2단계 : 데이터베이스 연결
            conn = DriverManager.getConnection(jdbcUrl,"root","wlfkf132");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeDB(){
        try {
            // 6단계 : 연결 해제
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void registUser(String id, String username){
        String sql = "insert into member values(?,?)";
        try{
            // 3단계 : Statement 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,username);

            // 4단계 : SQL 문 전송
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printList(){
        System.out.println("# 등록자 명단");
        String sql = "select * from member";
        try {
            pstmt = conn.prepareStatement(sql);

            // 5단계 : 결과받기
            rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("username")+ ", " + rs.getString(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
