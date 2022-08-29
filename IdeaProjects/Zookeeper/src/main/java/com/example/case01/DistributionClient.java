package com.example.case01;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/24  13:25
 */
public class DistributionClient {

    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException {
        DistributionClient client = new DistributionClient();
        //1.获取连接
        client.getConnect();

        //2.监听节点是否增加
        client.getServerList();

        //3.业务逻辑
        client.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getServerList() {
        try {
            List<String> children = zooKeeper.getChildren("/servers", true);

            List<String> serverLists = new ArrayList<>();
            for (String child : children) {
                byte[] data = zooKeeper.getData("/servers/" + child, false, null);
                serverLists.add(new String(data));
            }
            System.out.println(serverLists);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(DistributionServer.connectionString, DistributionServer.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                getServerList();
            }
        });
    }
}
