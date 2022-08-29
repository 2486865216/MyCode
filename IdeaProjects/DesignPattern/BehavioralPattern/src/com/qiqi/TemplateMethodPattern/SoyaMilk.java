package com.qiqi.TemplateMethodPattern;

/**
 * author ye
 * createDate 2022/2/7  11:41
 * 抽象类表示豆浆
 */
public abstract class SoyaMilk {
    //制作，模板方法用final修饰，防止子类覆盖
    final void make(){
        System.out.println("making........");
        select();
        if (customWant()){
            add();
        }
        soak();
        beat();
    }
    //选材料
    final void select(){
        System.out.println("第一步，选好黄豆");
    }
    //添加不同的配料，子类具体实现
    abstract void add();
    //浸泡
    final void soak(){
        System.out.println("第三步，浸泡黄豆三小时");
    }
    //打碎
    final void beat(){
        System.out.println("第四步，打碎黄豆");
    }
    //钩子方法,是否添加配料
    boolean customWant(){
        return true;
    }
}
