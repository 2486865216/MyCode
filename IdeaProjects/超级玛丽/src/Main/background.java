package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class background {
    private BufferedImage bgImage = null;
    //    第几个场景
    private int sort;
    //    是否是最后一个场景
    private boolean flag;
    //  所有的障碍物
    private List<obstacle> obstacleslist = new ArrayList<>();

    private BufferedImage gan = null;

    private BufferedImage tower = null;

    public background() {

    }

    public background(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if (flag) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }
        if (sort == 1) {
//            地面，上地面type1，下地面type2
            for (int i = 0; i < 27; i++) {
                obstacleslist.add(new obstacle(i * 30, 420, 1, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleslist.add(new obstacle(i * 30, 570 - j, 2, this));
                }
            }
//            砖块
            for (int i = 120; i <= 150; i += 30) {
                obstacleslist.add(new obstacle(i, 300, 7, this));
            }

            for (int i = 300; i <= 570; i += 30) {
                if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                    obstacleslist.add(new obstacle(i, 300, 7, this));
                } else {
                    obstacleslist.add(new obstacle(i, 300, 0, this));
                }
            }

            for (int i = 420; i <= 450; i++) {
                obstacleslist.add(new obstacle(i, 240, 7, this));
            }

//            水管
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleslist.add(new obstacle(620, i, 3, this));
                    obstacleslist.add(new obstacle(645, i, 4, this));
                } else {
                    obstacleslist.add(new obstacle(620, i, 5, this));
                    obstacleslist.add(new obstacle(645, i, 6, this));
                }
            }
        }
        if (sort == 2) {
//            地面，上地面type1，下地面type2
            for (int i = 0; i < 27; i++) {
                obstacleslist.add(new obstacle(i * 30, 420, 1, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleslist.add(new obstacle(i * 30, 570 - j, 2, this));
                }
            }
//          水管1
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleslist.add(new obstacle(60, i, 3, this));
                    obstacleslist.add(new obstacle(85, i, 4, this));
                } else {
                    obstacleslist.add(new obstacle(60, i, 5, this));
                    obstacleslist.add(new obstacle(85, i, 6, this));
                }
            }
//          水管2
            for (int i = 330; i <= 600; i += 25) {
                if (i == 330) {
                    obstacleslist.add(new obstacle(620, i, 3, this));
                    obstacleslist.add(new obstacle(645, i, 4, this));
                } else {
                    obstacleslist.add(new obstacle(620, i, 5, this));
                    obstacleslist.add(new obstacle(645, i, 6, this));
                }
            }

            obstacleslist.add(new obstacle(300, 330, 0, this));

            for (int i = 270; i <= 330; i += 30) {
                if (i == 270 || i == 330) {
                    obstacleslist.add(new obstacle(i, 360, 0, this));
                } else {
                    obstacleslist.add(new obstacle(i, 360, 7, this));
                }
            }

            for (int i = 240; i <= 360; i += 30) {
                if (i == 240 || i == 360) {
                    obstacleslist.add(new obstacle(i, 390, 0, this));
                } else {
                    obstacleslist.add(new obstacle(i, 390, 07, this));
                }
            }

            obstacleslist.add(new obstacle(240, 300, 0, this));

            for (int i = 360; i <= 540; i += 60) {
                obstacleslist.add(new obstacle(i, 270, 7, this));
            }
        }
        if (sort == 3) {
//            地面，上地面type1，下地面type2
            for (int i = 0; i < 27; i++) {
                obstacleslist.add(new obstacle(i * 30, 420, 1, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleslist.add(new obstacle(i * 30, 570 - j, 2, this));
                }
            }

            int temp = 290;
            for (int i = 390; i >= 270; i -= 30) {
                for (int j = temp; j <= 410; j += 30) {
                    obstacleslist.add(new obstacle(j, i, 7, this));
                }
                temp += 30;
            }

            temp = 60;
            for (int i = 390; i >= 360; i -= 30) {
                for (int j = temp; j <= 90; j += 30) {
                    obstacleslist.add(new obstacle(j, i, 7, this));
                }
                temp += 30;
            }
            gan = StaticValue.gan;
            tower = StaticValue.tower;

        }
    }


    public List<obstacle> getObstacleslist() {
        return obstacleslist;
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }
}
