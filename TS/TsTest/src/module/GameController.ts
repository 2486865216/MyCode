//控制所有的类
import Snake from "./Snake";
import Food from "./Food";
import ScorePanel from "./ScorePanel";

class GameController {
    snake: Snake;
    food: Food;
    scorePanel: ScorePanel;
    //保存蛇的前进方向
    direction: string = '';
    previousDirection: string = '';
    static game: GameController
    time: any = 0

    constructor() {
        this.snake = new Snake();
        this.food = new Food();
        this.scorePanel = new ScorePanel(5, 5);
        this.Head();
    }

    static getGameController() {
        if (!this.game) {
            this.game = new GameController();
        }
        return this.game;
    }

    //随机蛇头
    Head() {
        let left = Math.round(Math.random() * 29) * 10;
        let top = Math.round(Math.random() * 29) * 10;
        if (left !== this.food.X && top !== this.food.Y) {
            this.snake.head.style.left = left + 'px';
            this.snake.head.style.top = top + 'px';
        }
    }

    //游戏的初始化方法
    init() {
        //绑定键盘事件
        document.addEventListener('keydown', this.KeyDownHandler.bind(this));
        this.run()
    }

    /**
     *   ArrowUp
     ArrowDown
     ArrowLeft
     ArrowRight
     * @param event
     * @constructor
     */
    KeyDownHandler(event: KeyboardEvent) {
        if (this.snake.bodies.length === 1) {
            if (event.key === " ") {
                if (this.direction === "stop") {
                    this.direction = this.previousDirection;
                } else {
                    this.previousDirection = this.direction;
                    this.direction = "stop";
                }
            } else {
                this.direction = event.key;
            }
            return;
        }
        switch (event.key) {
            case "ArrowUp":
            case "w":
            case "W":
                if (this.direction !== "ArrowDown" && this.direction !== "s" && this.direction !== "S") {
                    this.direction = event.key;
                }
                break;
            case "ArrowDown":
            case "s":
            case "S":
                if (this.direction !== "ArrowUp" && this.direction !== "w" && this.direction !== "W") {
                    this.direction = event.key;
                }
                break;
            case "ArrowLeft":
            case "a":
            case "A":
                if (this.direction !== "ArrowRight" && this.direction !== "d" && this.direction !== "D ") {
                    this.direction = event.key;
                }
                break;
            case "ArrowRight":
            case "d":
            case "D":
                if (this.direction !== "ArrowLeft" && this.direction !== "a" && this.direction !== "A") {
                    this.direction = event.key;
                }
                break;
            case " ":
                if (this.direction !== "space") {
                    this.previousDirection = this.direction;
                    this.direction = "space";
                } else {
                    this.direction = this.previousDirection;
                }
                break;
            default:
                break;
        }
    }

    //蛇移动的函数
    run() {
        //获取现在的坐标
        let x = this.snake.X
        let y = this.snake.Y
        switch (this.direction) {
            case "ArrowUp":
            case "w":
            case "W":
                y -= 10;
                break;
            case "ArrowDown":
            case "s":
            case "S":
                y += 10;
                break;
            case "ArrowLeft":
            case "a":
            case "A":
                x -= 10;
                break;
            case "ArrowRight":
            case "d":
            case "D":
                x += 10;
                break;
        }
        //吃到食物
        this.checkEat(x, y)
        //修改蛇的位置
        this.snake.X = x;
        this.snake.Y = y;
        this.gameOver();
        if (this.snake.isHead) {
            clearTimeout(this.time)
            return;
        }
        if (this.snake.isWall) {
            clearTimeout(this.time)
            return;
        }
        this.time = setTimeout(this.run.bind(this), 300 - (this.scorePanel.level - 1) * 30);
    }
    //检查蛇是否吃到食物
    checkEat(x: number, y: number) {
        if (x === this.food.X && y === this.food.Y) {
            this.food.change();
            this.scorePanel.addScore();
            this.snake.addBody();
        }
    }

    gameOver() {
        let overGame = document.getElementById("overGame")!
        let message = document.getElementById("message")!
        if (this.snake.isHead) {
            let end = "撞到自己了!";
            message.innerHTML = end;
            overGame.style.opacity = "1";
        }
        if (this.snake.isWall) {
            let end = "撞到墙了!";
            message.innerHTML = end;
            overGame.style.opacity = "1";
        }
    }
}

export default GameController