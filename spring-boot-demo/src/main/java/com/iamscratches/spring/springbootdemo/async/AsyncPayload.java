package com.iamscratches.spring.springbootdemo.async;

public class AsyncPayload {
    private long id;
    private String model;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "AsyncPayload{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
