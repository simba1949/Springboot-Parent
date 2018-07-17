package top.simba1949.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.simba1949.common.Person;

import java.util.ArrayList;

/**
 * @author simba@onlying.cn
 * @date 2018/7/15 18:04
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @GetMapping
    public String index(Model model){
        Person single = new Person("李白", 18);

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("杜甫",19));
        people.add(new Person("白居易",20));
        people.add(new Person("张旭",21));

        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);

        return "/index";
    }
}
