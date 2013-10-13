package com.gdconsulting.adamedia.apicall;

public interface OnApiCallCompleted{
    void onApiCallCompleted(String result);
    void onApiCallError(String result);
}
