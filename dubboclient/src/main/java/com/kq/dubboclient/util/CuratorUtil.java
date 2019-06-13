package com.kq.dubboclient.util;

import com.kq.Constants;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

import java.util.Collections;
import java.util.List;

/**
 * CuratorUtil
 *
 * @author kq
 * @date 2019-06-12
 */
public class CuratorUtil {

    private static RetryPolicy retry = new RetryNTimes(5, 5000);

    private static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(Constants.ZOOKEEPER_SERVER)
            .connectionTimeoutMs(5000)   //连接超时时间
            .sessionTimeoutMs(3000)      //会话超时时间
            .retryPolicy(retry)
            .build();


    static {
        //建立连接
        client.start();
    }


    public static List<String> getChildPaths(String parentPath) {

        List<String> paths = null;
        try{
            paths = client.getChildren().forPath(parentPath);

        } catch (Exception e){
            e.printStackTrace();
            paths = Collections.EMPTY_LIST;
        }
        return paths;

    }


}
