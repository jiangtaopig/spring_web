package com.example.demo;

import com.example.demo.entity.LoginReq;
import com.example.demo.entity.LoginResp;
import com.example.demo.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


// @RestControoler:该类下所有方法不走视图解析器，返回一个json数据
@Controller
public class RestFulController {

    /**
     * http://localhost:8180/demo_war_exploded/add?a=3&b=4
     */
    @RequestMapping("/add")
    public String getAdd1(int a, int b, Model model) {
        int result = a + b;
        model.addAttribute("addResult", "原生的url：结果为 >> " + result);
        return "add";
    }

    /**
     * RestFul方式一：method = get
     * RequestMapping("/addRest/{a}/{b}" method=requestMethod.GET) = @GetMapping()
     * http://localhost:8180/demo_war_exploded/addRest/1/1
     */
    @GetMapping("/addRest/{a}/{b}")
    public String getAdd2(@PathVariable int a, @PathVariable int b, Model model) {
        int result = a + b;
        model.addAttribute("add", "Rest的url：结果为" + result);
        return "addRest";
    }

    /**
     * 复用相同的url
     * RestFul方式二：method=post，使用RestFul的话，请求的url和GET就一样了
     * http://localhost:8180/demo_war_exploded/addPostRest/1/1
     * 由于浏览器是不支持 post 访问的，所以在浏览器中输入上面的 url 会报错：Request method 'GET' not supported
     */
    @PostMapping("/addPostRest/{a}/{b}")
    public String postAdd(@PathVariable int a, @PathVariable int b, Model model) {
        int result = a + b;
        model.addAttribute("add", "Rest post url：结果为" + result);
        return "addPostRest";
    }

    /**
     * http://localhost:8180/demo_war_exploded/login
     * 由于是 post 请求，浏览器默认不支持，所以需要使用 postman 工具来模拟
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResp login(@RequestParam(value = "userName") String userName, @RequestParam(value = "pwd") String pwd) {
        System.out.println("---- login ----- userName = " + userName);
        if (userName.isEmpty() || pwd.isEmpty()) {
            throw new RuntimeException("userName or pwd is empty");
        }
        return new LoginResp("success", 200);
    }

    /**
     * 返回类型为自定义数据结构时，需要加上 @ResponseBody 的注解，
     * 因为 @ResponseBody 该方法不走视图解析器，返回一个json数据
     *
     */
    @PostMapping("/login2")
    @ResponseBody
    public LoginResp login2(@RequestBody LoginReq req) {
        System.out.println("---- login2 ----- userName = " + req.getUserName());
        if (req.getUserName().isEmpty() || req.getPwd().isEmpty()) {
            throw new RuntimeException("userName or pwd is empty");
        }
        return new LoginResp("success", 200);
    }

    // http://localhost:8180/demo_war_exploded/getUserInfo
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam(value = "userId") String userId) {
        System.out.println("---- getUserInfo ----- userId = " + userId);
        if (userId.isEmpty()) {
            throw new RuntimeException("userId is empty");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setName("pig");
        userInfo.setAge(23);
        return userInfo;
    }

}
