//引入样式
import './style/index.less';
import GameController from "./module/GameController";
let button = document.getElementById("begin");
let snake = document.getElementById("snake");
let food = document.getElementById("food");
let beginGame = document.getElementById("beginGame");
let gameOver = document.getElementById("overGame");
let stage = document.getElementById("stage");
button.onclick = function () {
    const game = GameController.getGameController();
    clearTimeout(game.time);
    game.snake.isHead = false;
    game.snake.isWall = false;
    game.direction = "";
    snake.style.display = "block";
    food.style.display = "flex";
    ;
    beginGame.style.display = "none";
    gameOver.style.opacity = "0";
    game.Head();
    game.food.change();
    game.init();
    button.blur();
    stage.focus();
    removeAll(snake);
};
function removeAll(obj) {
    for (let i = obj.childNodes.length - 1; i > 0; i--) {
        obj.removeChild(obj.childNodes[i]);
    }
}
