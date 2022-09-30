import com.cultureIsland.mapper.UserMapper;
import com.cultureIsland.pojo.User;
import com.cultureIsland.service.UserService;
import com.cultureIsland.service.impl.UserServiceImpl;
import com.cultureIsland.utils.EncryptByMd5;
import com.cultureIsland.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

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
        boolean exitUser = userService.isExistUser("12334");
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
}
