package hu.unideb.inf.finalproject.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> user = accountRepository.findByUserName(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        if (user.get().getRole().equals(Role.ADMIN)) {
            roles.add(new SimpleGrantedAuthority("ADMIN"));
            roles.add(new SimpleGrantedAuthority("USER"));
        } else if (user.get().getRole().equals(Role.USER)) {
            roles.add(new SimpleGrantedAuthority("USER"));
        }

        return User.withUsername(user.get().getUserName())
            .password(user.get().getPassword())
            .authorities(roles)
            .build();

    }
}
