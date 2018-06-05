package tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /*@RequestMapping(value = "/doc")
    public String index(@AuthenticationPrincipal final UserDetails userDetails){

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities
                .forEach(System.out::println);
        return "index";
    }*/

    @RequestMapping(value = "/xdoc")
    public String index2(){
        return "index";
    }

    public List<Doctor> fetchAllEmp() {
        return doctorService.findAllDoctors();
    }
}
