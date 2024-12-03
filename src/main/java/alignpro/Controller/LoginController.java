package alignpro.Controller;

import alignpro.Model.ProjectManager;
import alignpro.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginValidation")
    public String loginValidation(@RequestParam("mail") String mail,
                                  @RequestParam("password") String password,
                                  HttpSession session, Model model) {
        ProjectManager projectManager = loginService.getProjectManager(mail);
        if (loginService.loginCheck(mail, password)) {
            session.setAttribute("pmUserID", projectManager.getProjectManagerID());
            return "redirect:/pm-dashboard/" + projectManager.getProjectManagerID();
        } else {
            model.addAttribute("error", "Invalid mail or password");
            return "login";
        }

    }
}
