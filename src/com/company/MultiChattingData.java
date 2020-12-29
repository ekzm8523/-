package com.company;

import javax.swing.*;

public class MultiChattingData {
    private JTextArea msgOut;
    public void addObj(JComponent comp){ msgOut = (JTextArea) comp;}
    public void refreshData(String msg){ msgOut.append(msg);}
}
