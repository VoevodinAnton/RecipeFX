package com.netcracker.recipeproject.library;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 5990545414373884974L;
    private CommandEnum flag;
    private Object obj;

    public Message(CommandEnum flag, Object obj) {
        this.flag = flag;
        this.obj = obj;
    }

    public CommandEnum getFlag() {
        return flag;
    }

    public void setFlag(CommandEnum flag) {
        this.flag = flag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

