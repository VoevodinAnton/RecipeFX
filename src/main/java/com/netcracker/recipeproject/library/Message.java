package com.netcracker.recipeproject.library;

public class Message {
    private int flag;
    private Object obj;

    public Message(int flag, Object obj){
        this.flag = flag;
        this. obj = obj;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

