package test02

import (
	"testing"
)

func TestMonster_Store(t *testing.T) {
	monster := Monster{
		"hello world",
		18,
	}
	res := monster.Store()
	if !res {
		t.Fatalf("Store测试失败%v", res)
	}
	t.Logf("Store测试成功%v", res)
}
func TestMonster_UnStore(t *testing.T) {
	var monster Monster
	res := monster.UnStore()
	if !res {
		t.Fatalf("UnStore测试失败%v", res)
	}
	t.Logf("UnStore测试成功%v", res)
}
