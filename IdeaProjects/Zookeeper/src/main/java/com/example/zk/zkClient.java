package com.example.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/24  12:19
 */
public class zkClient {

    private String connectionString = "192.168.229.141:2181,192.168.229.142:2181,192.168.229.143:2181";

    private int sessionTimeout = 2000000;

    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> children = null;
                try {
                    children = zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=========================");
                for (String child : children) {
                    System.out.println(child);
                }
                System.out.println("=========================");
            }
        });
    }

    @Test
    public void create() throws InterruptedException, KeeperException {
        String client = zkClient.create("/atgugui", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }

    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        //List<String> children = zkClient.getChildren("/", true);
        //
        //for (String child : children) {
        //    System.out.println(child);
        //}
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void isExist() throws InterruptedException, KeeperException {
        Stat exists = zkClient.exists("/atguui", false);
        System.out.println(exists);
    }
}
