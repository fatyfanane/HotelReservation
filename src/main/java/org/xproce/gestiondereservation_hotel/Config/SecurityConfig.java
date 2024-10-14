package org.xproce.gestiondereservation_hotel.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/delete","/detailsreservation","/ajouterreOnce","/deletereservation","/listreservation","/ajouterreservationn","/ajouterrOnce","/editreservation","/detailreservation","/detailsreservation","/submitReservation","/savereservation","/success","/modifierroom","/updatereservation","/ajouterreservation","/detailsreservation" ,"/ajouterroo","/detailsroom","/editroom","/updateroom","/detailroom","/ajouterroom","/ajouterOOnceroom","/deleteroom","/ajouterOOnce","/detailscustomer","/updatecustomer","/ajouterOnce","/deletecustomer","/ajoutercustomer","/ajouter","/editcustomer","/detailhotel","/editHotel","/ajouterOncee","/ajouteradmin","/editadmin","/ajouterhotel","/deleteAdmin","/details","/detailsadmin","/updateadmin","/ajouterr","/ajouterh").authenticated()
                        .requestMatchers("/indexpage","/hello","/listadmin","/login" ,"/about","/indexlayout","/welcome","/listroom","/listcustomer","/error","/", "/webjars/**").permitAll())
                .formLogin((form -> form.loginPage("/login").permitAll()));
                //.formLogin(Customizer.withDefaults())
                //.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails adminmanager = User.withUsername("adminmanager")
                .password("12345")
                .roles("managetable", "delete", "update")
                //.roles("admin")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                //.authorities("admin")
                .roles("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(adminmanager, admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

