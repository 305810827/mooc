package cn.gzcc.demo.config;


import cn.gzcc.demo.filter.JwtAuthorizationTokenFilter;
import cn.gzcc.demo.security.JwtAuthenticationEntryPoint;
import cn.gzcc.demo.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration      // 声明为配置类
@EnableWebSecurity  // 启用 Spring Security web 安全的功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //认证失败处理类
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    //实现了DetailsService接口，用来做登陆验证
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    //token过滤器来验证token有效性
    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 设置UserDetailsService
                .userDetailsService(this.jwtUserDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                .antMatchers("/course/*").authenticated()       // 需携带有效 token
                //.antMatchers("/courseList/*").authenticated()
                .antMatchers("/user").authenticated()
                .anyRequest().permitAll();    // 允许所有请求通过
                //除上面外的所有请求全部需要鉴权认证
                //.anyRequest().authenticated()

        // 添加JWT filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        // 禁用缓存
        httpSecurity
                .headers()
                .cacheControl();

        // 设置登陆页面
        httpSecurity
                .formLogin()
                .loginPage("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
                .ignoring()
                .antMatchers(
                        "/**/*js",
                        "/**/*.css",
                        "/**/*.btl"
                );
    }
}
