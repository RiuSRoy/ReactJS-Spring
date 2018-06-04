package tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableGlobalMethodSecurity
@EnableWebSecurity
@Configuration
//@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Sam").password("{noop}sam").roles("ADMIN").and()
                .withUser("youtube").password("{noop}youtube").roles("USER");
    }

    /*@Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService )
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return true;
                    }
                });
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        /*http.authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .permitAll();*/
        http.authorizeRequests()
                .antMatchers("/doc").fullyAuthenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll();
                /*.loginPage("/secured.html").permitAll()
                .defaultSuccessUrl("/index.html").permitAll()
                .failureUrl("/secured.html?error=true").permitAll()
                .and()
                .logout().logoutSuccessUrl("/secured.html").permitAll();*/
    }

    @Bean
    public CustomFilter customFilter() {
        return  new CustomFilter();
    }
}
