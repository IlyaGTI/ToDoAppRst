package com.example.todoapprst.web.payload;

import lombok.Getter;

@Getter
public class ErrorInfo {
    private final String url;
    private final String info;

    public ErrorInfo(String url, String info) {
        this.url = url;
        this.info = info;
    }
}
