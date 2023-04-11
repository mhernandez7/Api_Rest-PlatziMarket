package com.platzi.market.web.security;


import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Se inyecta el servicio PlatziUserDetailsService para definir los parametros que nosotros le vamos a pasar

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platziUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean //Se define a el como gestor de autenticacion de la aplicacion
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

            web.ignoring().antMatchers("/v2/api-docs/**");
            web.ignoring().antMatchers("/swagger.json");
            web.ignoring().antMatchers("/swagger-ui.html");
            web.ignoring().antMatchers("/swagger-resources/**");
            web.ignoring().antMatchers("/webjars/**");
        }
    }

    /*desactiva la protección CSRF. Sin embargo, tener en cuenta que esto deja tu aplicación
     vulnerable a ataques CSRF, por lo que no es recomendable desactivar la protección CSRF

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
    */


