package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.ViewMapper;
import com.cultureIsland.service.ViewService;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class ViewServiceImpl implements ViewService {
    @Override
    public String getUrlByVid(int vid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ViewMapper mapper = sqlSession.getMapper(ViewMapper.class);
        String url = mapper.getUrlByVid(vid);
        return url;
    }

    @Override
    public void insertUrl(String url) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ViewMapper mapper = sqlSession.getMapper(ViewMapper.class);
        mapper.insertUrl(url);
    }
}
