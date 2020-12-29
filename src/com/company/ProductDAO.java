package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProductDAO {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/javadb?&serverTimezone=Asia/Seoul&useSSL=false";
    Connection conn;

    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    // 콤보박스용 벡터
    private Vector<String> items = null;
    String sql;

    public ProductDAO(){
        items = new Vector<String>();
        items.add("전체");
    }
    public Vector<String> getItems(){
        return items;
    }

    public ArrayList<Product> getAll() {
        connectDB();
        sql = "select * from product";

        // 데이터 번들
        ArrayList<Product> dataList = new ArrayList<>();

        items = new Vector<String>(); // 콤보박스 초기화
        items.add("전체");

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setPrCode(rs.getInt("prcode"));
                p.setPrName(rs.getString("prname"));
                p.setPrice(rs.getInt("price"));
                p.setManufacture(rs.getString("manufacture"));
                dataList.add(p);
                items.add(String.valueOf(rs.getInt("prcode")));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        closeDB();

        if(dataList.isEmpty()){
            return null;
        }
        return dataList;

    }

    public Product getProduct(int prCode){

        connectDB();
        sql = "select * from product where prcode = ?";
        Product p = null;

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,prCode);
            rs = pstmt.executeQuery();
            rs.next();
            p = new Product();
            p.setPrCode(rs.getInt("prcode"));
            p.setPrName(rs.getString("prname"));
            p.setPrice(rs.getInt("price"));
            p.setManufacture(rs.getString("manufacture"));

        } catch(Exception e){
            e.printStackTrace();
        }

        closeDB();

        return p;
    }
    public boolean newProduct(Product product){

        connectDB();
        sql = "insert into product(prname, price, manufacture) values (?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product.getPrName());
            pstmt.setInt(2,product.getPrice());
            pstmt.setString(3,product.getManufacture());
            result = pstmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        if(result > 0)
            return true;
        return false;

    }
    public boolean updateProduct(Product product){
        connectDB();
        sql = "update product set prname = ?, price = ?, manufacture = ? where prcode = ?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product.getPrName());
            pstmt.setInt(2,product.getPrice());
            pstmt.setString(3,product.getManufacture());
            pstmt.setInt(4,product.getPrCode());
            result = pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

        closeDB();

        if(result > 0)
            return true;

        return false;

    }

    public boolean deleteProduct(int prCode){
        connectDB();
        sql = "delete from product where prcode = ?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,prCode);
            result = pstmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        if(result > 0)
            return true;
        return false;

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

}
