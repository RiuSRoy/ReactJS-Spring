package tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    /*public int findByUsername(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        System.out.println("optionalUser : " + optionalUser);
        if(optionalUser.equals(null)) {
            return -1;
        }
        else {
            String password = user.getPassword(); //body
            if(password.equals(optionalUser.get().getPassword())) {
                return 1;
            }
            else{
                return 0;
            }
        }
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        optionalUser.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return optionalUser.map(CustomUserDetails::new).get();
    }
}
