package tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@RestController
public class UserController {

    /*@Autowired
    private UserService userService;
    @RequestMapping(value ="/login",method = RequestMethod.POST)
    public String login(@RequestBody @ModelAttribute User user){
        System.out.println("User : " + user);
        int x = userService.findByUsername(user);
        if(x == 1) {
            return "index";
        }
        else {
            return "secured";
        }
    }*/
}
