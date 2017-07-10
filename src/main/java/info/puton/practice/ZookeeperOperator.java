package info.puton.practice;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by taoyang on 2017/7/10.
 */
public class ZookeeperOperator {

    public static final String HOST = "master.hadoop:2181";

    public static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        try {
            ZooKeeper zk = new ZooKeeper(HOST, TIMEOUT, null);

            if (zk.exists("/myjavapath",false)==null){

                zk.create("/myjavapath", "myjavadata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            zk.setData("/myjavapath","newdata2".getBytes(),-1);

            System.out.println(new String(zk.getData("/myjavapath",false,null)));

            zk.delete("/myjavapath", -1);

            zk.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
