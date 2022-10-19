package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.CommentMapper;
import com.cultureIsland.pojo.Article;
import com.cultureIsland.pojo.Comment;
import com.cultureIsland.pojo.Page;
import com.cultureIsland.service.CommentService;
import com.cultureIsland.utils.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public Page getCommentPageInfo(int pageNum, int aid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        com.github.pagehelper.Page<Object> pageUtil = PageHelper.startPage(pageNum, 5);
        List<Comment> comments = mapper.getCommentByAid(aid);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments, 8);
        Page<Comment> page = new Page<>();
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
        page.setList(comments);
        return page;
    }

    @Override
    public Comment insertCommentByUidAndAid(Comment comment) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        mapper.insertCommentByUidAndAid(comment);
        return comment;
    }

    @Override
    public void deleteCommentByCid(int cid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        mapper.deleteCommentByCid(cid);
    }

    @Override
    public List<Integer> getUserCommentedArticleByUid(int uid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        List<Integer> commentedAidByUid = mapper.getCommentedAidByUid(uid);
        return commentedAidByUid;
    }
}
