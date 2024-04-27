package karuna.karuna_backend.Authentication;

import karuna.karuna_backend.Models.User;
import karuna.karuna_backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found in the database")
        );

        return new CustomUserDetails(user);
    }
}
