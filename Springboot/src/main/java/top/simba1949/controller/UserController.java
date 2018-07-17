package top.simba1949.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.simba1949.properties.BookProperties;
import top.simba1949.properties.TestProperties;
import top.simba1949.properties.TestPropertiess;

import java.io.IOException;

/**
 * @author simba@onlying.cn
 * @date 2018/7/15 11:58
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private TestPropertiess testPropertiess;

    @Autowired
    private BookProperties bookProperties;

    @GetMapping("/string/{str}")
    @ResponseBody
    public String getString(@PathVariable String str){
        return str;
    }

    @GetMapping
    @ResponseBody
    public String getTestProperties(){
        return testProperties.toString();
    }

    @PostMapping
    @ResponseBody
    public String getTestPropertiess() throws IOException {
        return  testPropertiess.output();
    }

    @PutMapping
    @ResponseBody
    public String getBookProperteis(){
        return bookProperties.toString();
    }
}
