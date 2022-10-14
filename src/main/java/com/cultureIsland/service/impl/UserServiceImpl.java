package com.cultureIsland.service.impl;

import com.cultureIsland.mapper.UserMapper;
import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.utils.EncryptByMd5;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isExistUser(String userId) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserByUserId(userId);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insertUser(User user) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        EncryptByMd5 encrypt = new EncryptByMd5(user.getPassword());
        //Password encryption
        user.setPassword(encrypt.getSimpleHash());
        user.setSalt(encrypt.getSalt());
        mapper.insertUser(user);
    }

    @Override
    public boolean checkPassword(String userId, String password) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserByUserId(userId);
        if (user != null) {
            EncryptByMd5 encrypt = new EncryptByMd5(password, user.getSalt());
            if (encrypt.getSimpleHash().equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String getUserNameByUserId(String userId) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String userName = mapper.selectUserNameByUserId(userId);
        return userName;
    }

    @Override
    public int getUidByUserId(String userId) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int uid = mapper.selectUidByUserId(userId);
        return uid;
    }

    @Override
    public User getUserAllInfoByUserId(String userId) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserAllInfoByUserId(userId);
        return user;
    }

    @Override
    public boolean checkPasswordAndUpdate(String userId, String oldPassword, String newPassword) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserByUserId(userId);
        EncryptByMd5 encrypt = new EncryptByMd5(oldPassword, user.getSalt());
        if (encrypt.getSimpleHash().equals(user.getPassword())) {
            /**
             * 重新加密,然后将新密码和新盐修改保存到数据库
             */
            EncryptByMd5 encrypt1 = new EncryptByMd5(newPassword);
            mapper.updatePasswordAndSaltByUserId(userId, encrypt1.getSimpleHash(), encrypt1.getSalt());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateUserInfoByUser(User user) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUserInfo(user);
    }
}
