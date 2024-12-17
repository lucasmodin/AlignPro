package alignpro.Controller;

import alignpro.Model.ProjectManager;
import alignpro.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        Integer pm = (Integer) session.getAttribute("pmUserID");
        boolean isLoggedIn = (pm != null);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("pmUserID", pm);
        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/loginValidation")
    public String loginValidation(@RequestParam("mail") String mail,
                                  @RequestParam("password") String password,
                                  HttpSession session, Model model) {
        ProjectManager projectManager = loginService.getProjectManager(mail);
        if (projectManager == null || !loginService.loginCheck(mail, password)) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }


        session.setAttribute("pmUserID", projectManager.getProjectManagerID());
        return "redirect:/pm-dashboard/" + projectManager.getProjectManagerID();
    }
}
