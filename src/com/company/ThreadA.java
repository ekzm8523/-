package com.company;

public class ThreadA extends Thread{

    private WorkObject workObject;

    public ThreadA(WorkObject workObject) {
        this.workObject = workObject;
    }

    public void run() {
        for(int i=0;i<10;i++){
            workObject.methodA();
        }
    }

}
