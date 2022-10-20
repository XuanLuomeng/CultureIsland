package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.LikeArticleMapper;
import com.cultureIsland.pojo.LikeArticle;
import com.cultureIsland.service.LikeArticleService;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
        mapper.updateLikeArticleByUpdateLikeArray(uid, likeArray);
    }

    @Override
    public void updateLikeArrays(String aid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        LikeArticleMapper mapper = sqlSession.getMapper(LikeArticleMapper.class);
        List<LikeArticle> list = mapper.getUidAndListByLike(aid);
        for (int i = 0; i < list.size(); i++) {
            String[] strings = list.get(i).getLAidArray().split(aid + ",");
            String newStr = "";
            /**
             * 分割字符串时，分割的是内部与边缘时情况不同，以下操作用于防止数组越界
             * 1、当字符串只有一个(*,)时，长度为0
             * 2、当字符串有多个(*,)时，split边缘内容后长度为1
             * 3、当字符串有多个(*,)时，split中间内容后长度为2
             */
            if (strings.length == 2) {
                newStr += strings[0] + strings[1];
            } else if (strings.length == 1) {
                newStr += strings[0];
            }
            mapper.updateLikeArticleByUpdateLikeArray(list.get(i).getLUid(), newStr);
        }
    }
}
