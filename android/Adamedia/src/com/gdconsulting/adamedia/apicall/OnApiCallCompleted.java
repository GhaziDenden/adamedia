package com.gdconsulting.adamedia.apicall;

public interface OnApiCallCompleted{
    void onApiCallCompleted(ApiResult result);
    void onApiCallError(ApiResult result);
}
