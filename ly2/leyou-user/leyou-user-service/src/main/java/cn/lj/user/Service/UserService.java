package cn.lj.user.Service;

import cn.lj.common.utils.NumberUtils;
import cn.lj.user.mapper.UserMapper;
import cn.lj.user.pojo.User;
import cn.lj.user.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    static final String KEY_PREFIX = "user:code:phone:";

    static final Logger logger= LoggerFactory.getLogger(UserService.class);

    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if (type==1){
            record.setUsername(data);
        }else if (type==2){
            record.setPhone(data);
        }else {
            return null;
        }

        return this.userMapper.selectCount(record) == 0;
    }

    public Boolean sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);

        try {
            // 发送消息到MQ，短信
            Map<String, String> msg = new HashMap<>();
            msg.put("phone",phone);
            msg.put("code",code);
            this.amqpTemplate.convertAndSend("leyou.sms.exchange","sms.verify.code",msg);

            // 将验证码code存入redis
            this.redisTemplate.opsForValue().set(KEY_PREFIX+phone,code,5, TimeUnit.MINUTES);
            return true;
        } catch (AmqpException e) {
            logger.error("发送短信失败。phone：{}， code：{}", phone, code);
            return false;
        }
    }

    public void register(User user, String code) {
        //查询redis中的验证码
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        // 校验短信验证码
/*        if (!StringUtils.equals(code,cacheCode)){
            return ;
        }*/
        // 生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        // 对密码加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));

        // 强制设置不能指定的参数为null
        user.setId(null);
        user.setCreated(new Date());

        // 添加到数据库
        this.userMapper.insertSelective(user);

        // 注册成功，删除redis中的记录
//        this.redisTemplate.delete(KEY_PREFIX+user.getPhone());
    }

    public User queryUser(String username, String password) {
        // 查询
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        // 校验用户名
        if (user == null) {
            return null;
        }
        // 获取盐,校验密码,输出用户信息
        password = CodecUtils.md5Hex(password, user.getSalt());
        if (StringUtils.equals(password,user.getPassword())) {
            return user;
        }
        return null;
    }
}