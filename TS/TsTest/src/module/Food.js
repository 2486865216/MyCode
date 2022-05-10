//定义食物
class Food {
    constructor() {
        //获取food
        this.element = document.getElementById('food');
        this.change();
    }
    //获取食物x坐标
    get X() {
        return this.element.offsetLeft;
    }
    //获取食物y坐标
    get Y() {
        return this.element.offsetTop;
    }
    //修改食物的位置
    change() {
        //生成随机的位置
        //最小是0，最大是290
        //蛇一次移动10px,x,y为十的倍数
        let left = Math.round(Math.random() * 27 + 1) * 10;
        let top = Math.round(Math.random() * 27 + 1) * 10;
        this.element.style.left = left + 'px';
        this.element.style.top = top + 'px';
    }
}
export default Food;
