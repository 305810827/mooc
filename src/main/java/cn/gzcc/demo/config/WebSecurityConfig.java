package cn.gzcc.demo.config;

import cn.gzcc.demo.filter.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注册 401 处理器
     */
    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    /**
     * 注册 403 处理器
     */
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/courseList/**").authenticated()       // 需携带有效 token
                .antMatchers("/course/**").hasAuthority("STUDENT")   // 需拥有 admin 这个权限
                .antMatchers("/user/**").hasRole("ADMIN")     // 需拥有 ADMIN 这个身份
                .anyRequest().permitAll()       // 允许所有请求通过
//                .antMatchers("/uploads/**", "/video/**", "/target/**", "/image/**", "/editor.md-master/**", "/doc-template/**", "/bootstrap/**", "/mooc/**", "/mooc1/**", "/css/**", "/js/**", "/img/**", "/vendors/**", "/user/save", "/user/check", "/user/**", "/course/**", "/courseList/**", "/post/**").permitAll()
//                .antMatchers("/course/**").hasRole("TEACHER")
//                .antMatchers("/courseList/**").hasRole("STUDENT")
//                .antMatchers("/user/**").hasRole("ADMIN")
//                .antMatchers("/course/**").hasRole("ADMINISTRATOR")
//                .anyRequest().authenticated()// 允许所有请求通过
                .and()
                // 配置被拦截时的处理
                .exceptionHandling()
                .authenticationEntryPoint(this.unauthorizedHandler)   // 添加 token 无效或者没有携带 token 时的处理
                .accessDeniedHandler(this.accessDeniedHandler)      //添加无权限时的处理
                .and()
                .csrf()
                .disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement()                        // 定制我们自己的 session 策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session
    }


}
