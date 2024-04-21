package Service;

import ApiException.ApiException;
import Model.User;
import Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(User user) {
       user.setRole("CUSTOMER");
       String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());
       user.setPassword(hashPassword);
       authRepository.save(user);
    }

   public List<User> getUsers() {
//        User u=authRepository.findUserByUsername(username);
//        if (u==null){
//            throw new ApiException("User not found");
//        }
       return authRepository.findAll();
    }

    public void updateUser(User user){
        User u=authRepository.findUserById(user.getId());
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        authRepository.save(u);
    }

    public void deleteUser(String username,String usernameDel){
        User u=authRepository.findUserByUsername(username);
        User uDel=authRepository.findUserByUsername(usernameDel);
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
        if (uDel==null){
            throw new ApiException("this user name not in the system yet " +usernameDel) ;
        }

        //i should not write this bc it will check in secutity_confi ?
        if (u.getRole().equals("CUSTOMER")) {
            throw new ApiException("you are not autherized ") ;
        }


        authRepository.delete(uDel);
    }


    public void login(String username, String password) {
        User u=authRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
else if (u.getPassword().equals(password)){
    throw new ApiException("you are logged in") ;
        }
   }

 //   public void logout(String token) {}
}
