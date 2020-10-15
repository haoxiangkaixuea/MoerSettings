package com.zsmarter.moresettings.util;

import com.zsmarter.moresettings.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class DisRepeatUtils {

    /**
     * 获取不重复的元素和重复的元素
     *
     * @param s1 服务器数据
     * @param s2 当前页面最初的数据
     * @return 不重复的元素和重复的元素
     */
    public static List<User> disRepeat(List<User> s1, List<User> s2) {

        //判断不能为空
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
            return null;
        }
        //复制rps1，作为备份
        List<User> rps1Bak = new ArrayList<>(s1);

        //1、获取rps1中与rps2中不同的元素
        s1.removeAll(s2);

        //2、获取rps1和rps2中相同的元素
        rps1Bak.removeAll(s1);

        //3、获取rps2中与rps1中不同的元素
        s2.removeAll(rps1Bak);

        if (s1.size() > s2.size()) {
            //rps1中独有的数据
            return s1;
        } else {
            return rps1Bak;
            //交集的数据
        }
        // mapList.put(2, s2);//rps2中的独有数据
    }
}
