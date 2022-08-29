package main

import "github.com/gin-gonic/gin"
import "net/http"

func main(){
	render := gin.Default()
	render.GET("/data", func(c *gin.Context){
		data := map[string]string{
			"lang": "go语言",
			"tag": "<br>",
		}
		c.AsciiJSON(http.StatusOK, data)
	})
	render.Run(":8080")
}
