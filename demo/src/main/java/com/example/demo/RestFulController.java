package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.LoginReq;
import com.example.demo.entity.LoginResp;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.ZjtLoginReq;
import com.example.demo.service.IDataService;
import org.jetbrains.annotations.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


// @RestController:该类下所有方法不走视图解析器，返回一个json数据
@Controller
@PropertySource({"classpath:application.properties"})
public class RestFulController {

    /**
     * 由于我在项目中有2个IDataService 的实现类 OracleService 和 SqlService，所以会报错：
     * No qualifying bean of type 'com.example.demo.service.IDataService' available:
     * expected single matching bean but found 2: oracleService,sqlService
     *
     * 所以需要加上注解 Qualifier 来限定具体调用哪个
     */
    @Qualifier("oracleService") // 使用 Component 、Repository (而 Repository 本身被 @Component 标记，所以它们都是间接标记了 @Componen) 等注解生成的 Bean , ID 默认是类名首字母小写
    @Autowired
    IDataService dataService;

    @Value("${passWord}")
    String passWord;

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

    @RequestMapping(value = "/sum/{b}", method = RequestMethod.GET)
    @ResponseBody
    public int sum(@PathVariable @Range(from = 2,to = 5) int b) {
        return  b;
    }

    /**
     * http://localhost:8180/demo_war_exploded/login
     * 由于是 post 请求，浏览器默认不支持，所以需要使用 postman 工具来模拟
     * <p>
     * path 和 value 的注解是一样的作用
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResp login(@RequestParam(value = "userName") String userName, @RequestParam(value = "pwd") String pwd, HttpServletRequest servletRequest) {
        System.out.println("---- login ----- userName = " + userName + " , path = " + servletRequest.getContextPath());
        // 我在 getUserInfo 中设置了 session
        HttpSession session = servletRequest.getSession();
        // 由于在 getUserInfo 中设置了最大周期为10s， 所以10s后就获取不到 secret 了
        // session 的周期指的是不活动时间，比如我们设置10s，那么10s 内没有访问session，session 中属性失效，
        // 如果在9s的时候访问了 session 则重新计时10s.

        String secret = (String) session.getAttribute("secret");
        long time = session.getCreationTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String sessionTime = simpleDateFormat.format(date);

        String[] names = session.getValueNames();

        if (names != null && names.length > 0) {
            for (String name : names) {
                System.out.println("---- name ---- " + name);
            }
        }

        // session 最后活跃时间
        long lastTime = session.getLastAccessedTime();
        String lastSessionTime = simpleDateFormat.format(new Date(lastTime));
        System.out.println("secret = " + secret + ", sessionTime = " + sessionTime + " , lastSessionTime = " + lastSessionTime);

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
     */
    @PostMapping(value = "/login2")
    @ResponseBody
    public LoginReq login2(@RequestBody LoginReq req, HttpServletRequest servletRequest) {
        System.out.println("---- login2 ----- userName = " + req.getUserName());

        String user = JSONObject.toJSONString(req);
        System.out.println("---- login2 ----- user = " + user);

        System.out.println("---- login2 -----  birthday = " + req.getBirthday());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = null;
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(req.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("date = " + simpleDateFormat.format(date));

        req.setBirthday(new Date());

        // 获取 cookie
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("cookie >> name = " + name + " , value = " + value);
            }
        }

//        int a = 10 / 0;

        if (req.getUserName().isEmpty() || req.getPwd().isEmpty()) {
            throw new RuntimeException("userName or pwd is empty");
        }
//        return new LoginResp("success", 200);

        return req;
    }

    // http://localhost:8180/demo_war_exploded/getUserInfo
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam(value = "userId") String userId, HttpServletRequest servletRequest, HttpServletResponse response) {
        String url = servletRequest.getRequestURL().toString();
        HttpSession session = servletRequest.getSession();
        session.setMaxInactiveInterval(10); // 设置失效时间为 10s

        session.setAttribute("secret", "i love u");

        dataService.showMsg("哈哈哈哈");

        // 获取用户的请求参数
        Map<String, String[]> map = servletRequest.getParameterMap();
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String[]> entry = iterator.next();
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
        System.out.println("------------- " + passWord);
        if (userId.isEmpty()) {
            throw new RuntimeException("userId is empty");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setName("pig");
        userInfo.setAge(23);
        return userInfo;
    }

    // 注意这里面的入参 ZjtLoginReq 前面不能加 @RequestBody 注解

    /**
     * 由于入参中包含 date 类型，而 login.jsp 中的日期是 字符串，
     * 所以服务器报错：Failed to convert property value of type 'java.lang.String' to required type 'java.util.Date'
     * 加上下面的 initBinder 方法且该方法要加 @InitBinder 注解
     */
    @PostMapping(value = "zjt_login")
    public String zjtLogin(ZjtLoginReq req, HttpServletRequest servletRequest, Model model) {
        System.out.println("---- zjtLogin ----- userName = " + req.getUserName());
        if (req.getUserName().isEmpty() || req.getPwd().isEmpty()) {
            throw new RuntimeException("userName or pwd is empty");
        }
        model.addAttribute("msg", "我是 zjt_login 接口的返回数据 ");
        // 返回一个 jsp
        return "zjtLoginResp";
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // 因为 login.jsp 中写的日期是 1988-08-22，所以下面的格式要保持一致，写成 ‘yyyy-MM-dd’
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}
