package com.cultureIsland.service;

public interface ViewService {
    /**
     * 通过vid获取view的url
     *
     * @param vid
     * @return
     */
    String getUrlByVid(int vid);

    /**
     * 添加view的url
     *
     * @param url
     */
    void insertUrl(String url);
}
