package com.cultureIsland.service;

import com.cultureIsland.pojo.Page;
import com.cultureIsland.pojo.WikiPedia;

public interface WikiPediaService {
    /**
     * 通过百科页码获取分页信息(包括搜索功能)
     *
     * @param pageNum
     * @return
     */
    Page getWikePediaPageInfo(int pageNum, String str);

    /**
     * 发布百科
     * @param wikiPedia
     */
    void insertWikePedia(WikiPedia wikiPedia);

    /**
     * 通过百科wid删除百科
     * @param wid
     */
    void deleteWikePediaByWid(int wid);

    /**
     * 修改百科标题，内容
     * @param wikiPedia
     */
    void updateWikePedia(WikiPedia wikiPedia);
}
