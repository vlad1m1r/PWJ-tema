package zvn.tema.controller;

import net.minidev.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController implements ErrorController {

    @GetMapping(value = "/app")
    public String app(Model model){
        model.addAttribute("tableColumns",new String[]{"ISBN","Name","Author","Action"});
        return "app";
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public String handleError() {
        var response= new JSONObject();
        response.put("error","Internal server error");
        return response.toString();
    }
}
