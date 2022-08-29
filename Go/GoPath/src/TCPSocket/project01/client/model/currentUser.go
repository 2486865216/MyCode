package model

import "net"
import common "TCPSocket/project01/common/message"

type CurUser struct {
	Conn net.Conn
	common.User
}
