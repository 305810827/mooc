package cn.gzcc.demo.security;

import cn.gzcc.demo.model.entity.User;
import cn.gzcc.demo.model.vo.JwtUser;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getRole(),
                user.getLastPasswordResetDate()
        );
    }
}
