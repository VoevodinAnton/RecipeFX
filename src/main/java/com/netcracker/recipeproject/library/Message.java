package com.netcracker.recipeproject.library;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 5990545414373884974L;
    private int flag;
    private Object obj;

    public Message(int flag, Object obj) {
        this.flag = flag;
        this.obj = obj;
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

