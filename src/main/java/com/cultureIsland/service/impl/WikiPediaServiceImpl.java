package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.WikiPediaMapper;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.pojo.WikiPedia;
import com.cultureIsland.service.WikiPediaService;
import com.cultureIsland.utils.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WikiPediaServiceImpl implements WikiPediaService {
    @Override
    public Page getWikePediaPageInfo(int pageNum, String str) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        WikiPediaMapper mapper = sqlSession.getMapper(WikiPediaMapper.class);
        com.github.pagehelper.Page<Object> pageUtil = PageHelper.startPage(pageNum, 5);
        List<WikiPedia> allWikiPedia = mapper.getAllWikiPedia(str);
        PageInfo<WikiPedia> pageInfo = new PageInfo<>(allWikiPedia, 8);
        Page<WikiPedia> page = new Page<>();
        page.setTotalCount((int) pageInfo.getTotal());
        page.setTotalPage(pageInfo.getPages());
        page.setCurrentPage(pageNum);
        page.setPageSize(5);
        page.setSize(pageInfo.getSize());
        page.setFirstPage(pageInfo.isIsFirstPage());
        page.setLastPage(pageInfo.isIsLastPage());
        page.setHasNextPage(pageInfo.isHasNextPage());
        page.setHasPreviousPage(pageInfo.isHasPreviousPage());
        page.setNavigatePages(pageInfo.getNavigatePages());
        page.setNavigatePageNums(pageInfo.getNavigatepageNums());
        page.setList(allWikiPedia);
        return page;
    }

    @Override
    public void insertWikePedia(WikiPedia wikiPedia) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        WikiPediaMapper mapper = sqlSession.getMapper(WikiPediaMapper.class);
        mapper.insertWikiPedia(wikiPedia);
    }

    @Override
    public void deleteWikePediaByWid(int wid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        WikiPediaMapper mapper = sqlSession.getMapper(WikiPediaMapper.class);
        mapper.deleteWikiPediaByWid(wid);
    }

    @Override
    public void updateWikePedia(WikiPedia wikiPedia) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        WikiPediaMapper mapper = sqlSession.getMapper(WikiPediaMapper.class);
        mapper.updateWikiPedia(wikiPedia);
    }
}
