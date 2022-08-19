package com.example.demo;

import com.example.demo.entity.LoginReq;
import com.example.demo.entity.LoginResp;
import com.example.demo.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


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
     *
     * path 和 value 的注解是一样的作用
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResp login(@RequestParam(value = "userName") String userName, @RequestParam(value = "pwd") String pwd, HttpServletRequest servletRequest) {
        System.out.println("---- login ----- userName = " + userName + " , path = " + servletRequest.getContextPath());
        // 我在 getUserInfo 中设置了 session
        HttpSession session = servletRequest.getSession();
        String secret = (String) session.getAttribute("secret");
        System.out.println("secret = " + secret);

        // 获取 cookie
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                String path = cookie.getPath();
                System.out.println("cookie >> name = " + name + " , value = " + value + " , path = " + path);
            }
        }

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
    @PostMapping(value = "/login2")
    @ResponseBody
    public LoginResp login2(@RequestBody LoginReq req, HttpServletRequest servletRequest) {
        System.out.println("---- login2 ----- userName = " + req.getUserName());

        // 获取 cookie
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("cookie >> name = " + name + " , value = " + value);
            }
        }


        if (req.getUserName().isEmpty() || req.getPwd().isEmpty()) {
            throw new RuntimeException("userName or pwd is empty");
        }
        return new LoginResp("success", 200);
    }

    // http://localhost:8180/demo_war_exploded/getUserInfo
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam(value = "userId") String userId, HttpServletRequest servletRequest, HttpServletResponse response) {
        String url = servletRequest.getRequestURL().toString();
        HttpSession session = servletRequest.getSession();

        session.setAttribute("secret", "i love u");

        // 获取用户的请求参数
        Map<String, String []> map = servletRequest.getParameterMap();
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, String []>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String []> entry = iterator.next();
                String key = entry.getKey();
                String[] value = entry.getValue();
                System.out.println("key = " + key);
                if (value != null && value.length > 0) {
                    for (String v : value) {
                        System.out.println("v = " + v);
                    }
                }
            }
        }

        Cookie cookie = new Cookie("user", "zhu");
        cookie.setMaxAge(10000);
        // 给浏览器传一个cookie
        response.addCookie(cookie);

        Cookie cookie1 = new Cookie("age3", "23");
        // 设置了 path，那么就只有 /login接口可以获取 age3 这个 cookie 了, /login2 无法获取这个 cookie
        cookie1.setPath(servletRequest.getContextPath() + "/login");
        cookie1.setMaxAge(4455);
        response.addCookie(cookie1);

        System.out.println("---- getUserInfo ----- userId = " + userId + " , url = " + url);
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
