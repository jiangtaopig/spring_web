package com.zjt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "cdPlayer")
public class CDPlayer {

//    @Autowired // 属性注入的话会警告：Field injection is not recommended ，为什么呢？
    // 如果你在 TestAutoScan 的 cdPlayer 方法中，直接 new CDPlayer()，那么就会导致这里使用的 @Autowired 注解的对象为null，因为
    // @Autowired注入时是将类交给 Spring 管理，而new出来的实例脱离了 Spring 的管理，两个东西不在一个管理者管理下，所以没法联系在一起，@Autowired 注入就会为null
    // 所以推荐使用 构造器注入 或者 属性的 Setter 方法注入
    private Mp3Player mp3Player;

    // 这个注解表示，在 Spring 创建 CDPlayer 的时候，会通过这个构造器进行实例化并且会传入一个可设置给 Mp3Player 类型的 bean.
    // 构造器上 @Autowired 注解可加可不加
//    @Autowired
    public CDPlayer(Mp3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    // Autowired 不仅可以用在构造器上，还可以用在属性的 Setter 方法上
    // setter 方法上，@Autowired 注解不可以省略
    @Autowired
    public void setMp3Player(Mp3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    public void play() {
        System.out.println(" I am playing");
        mp3Player.play();
    }
}
