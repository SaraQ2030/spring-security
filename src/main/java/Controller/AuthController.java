package Controller;

import ApiException.ApiREsponse;
import Model.User;
import Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiREsponse("registerd"));
    }
    @RequestMapping("/get")
    public ResponseEntity getAllusers(){
        return ResponseEntity.status(200).body( authService.getUsers());
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid User user){
        authService.updateUser(user);
        return ResponseEntity.status(200).body(new ApiREsponse("updated"));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity delete(@PathVariable String username){
        authService.deleteUser(username);
        return ResponseEntity.status(200).body(new ApiREsponse("deleted"));
    }



    }

