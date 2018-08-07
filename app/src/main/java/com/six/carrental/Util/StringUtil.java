package com.six.carrental.Util;

import java.util.HashMap;

/**
 * @author Lian
 * @create 2018/7/24
 * @Describe 字符串工具类
 */
public class StringUtil {

    public static String getFreeTime(String time) {
        return time.substring ( 5, time.length () );
    }

    public static HashMap <String, Integer> getDetail(String time) {
        HashMap <String, Integer> timeMap = new HashMap <> ();
        int startHour = Integer.parseInt ( time.substring ( 11, 13 ) );
        int endHour = Integer.parseInt ( time.substring ( 17, 19 ) );
        int startMin = Integer.parseInt ( time.substring ( 14, 16 ) );
        int endMin = Integer.parseInt ( time.substring ( 20 ) );
        timeMap.put ( "startHour", startHour );
        timeMap.put ( "endHour", endHour );
        timeMap.put ( "startMin", startMin );
        timeMap.put ( "endMin", endMin );
        return timeMap;
    }


}
