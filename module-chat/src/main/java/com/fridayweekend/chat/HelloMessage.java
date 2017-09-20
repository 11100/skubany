package com.fridayweekend.chat;

public class HelloMessage {

    private String content;
    private String name;
    private String color;


    public void setContent(String content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(String color) {
		this.color = color;
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
