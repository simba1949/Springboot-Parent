package top.simba1949.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 11:56
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping
    public String string(Model model){
        model.addAttribute("user","李白");
        return "/index";
    }
}
