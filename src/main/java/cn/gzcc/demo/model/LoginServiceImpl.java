package cn.gzcc.demo.model;

import cn.gzcc.demo.repository.UserRepository;
import cn.gzcc.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 2:12
 */
@Service
public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;
    private final TokenUtils tokenUtils;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, TokenUtils tokenUtils) {
        this.userRepository = userRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public LoginDetail getLoginDetail(String username) {
        return userRepository.getUserFromDatabase(username);
    }

    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }
}
