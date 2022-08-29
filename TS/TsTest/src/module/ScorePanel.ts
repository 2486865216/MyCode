//记分牌
class ScorePanel{
    //记录分数和等级
    score = 0;
    level = 1;
    //设置一个变量限制等级
    maxLevel: number;
    //设置一个变量表示多少分升级
    upScore: number;
    scoreElement: HTMLElement;
    levelElement: HTMLElement;
    constructor(maxLevel: number = 10,upScore: number = 10){
        this.scoreElement = document.getElementById('score')!;
        this.levelElement = document.getElementById('level')!;
        this.scoreElement.innerText = this.score + '';
        this.levelElement.innerText = this.level + '';
        this.maxLevel = maxLevel;
        this.upScore = upScore
    }
    //设置加分方法
    addScore(){
        this.scoreElement.innerText = ++this.score + '';
        if (this.score % this.upScore == 0){
            this.levelUP();
        }
    }
    //设置加等级方法
    levelUP(){
        if (this.level < this.maxLevel){
            this.levelElement.innerText = ++this.level + '';
        }
    }
}
// const scorePanel = new ScorePanel()
// for (let i = 0; i < 200; i++) {
//     scorePanel.addScore()
// }
export default ScorePanel