package com.example.case01;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * author ye
 * createDate 2022/5/24  13:12
 */
public class DistributionServer {

    public static String connectionString = "192.168.229.141:2181,192.168.229.142:2181,192.168.229.143:2181";

    public static int sessionTimeout = 2000000;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException {
        DistributionServer distributionServer = new DistributionServer();

        //1.获取zk连接
        distributionServer.getConnect();

        //2.注册服务器到集群
        distributionServer.register(args[0]);

        //3.启动逻辑业务
        distributionServer.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void register(String hostName) {
        try {
            zooKeeper.create("/servers/" + hostName, hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(hostName + " is online");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
