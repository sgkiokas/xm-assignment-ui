package com.xm.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseUrls {
    XM("https://www.xm.com/");

    private final String baseUrl;
}
