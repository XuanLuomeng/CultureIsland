package com.cultureIsland.pojo;

import lombok.Data;

@Data
public class Article {
    /**
     * aid:文章id
     * title:标题
     * content:内容
     * date:发布日期
     * userName:作者名
     * uid:作者id
     */
    private int aid;
    private String title;
    private String content;
    private String date;
    private String userName;
    private int uid;
}
