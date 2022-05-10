package Main;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Mario implements Runnable {
    //    坐标
    private int x;
    private int y;

    //    当前状态
    private String status;
    //    当前状态对应的图像
    private BufferedImage show = null;
    //    获取障碍物信息
    private background background = new background();
    //    实现马里奥的动作
    private Thread thread = null;
    //      马里奥移动速度
    private int run_speed;
    //      马里奥跳跃速度
    private int jump_speed;
    //    索引
    private int index;

    public Mario() {
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_right;
        this.status = "stand_right";
        thread = new Thread(this);
        thread.start();
    }

    //    左移
    public void LeftMove() {
        run_speed = -5;
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "move-left";
        }
    }

    //    右移
    public void RightMove() {
        run_speed = 5;
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "move-right";
        }
    }

    //    向左停止
    public void LeftStop() {
        run_speed = 0;
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "stop--left";
        }
    }

    //    向右停止
    public void RightStop() {
        run_speed = 0;
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "stop--right";
        }
    }

    @Override
    public void run() {
        while (true) {
            if (run_speed < 0 || run_speed > 0) {
                x += run_speed;
//                判断马里奥是否在屏幕最左边
                if (x < 0) {
                    x = 0;
                }
            }
//            判断当前是否是移动状态
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
//            是否左移
            if ("move--left".equals(status)) {
                show = StaticValue.run_left.get(index);
            }
//            是否右移
            if ("move--right".equals(status)) {
                show = StaticValue.run_right.get(index);
            }
//            是否向左停止
            if ("stop--left".equals(status)) {
                show = StaticValue.stand_left;
            }
//            是否向右停止
            if ("stop--right".equals(status)) {
                show = StaticValue.stand_right;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackground(Main.background background) {
        this.background = background;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getStatus() {
        return status;
    }

    public BufferedImage getShow() {
        return show;
    }
}
