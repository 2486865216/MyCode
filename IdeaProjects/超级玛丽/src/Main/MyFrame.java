package Main;

import javafx.scene.layout.Background;
import sun.awt.image.OffScreenImage;
import sun.java2d.pipe.DrawImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener, Runnable {
    //    所有背景
    private List<background> allbg = new ArrayList<>();
    //    当前背景
    private background nowbg = new background();
    //    用于双缓存
    private Image OffScreenImage = null;

    //  马里奥对象
    Mario mario = new Mario();
    //    线程对象，实现马里奥的移动
    private Thread thread = new Thread(this);

    public MyFrame() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(this);
        this.setTitle("超级玛丽");
//      初始化图片
        StaticValue.init();
//        初始马里奥对象
        mario = new Mario(10, 395);

//       创建全部场景
        for (int i = 1; i <= 3; i++) {
            allbg.add(new background(i, i == 3 ? true : false));
        }
        nowbg = allbg.get(0);
        mario.setBackground(nowbg);
//        绘制图像
        repaint();

        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        if (OffScreenImage == null) {
            OffScreenImage = createImage(800, 600);
        }
        Graphics graphics = OffScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);

//        绘制背景
        graphics.drawImage(nowbg.getBgImage(), 0, 0, this);

        for (obstacle ob : nowbg.getObstacleslist()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }
//        城堡
        graphics.drawImage(nowbg.getTower(), 620, 270, this);
//        旗杆
        graphics.drawImage(nowbg.getGan(), 500, 220, this);
//        马里奥
        graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
//        绘制图片
        g.drawImage(OffScreenImage, 0, 0, this);

    }

    public static void main(String[] args) {
        MyFrame myframe = new MyFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
//    按下
    public void keyPressed(KeyEvent e) {
//        右移
        if (e.getKeyCode() == 39) {
            mario.RightMove();
        }
//        左移
        if (e.getKeyCode() == 37) {
            mario.LeftMove();
        }
    }

    @Override
//    松开
    public void keyReleased(KeyEvent e) {
//      向右停止
        if (e.getKeyCode() == 39) {
            mario.RightStop();
        }
//      向左停止
        if (e.getKeyCode() == 37) {
            mario.LeftStop();
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);

                if (mario.getX() >= 775) {
                    nowbg = allbg.get(nowbg.getSort());
                    mario.setBackground(nowbg);
                    mario.setX(10);
                    mario.setY(395);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
