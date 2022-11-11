package com.zjt;

import org.springframework.stereotype.Component;

@Component("mp4")
public class Mp4Player implements MediaPlayer{
    @Override
    public void play() {
        System.out.println("I am mp4 player .................");
    }
}
