package com.example.cruiseapp;

public interface OnEventListener<T> {
    public void onSuccess(T result);
    public void onFailure(T result);
}
