//package com.company;
//
//import javax.swing.*;
//import java.io.*;
//import java.util.Scanner;
//
//public class phoneCallProgram {
//
//    static String fname = "/Users/macbook/eclipse-workspace/OOD/juso.txt";
//
//    static class address {
//        String name;
//        String age;
//        String phoneNum;
//        address(String name, String age, String phoneNum){
//            this.name = name;
//            this.age = age;
//            this.phoneNum = phoneNum;
//        }
//    }
//
//    phoneCallProgram()  throws IOException{
//        Scanner sc = new Scanner(System.in);
//        String select = "";
//        System.out.println("\n ### 친구 연락처 관리 ### \n");
//
//        while(select != "4"){
//            print_menu();
//            select = sc.next(); // 계속 다음꺼 입력받
//
//            switch (select){
//                case "1":
//                    view_juso();
//                    break;
//                case "2":
//                    System.out.println("check");
//                    add_juso();
//                    break;
//                case "3":
//                    delete_juso();
//                    break;
//                case "4":
//                    System.out.println("종료합니다.");
//                    return;
//                default:
//                    System.out.println("\n 잘못 입력했어요. 다시 선택하세요.");
//            }
//        }
//    }
//    static void print_menu(){
//        System.out.println();
//        System.out.println("1. 연락처 출력");
//        System.out.println("2. 연락처 등록");
//        System.out.println("3. 연락처 삭제");
//        System.out.println("4. 끝내기");
//    }
//    static void view_juso() throws IOException {
//        String str = "";
//
//        // 처음에 fname 파일이 없으면 빈 파일 생성
//        File f = new File(fname);
//        if(!f.exists()){
//            BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
//            bw.close();
//        }
//
//        BufferedReader br = new BufferedReader(new FileReader(fname));
//        int i;
//        for(i=1;;i++){
//            if(!br.ready()) break;
//            else{
//                str = br.readLine();
//                //System.out.printf("%2d: %s\n",i,str);
//            }
//        }
//        if (i == 1)
//            System.out.println("\n ** 연락처 파일에 전화번호가 하나도 없어요 **");
//        br.close();
//    }
//    static void add_juso() throws IOException{
//        Scanner sc = new Scanner(System.in);
//        address adr = new address("","","");
//        String wstr = "";
//
//        // 파일을 추가 모드로 열기
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fname,true));
//        System.out.printf("이름을 입력 =>> ");
//        adr.name = sc.nextLine();
//        System.out.printf("나이를 입력 ==> ");
//        adr.age = sc.nextLine();
//        System.out.printf("전화번호를 입력 ==> ");
//        adr.phoneNum = sc.nextLine();
//
//        // 입력된 3개의 값을 하나의 문자열로 만듦
//        wstr = adr.name + "\t" + adr.age + "\t" + adr.phoneNum;
//
//        bw.write(wstr);
//        bw.newLine();
//
//        bw.close();
//    }
//    static void delete_juso() throws IOException{
//        Scanner sc = new Scanner(System.in);
//        // 연락처 파일의 내용 전체를 저장하기 위한 문자열 배열
//        String[] read_str = new String[50]; // 최대 연락처 개수를 50개로 한정
//        String str = "";
//        int del_line, i, count = 0;
//
//        BufferedReader br = new BufferedReader(new FileReader(fname));
//
//        // 연락처 파일이 없으면 돌아간다.
//        if(!br.ready()){
//            System.out.println("\n 연락처 파일이 없습니다. !!");
//            return;
//        }
//        System.out.printf("\n삭제할 행 번호는 ?");
//        del_line = sc.nextInt();
//
//        for(i=0;i<50;i++){
//            if((str = br.readLine())==null) break;
//            if(i+1 != del_line){
//                read_str[count] = str;
//                count++;
//            }
//            else
//                System.out.printf("%d 행이 삭제되었습니다. \n",del_line);
//        }
//        br.close();
//
//        //파일을 쓰기 모드로 열고 새로운 내용을 쓴다.
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
//
//        for(i=0;i<count;i++){
//            bw.write(read_str[i]);
//            bw.newLine();
//        }
//
//        bw.close();
//    }
//
//}
