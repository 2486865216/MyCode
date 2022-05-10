package Main;

import java.awt.image.BufferedImage;

public class obstacle {
    private int x;
    private int y;

    //    障碍物类型
    private int type;

    //    显示图像
    private BufferedImage show = null;
    //    当前的场景对象
    private background bg = null;

    public obstacle(int x, int y, int type, background bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacle.get(type);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }
}
