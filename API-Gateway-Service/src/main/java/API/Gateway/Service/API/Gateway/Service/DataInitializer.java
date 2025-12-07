package API.Gateway.Service.API.Gateway.Service;

import API.Gateway.Service.API.Gateway.Service.controller.UserType;
import API.Gateway.Service.API.Gateway.Service.entity.AppUser;
import API.Gateway.Service.API.Gateway.Service.repositry.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;


 private DataInitializer(UserRepo userRepository, PasswordEncoder passwordEncoder){
     this.userRepository = userRepository;
     this.passwordEncoder = passwordEncoder;
 }

 @Override
    public void run(String... args) throws Exception {
     CreaterUsers();
 }

 private void CreaterUsers(){

     if (userRepository.count() > 0) {
         System.out.println("Existing users found. ");
//         return;
         userRepository.deleteAll();
     }

     for(int i = 0; i < 5; i++) {
         AppUser user = new AppUser();
         user.setUsername("customer" + i);
         user.setPassword(passwordEncoder.encode("password" + i));
         user.setRole(UserType.CUSTOMER);
         user.setEmail("customer" + i + "@gmail.com");
         user.setPhoneNumber("1234567890");
         userRepository.save(user);
     }


     for(int i =0 ; i<5 ; i++){
         AppUser user = new AppUser();
         user.setUsername("driver"+i);
         user.setPassword(passwordEncoder.encode("password"+i));
         user.setRole(UserType.DRIVER);
         user.setEmail("driver"+i+"@gmail.com");
         user.setPhoneNumber("1234567890");
         userRepository.save(user);
     }


//     for(int i =0 ; i<5 ; i++){
//         User user = new User();
//         user.setUsername("driver_witout_premssion"+i);
//         user.setPassword(passwordEncoder.encode("password"+i));
//         user.setRole(null);
//         userRepository.save(user);
//     }
     System.out.println("Users created successfully!");
 }
}
