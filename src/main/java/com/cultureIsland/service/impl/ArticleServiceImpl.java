package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.ArticleMapper;
import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.service.ArticleService;
import com.cultureIsland.service.LikeArticleService;
import com.cultureIsland.utils.SqlSessionUtil;
import com.cultureIsland.utils.TimeDiffer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public Page getArticlePageInfo(int pageNum, String str, int uid) throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        com.github.pagehelper.Page<Object> pageUtil = PageHelper.startPage(pageNum, 5);
        List<Article> allArticle = mapper.getAllArticle(str);
        PageInfo<Article> pageInfo = new PageInfo<>(allArticle, 8);
        Page<Article> page = new Page<>();
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

        /**
         * 设置发布时间差
         */
        for (int i = 0; i < allArticle.size(); i++) {
            String date = allArticle.get(i).getDate();
            TimeDiffer timeDiffer = new TimeDiffer(date);
            allArticle.get(i).setTimeDiffer(timeDiffer.getTime());
        }

        /**
         * 若uid不为空则通过LikeArticleService获取对应文章是否点过赞并设置给Article的isLike，否则所有Article的isLike设置为False
         */
        if (uid != 0) {
            LikeArticleService likeArticleService = new LikeArticleServiceImpl();
            for (int len = 0; len < allArticle.size(); len++) {
                String aid = String.valueOf(allArticle.get(len).getAid());
                allArticle.get(len).setIsLike(likeArticleService.isLike(uid, aid));
            }
        } else {
            for (int len = 0; len < allArticle.size(); len++) {
                allArticle.get(len).setIsLike("0");
            }
        }

        page.setList(allArticle);
        return page;
    }

    @Override
    public Page getUserArticlePageInfo(int pageNum, String str, int uid) throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        com.github.pagehelper.Page<Object> pageUtil = PageHelper.startPage(pageNum, 5);
        List<Article> userArticle = mapper.getUserArticle(str, uid);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticle, 8);
        Page<Article> page = new Page<>();
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

        /**
         * 设置发布时间差
         */
        for (int i = 0; i < userArticle.size(); i++) {
            String date = userArticle.get(i).getDate();
            TimeDiffer timeDiffer = new TimeDiffer(date);
            userArticle.get(i).setTimeDiffer(timeDiffer.getTime());
        }

        /**
         * 通过LikeArticleService获取对应文章是否点过赞并设置给Article的isLike
         */
        LikeArticleService likeArticleService = new LikeArticleServiceImpl();
        for (int len = 0; len < userArticle.size(); len++) {
            String aid = String.valueOf(userArticle.get(len).getAid());
            userArticle.get(len).setIsLike(likeArticleService.isLike(uid, aid));
        }

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

    @Override
    public void addArticleViewCountByAid(int aid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        mapper.addViewCountByAid(aid);
    }

    @Override
    public Article getUserLikeOrCommentedArticleByAid(int aid) throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = mapper.getUserArticleByAid(aid);
        /**
         * 设置发布时间差
         */
        String date = article.getDate();
        TimeDiffer timeDiffer = new TimeDiffer(date);
        article.setTimeDiffer(timeDiffer.getTime());
        return article;
    }
}