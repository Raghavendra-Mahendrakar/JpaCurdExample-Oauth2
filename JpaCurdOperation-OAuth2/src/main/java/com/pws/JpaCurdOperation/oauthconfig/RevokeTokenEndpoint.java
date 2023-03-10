package com.pws.JpaCurdOperation.oauthconfig;



import com.pws.JpaCurdOperation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    @Resource(name = "tokenStore")
    TokenStore tokenStore;


    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public ResponseEntity<Object> revokeToken(HttpServletRequest request, @RequestParam Integer id) {

        String authorization = request.getHeader("AUTH-TOKEN");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(tokenId);
            if (tokenStore instanceof JdbcTokenStore) {
                ((JdbcTokenStore) tokenStore).removeRefreshToken(tokenId);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}