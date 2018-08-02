package com.zero.barrageserver.common.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全控制中心
 */
@EnableWebSecurity
//@ConditionalOnClass({
//        UserService.class
//})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private UserService userService;
//
//    @Autowired
//    private void setUserService(UserService userService){
//        this.userService = userService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/user/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/user", true)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().portMapper().http(9001).mapsTo(9001)
                .and().csrf().disable();

        http.rememberMe().alwaysRemember(true);

//        http.addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        http.addFilterAt(githubAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    /**
//     * 自定义 QQ登录 过滤器
//     */
//    private QQAuthenticationFilter qqAuthenticationFilter(){
//        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/login/qq");
//        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//        successHandler.setAlwaysUseDefaultTargetUrl(true);
//        successHandler.setDefaultTargetUrl("/user");
//        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager(userService));
//        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
//        return authenticationFilter;
//    }
//
//    /**
//     * 自定义 Github登录 过滤器
//     */
//    private GithubAuthenticationFilter githubAuthenticationFilter(){
//        GithubAuthenticationFilter authenticationFilter = new GithubAuthenticationFilter("/login/github");
//        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//        successHandler.setAlwaysUseDefaultTargetUrl(true);
//        successHandler.setDefaultTargetUrl("/user");
//        authenticationFilter.setAuthenticationManager(new GithubAuthenticationManager(userService));
//        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
//        return authenticationFilter;
//    }

}
