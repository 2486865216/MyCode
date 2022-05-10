package com.qiqi.Facade;

import javafx.scene.control.ScrollPane;
import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/6  13:34
 */
//外观
public class HomeFacade {
    //定义各个子系统对象
    private DVDPlayer dvdPlayer;
    private Screen screen;
    private Stereo stereo;
    private TV tv;

    public HomeFacade() {
        this.dvdPlayer = DVDPlayer.GetInstance();
        this.screen = Screen.getInstance();
        this.stereo = Stereo.GetInstance();
        this.tv = TV.getInstance();
    }
    public void ready(){
        dvdPlayer.on();
        screen.up();
        stereo.on();
        tv.on();
    }
    public void play(){
        dvdPlayer.play();
        tv.play();
    }
    public void off(){
        dvdPlayer.off();
        stereo.off();
        screen.down();
        tv.off();
    }
}
