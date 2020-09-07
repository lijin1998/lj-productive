package com.test;

import cn.lj.auto.pojo.UserInfo;
import cn.lj.auto.utils.JwtUtils;
import cn.lj.auto.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "C:\\leyou\\rsa\\rsa.pub";

    private static final String priKeyPath = "C:\\leyou\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU5OTIxOTY0NX0.RvXQMeETabw9hO2JqKk9kb2I7k5ctOiAr2CQ0ra-73tOkEinIbxfYHWPdjoWXGUhTS_YkxAs5R9S_De6TWY0s-eN9KpHxA3Df0-SO-7uufEoAZrNtMkAw8SwMuwCeqH6FanDPMCMSavXizVmQ51CrSPILm5j7HBJBj_F9Sc7nmw";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}