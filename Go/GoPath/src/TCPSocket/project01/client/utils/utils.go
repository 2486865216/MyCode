package utils

import (
	common "TCPSocket/project01/common/message"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
)

type Transfer struct {
	Conn net.Conn
	Buffer [1024 * 8]byte
}

func (this *Transfer) ReadMessage() (msg common.Message, err error) {
	buffer := make([]byte, 1024 * 8)
	fmt.Println("读取服务端发送的消息")
	_, err = this.Conn.Read(buffer[:4])
	//fmt.Println("等待", conn.RemoteAddr().String())
	if err != nil {
		return
	}
	var pkglen uint32
	pkglen = binary.BigEndian.Uint32(buffer[:4])

	//根据pkglen读取信息
	n, err := this.Conn.Read(buffer[:pkglen])
	if n != int(pkglen) || err != nil {
		fmt.Println("read data error = ", err)
		return
	}

	err = json.Unmarshal(buffer[:pkglen], &msg)
	if err != nil {
		return common.Message{}, err
	}
	return
}

func (this * Transfer) WriteMessage(data []byte) (err error) {
	//先发送长度
	pckLen := uint32(len(data))
	binary.BigEndian.PutUint32(this.Buffer[0:4], pckLen)
	n, err := this.Conn.Write(this.Buffer[:4])
	if n != 4 || err != nil {
		fmt.Println("conn Write error = ", err)
		return
	}

	//发送数据
	n, err = this.Conn.Write(data)
	if n != int(pckLen) || err != nil {
		fmt.Println("conn Write error = ", err)
		return
	}
	return
}

