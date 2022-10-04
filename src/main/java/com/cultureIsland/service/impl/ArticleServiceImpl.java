package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.ArticleMapper;
import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.utils.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public Page getArticlePageInfo(int pageNum, String str) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        com.github.pagehelper.Page<Object> pageUtil = PageHelper.startPage(pageNum, 5);
        List<Article> allArticle = mapper.getAllArticle(str);
        PageInfo<Article> pageInfo = new PageInfo<>(allArticle, 8);
        Page page = new Page();
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
        page.setList(allArticle);
        return page;
    }

    @Override
    public Page getUserArticlePageInfo(int pageNum, String str, String uid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        com.github.pagehelper.Page<Object> pageUtil = PageHelper.startPage(pageNum, 5);
        List<Article> userArticle = mapper.getUserArticle(str, uid);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticle, 8);
        Page page = new Page();
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
        page.setList(userArticle);
        return page;
    }

    @Override
    public void insertArticle(Article article) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        mapper.insertArticle(article);
    }

    @Override
    public void deleteArticleByAid(int aid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        mapper.deleteArticleByAid(aid);
    }

    @Override
    public void updateArticle(Article article) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        mapper.updateArticle(article);
    }
}