//记分牌
class ScorePanel {
    constructor(maxLevel = 10, upScore = 10) {
        //记录分数和等级
        this.score = 0;
        this.level = 1;
        this.scoreElement = document.getElementById('score');
        this.levelElement = document.getElementById('level');
        this.scoreElement.innerText = this.score + '';
        this.levelElement.innerText = this.level + '';
        this.maxLevel = maxLevel;
        this.upScore = upScore;
    }
    //设置加分方法
    addScore() {
        this.scoreElement.innerText = ++this.score + '';
        if (this.score % this.upScore == 0) {
            this.levelUP();
        }
    }
    //设置加等级方法
    levelUP() {
        if (this.level < this.maxLevel) {
            this.levelElement.innerText = ++this.level + '';
        }
    }
}
// const scorePanel = new ScorePanel()
// for (let i = 0; i < 200; i++) {
//     scorePanel.addScore()
// }
export default ScorePanel;
