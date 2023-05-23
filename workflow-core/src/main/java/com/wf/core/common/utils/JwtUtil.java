package com.wf.core.common.utils;

import com.wf.core.common.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joffrey
 */
public class JwtUtil {

    // 设置HS512密钥
    public static final String JWT_SECRET = "AToughGuyMustMaintainDiscipline";

    //签发者
    public static final String Issuer = "Joffrey";

    // 签名算法
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;


    public static String extractJwt(HttpServletRequest request) {
        String jwt = request.getHeader("Authentication");
        if (StringUtil.isNotEmpty(jwt) && jwt.startsWith(Constants.TOKEN_PREFIX)) {
            jwt = jwt.replace(Constants.TOKEN_PREFIX, "");
        }
        return jwt;
    }

    /**
     * 创建一个token
     */
    public static String createJWT(Map<String, Object> claims, Long validityPeriod) {
        //获取当前时间并转换为date对象
        long nowMillis = System.currentTimeMillis();
        //签发时间
        Date now = new Date(nowMillis);
        //结束时间 = 当前时间 + 设定的有效期时间
        long expireMillis = nowMillis + validityPeriod;
        //转换为date数据类型
        Date expireTime = new Date(expireMillis);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("TYPE", "JWT")    //一下两行就是Header
                .setHeaderParam("ALGORITHM", signatureAlgorithm.getValue())
                .setClaims(claims)
                .setIssuer(Issuer)   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(generalKey(), signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(expireTime);   //设置过期时间
        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    public static String createJWT(Map<String, Object> claims) {
        // 默认一个小时过期
        return createJWT(claims, 60 * 60 * 1000L);
    }

    /**
     * 加密明文密钥
     *
     * @return secret
     */
    public static SecretKey generalKey() {
        //调用base64中的getDecoder方法获取解码器，调用解码器中的decode方法将明文密钥进行编码
        byte[] decodeKey = Base64.getDecoder().decode(JWT_SECRET + JWT_SECRET + JWT_SECRET + JWT_SECRET);
        //返回加密后的密钥
        return Keys.hmacShaKeyFor(decodeKey);
    }

    /**
     * 解析jwt
     *
     * @param jwt 令牌
     * @return claims
     */
    //此处当解析不出的时候会抛出异常
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(generalKey())    //设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(jwt)// (jwt)    //解析传入的jwt字符串
                .getBody();     // 拿到claims对象返回
    }

    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "admin");
        String jwt = createJWT(claims);
        System.out.println(jwt);
        System.out.println(parseJWT(jwt));
    }
}
