package com.fridayweekend.chat;

public class Greeting {
    
    private String content;
    private String name;
    private String color;

    public Greeting(HelloMessage message) {
        this.content = message.getContent();  
        this.color = message.getColor();
        this.name = message.getName();
  }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

}
