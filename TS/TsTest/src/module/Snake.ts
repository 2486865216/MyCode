class Snake{
    //蛇头
    head: HTMLElement;
    //身体
    bodies: HTMLCollection;
    //蛇的容器
    element: HTMLElement;
    isWall = false;
    isHead = false;
    constructor(){
        this.head = document.querySelector('#snake>div')! as HTMLElement;
        this.bodies = document.getElementById('snake')!.getElementsByTagName('div');
        this.element = document.getElementById('snake')!;
    }
    //获取蛇头坐标
    get X(){
        return this.head.offsetLeft
    }
    get Y(){
        return this.head.offsetTop
    }
    set X(value){
        if (this.X === value){
            return;
        }
        if (value < 0 || 290 < value){
            this.isWall = true;
            return;
        }
        this.moveBody();
        this.head.style.left = value + 'px';
        this.checkHeadBody();
    }
    set Y(value){
        if (this.Y === value){
            return;
        }
        if (value < 0 || 290 < value){
            this.isWall = true;
            return
        }
        this.moveBody();
        this.head.style.top = value + 'px';
        this.checkHeadBody();
    }
    //增加蛇的身体
    addBody(){
        this.element.insertAdjacentHTML('beforeend', "<div></div>");
    }
    //身体移动
    moveBody(){
        for (let i = this.bodies.length - 1; i > 0; i--) {
            //获取前面身体的值
            let x = (this.bodies[i - 1] as HTMLElement).offsetLeft;
            let y = (this.bodies[i - 1] as HTMLElement).offsetTop;

            (this.bodies[i] as HTMLElement).style.left = x + 'px';
            (this.bodies[i] as HTMLElement).style.top = y + 'px';
        }
    }
    //是否撞到身体
    checkHeadBody(){
        for (let i = 4; i < this.bodies.length; i++) {
            let isBoom = this.bodies[i] as HTMLElement;
            if (this.X === isBoom.offsetLeft && this.Y === isBoom.offsetTop){
                this.isHead = true;
            }
        }
    }
}
export default Snake