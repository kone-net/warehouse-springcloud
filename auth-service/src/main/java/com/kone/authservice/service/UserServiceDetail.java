package com.kone.authservice.service;

import com.kone.authservice.dao.UserDao;
import com.kone.authservice.entity.Role;
import com.kone.authservice.entity.UserT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceDetail implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        UserT user = new UserT();
//        user.setId(1L);
//        user.setUsername("kone");
//        user.setPassword(BPwdEncoderUtil.BCryptPassword("kone"));
//        return user;
        UserT userT = userDao.findByUsername(s);
        Role role = new Role();
        role.setId(2L);
        role.setName("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        userT.setAuthorities(roles);
        System.out.println(userT.getId() + " " + userT.getUsername() + "  " + userT.getPassword());
        return userT;
    }


}
