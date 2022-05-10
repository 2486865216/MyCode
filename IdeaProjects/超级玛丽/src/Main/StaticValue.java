package Main;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    //    背景
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
    //    跳跃
    public static BufferedImage jump_left = null;
    public static BufferedImage jump_right = null;
    //    站立
    public static BufferedImage stand_left = null;
    public static BufferedImage stand_right = null;
    //    城堡
    public static BufferedImage tower = null;
    //    旗杆
    public static BufferedImage gan = null;
    //    障碍物
    public static List<BufferedImage> obstacle = new ArrayList<>();
    //    跑
    public static List<BufferedImage> run_left = new ArrayList<>();
    public static List<BufferedImage> run_right = new ArrayList<>();
    //    蘑菇
    public static List<BufferedImage> mogu = new ArrayList<>();
    //    食人花
    public static List<BufferedImage> flower = new ArrayList<>();
    //    路径前缀
    public static String path = System.getProperty("user.dir") + "/src/images/";

    //     初始化方法
    public static void init() {
        try {
//            加载图片
            bg = ImageIO.read(new File(path + "bg.png"));
            bg2 = ImageIO.read(new File(path + "bg2.png"));

            stand_left = ImageIO.read(new File(path + "s_mario_stand_L.png"));
            stand_right = ImageIO.read(new File(path + "s_mario_stand_R.png"));

            tower = ImageIO.read(new File(path + "tower.png"));

            gan = ImageIO.read(new File(path + "gan.png"));

            jump_left = ImageIO.read(new File(path + "s_mario_jump1_L.png"));
            jump_right = ImageIO.read(new File(path + "s_mario_jump1_R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        跑
        for (int i = 1; i <= 2; i++) {
            try {
                run_right.add(ImageIO.read(new File(path + "s_mario_run" + i + "_R.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        地面
        try {
            obstacle.add(ImageIO.read(new File(path + "brick.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        水管
        for (int i = 1; i <= 4; i++) {
            try {
                obstacle.add(ImageIO.read(new File(path + "pipe" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        砖块和棋子
        try {
            obstacle.add(ImageIO.read(new File(path + "brick2.png")));
            obstacle.add(ImageIO.read(new File(path + "flag.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
//         蘑菇
        for (int i = 1; i <= 3; i++) {
            try {
                mogu.add(ImageIO.read(new File(path + "fungus" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        食人花
        for (int i = 1; i <= 2; i++) {
            try {
                mogu.add(ImageIO.read(new File(path + "flower1." + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
