package com.test_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    IDecorate sportDecorate() {
        return new SportDecorate();
    }

//    /**
//     * 可以通过如下的配置来为 BMWCar 注入 IDecorate
//     */
//    @Bean
//    public ICar bmwCar() {
//        return new BMWCar(sportDecorate());
//    }

    /**
     * 也可以通过如下的方式来注入，这样是最佳方式，因为它不要求将 IDecorate 声明在同一配置文件中，
     * 甚至不要求 IDecorate 必须声明在 Config 文件中，即如果 IDecorate 声明在 xml 文件中也是可以的
     */
    @Bean
    public ICar bmwCar2(IDecorate iDecorate) {
        return new BMWCar(iDecorate);
    }
}
