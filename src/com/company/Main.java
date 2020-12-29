package com.company;

public class Main {

    public static void main(String[] args) {
//        AppMain program = new AppMain();
//        program.startUI();
//        Ch7Ex7Server server = new Ch7Ex7Server();
//        Ch7Ex7Client client = new Ch7Ex7Client();
//        MultiChattingUI app = new MultiChattingUI();
        MultiChattingController app = new MultiChattingController(new MultiChattingUI(), new MultiChattingData());
        app.appMain();
    }
}
