package com.kone.authservice.security;

import com.kone.authservice.dao.UserDao;
import com.kone.authservice.entity.UserT;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//@Component
public class TokenEnhancerConfiguration implements TokenEnhancer {

    @Resource
    private UserDao userDao;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
//        additionalInfo.put("client_name", oAuth2Authentication.getName());
        UserT user = userDao.findByUsername(oAuth2Authentication.getName());
        additionalInfo.put("user", user);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
