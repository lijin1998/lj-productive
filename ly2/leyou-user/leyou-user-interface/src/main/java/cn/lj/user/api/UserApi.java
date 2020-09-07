package cn.lj.user.api;

import cn.lj.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {

    @GetMapping("/query")
    public User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password);
}