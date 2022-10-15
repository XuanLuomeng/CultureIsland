package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.CheckPointMapper;
import com.cultureIsland.service.CheckPointService;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class CheckPointServiceImpl implements CheckPointService {
    @Override
    public void insertCheckPointInfoByUid(int uid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CheckPointMapper mapper = sqlSession.getMapper(CheckPointMapper.class);
        mapper.insertCpNumByUid(uid);
    }

    @Override
    public String getCheckPointInfoByUid(int uid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CheckPointMapper mapper = sqlSession.getMapper(CheckPointMapper.class);
        String cpNum = mapper.getCpNumByUid(uid);
        return cpNum;
    }

    @Override
    public void updateCheckPointInfoByUid(int uid, String cpNum) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CheckPointMapper mapper = sqlSession.getMapper(CheckPointMapper.class);
        mapper.updateCpNumByUid(uid, cpNum);
    }
}
