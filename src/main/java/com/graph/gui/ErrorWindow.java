package com.graph.gui;

public class ErrorWindow extends Thread{
    private String message ;
    public ErrorWindow(String message) {
        super(message);
        createWindow(message);
    }

    public void createWindow(String message){
        PopupMessage.newMessage(message,"RED");
    }
}