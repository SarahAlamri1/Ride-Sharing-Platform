package API.Gateway.Service.API.Gateway.Service.service;

import API.Gateway.Service.API.Gateway.Service.entity.AppUser;
import API.Gateway.Service.API.Gateway.Service.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("DEBUG: username: " + username);
        Optional<AppUser> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            System.out.println("DEBUG: user.isPresent(): " + user.isPresent());
            var userObj = user.get();
            System.out.println("DEBUG: userObj: " + userObj);

            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole().toString())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }

    }

}
