package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.LikeArticleMapper;
import com.cultureIsland.service.LikeArticleService;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class LikeArticleServiceImpl implements LikeArticleService {
    @Override
    public String isLike(int uid, String aid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        LikeArticleMapper mapper = sqlSession.getMapper(LikeArticleMapper.class);
        String likeByUidAndAid = mapper.getLikeArrayOrIsLikeByUidAndAid(uid, aid);
        if (likeByUidAndAid == null) {
            return "0";
        } else {
            return "1";
        }
    }

    @Override
    public String getLikeArray(int uid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        LikeArticleMapper mapper = sqlSession.getMapper(LikeArticleMapper.class);
        String likeByUidAndAid = mapper.getLikeArrayOrIsLikeByUidAndAid(uid, null);
        return likeByUidAndAid;
    }

    @Override
    public void updateLikeArray(int uid, String likeArray) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        LikeArticleMapper mapper = sqlSession.getMapper(LikeArticleMapper.class);
        mapper.insertLikeArticleByUpdateLikeArray(uid,likeArray);
    }
}
