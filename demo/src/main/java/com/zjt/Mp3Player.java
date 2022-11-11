package com.zjt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("mp3")
public class Mp3Player implements MediaPlayer{
    Logger logger = LoggerFactory.getLogger(Mp3Player.class);
    @Override
    public void play() {
        logger.info(" I am mp3 playing ...");
    }
}
