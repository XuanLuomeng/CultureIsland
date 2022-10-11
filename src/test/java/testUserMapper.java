import com.cultureIsland.mapper.UserMapper;
import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.EncryptByMd5;
import com.cultureIsland.utils.RandomName;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class testUserMapper {
    @Test
    public void testSelectUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserByUserId("1069619982");
        System.out.println(user);
    }

    @Test
    public void testUserIsExit(){
        UserServiceImpl userService = new UserServiceImpl();
        boolean exitUser = userService.isExistUser("12345678");
        System.out.println(exitUser);
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        EncryptByMd5 encrypt = new EncryptByMd5("7758258");
        User user = new User("1234567", encrypt.getSimpleHash(), encrypt.getSalt(), "罗星",null,null,null,null);
        mapper.insertUser(user);
    }

    @Test
    public void testServiceInsert(){
        User user = new User("12345678", "1234567", null, "田星",null,null,null,null);
        UserService userService = new UserServiceImpl();
        boolean exitUser = userService.isExistUser(user.getUserId());
        if(exitUser){
            System.out.println("用户已存在");
        }else {
            userService.insertUser(user);
        }
    }

    @Test
    public void testCheckUserIdAndPassword(){
        UserService userService = new UserServiceImpl();
        boolean b = userService.checkPassword("12345678", "1234567");
        System.out.println(b);
    }

    @Test
    public void testRandomName() throws IOException {
        RandomName randomName = new RandomName("","");
        String name = randomName.getName();
        System.out.println(String.valueOf(name));
    }

    @Test
    public void testGetUrl() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("web/html/register.html"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
