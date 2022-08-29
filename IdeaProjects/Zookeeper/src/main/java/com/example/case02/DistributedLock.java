package com.example.case02;

import com.example.case01.DistributionServer;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * author ye
 * createDate 2022/5/24  13:58
 */
public class DistributedLock {

    private ZooKeeper zk;

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CountDownLatch waitWatch = new CountDownLatch(1);

    private String waitPath;
    private String currentNode;

    public DistributedLock() throws InterruptedException, KeeperException {
        //获取连接
        try {
            zk = new ZooKeeper(DistributionServer.connectionString, DistributionServer.sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //连接成功释放
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected){
                        countDownLatch.countDown();
                    }

                    //释放waitWatch锁
                    if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(waitPath)){
                        waitWatch.countDown();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //zk连接成功释放
        countDownLatch.await();

        //判断节点是否存在
        Stat exists = zk.exists("/locks", false);
        if (exists == null) {
            zk.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    //枷锁
    public void zkLock() throws InterruptedException, KeeperException {
        //创建临时节点
        currentNode = zk.create("/locks/" + "seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        //判断是否是序号最小的节点,如果是，获取到锁，如果不是，监听上一个节点
        List<String> children = zk.getChildren("/locks", false);
        if (children.size() == 1) {
            return;
        }else {
            Collections.sort(children);

            //获取节点名称
            String thisNode = currentNode.substring("/locks/".length());
            //获取在集合中的位置
            int index = children.indexOf(thisNode);

            if (index == -1){
                System.out.println("数据异常");
            }else if (index == 0){
                return;
            }else {
                waitPath = "/locks/" + children.get(index - 1);
                zk.getData(waitPath, true, null);
                waitWatch.await();

                return;
            }
        }
    }

    //解锁
    public void zkUnLock() {
        //删除节点
        try {
            zk.delete(currentNode, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
