package alignpro.Controller;


import alignpro.Service.AlignProService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/AlignPro")
public class AlignProController {

    private final AlignProService alignProService;

    public AlignProController(AlignProService service){
        this.alignProService = service;
    }


}
