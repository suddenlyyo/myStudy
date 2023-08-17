package com.zx.calendar;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @program: myStudy
 * @description: Calendar测试
 * @author: zhou  xun
 * @create: 2023-08-17 09:41
 */
public class CalendarTest {
    @Test
    public void get() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        System.out.println("当前时间: " + cal.getTime());

        int year = cal.get(Calendar.YEAR);  // 年份
        int month = cal.get(Calendar.MONTH) + 1;  // 月份(月份0代表1月)
        int date = cal.get(Calendar.DATE);  // 日期，年月日中的日，同DAY_OF_MONTH
        int week_of_year = cal.get(Calendar.WEEK_OF_YEAR);  // 第几个星期/年
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);  // 第几个星期/月
        int day_of_year = cal.get(Calendar.DAY_OF_YEAR);  // 第几天/年
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);  // 第几天/月
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);  // 第几天/星期
        int hour = cal.get(Calendar.HOUR);  // 小时，12小时制
        int hour_of_day = cal.get(Calendar.HOUR_OF_DAY);  // 小时，24小时制
        int minute = cal.get(Calendar.MINUTE);  // 分钟
        int second = cal.get(Calendar.SECOND);  // 秒
        int millisecond = cal.get(Calendar.MILLISECOND);  // 毫秒

        System.out.println("年份，YEAR: " + year);
        System.out.println("月份，MONTH: " + month);
        System.out.println("日期，DATE: " + date);
        System.out.println("第几个星期/年，WEEK_OF_YEAR: " + week_of_year);
        System.out.println("第几个星期/月，WEEK_OF_MONTH: " + week_of_month);
        System.out.println("第几天/年，DAY_OF_YEAR: " + day_of_year);
        System.out.println("第几天/月，DAY_OF_MONTH: " + day_of_month);
        System.out.println("第几天/星期，DAY_OF_WEEK: " + day_of_week);
        System.out.println("小时，12小时制，HOUR: " + hour);
        System.out.println("小时，24小时制，HOUR_OF_DAY: " + hour_of_day);
        System.out.println("分钟，MINUTE: " + minute);
        System.out.println("秒，SECOND: " + second);
        System.out.println("毫秒，MILLISECOND: " + millisecond);

    }
    @Test
    public void editCalendar() {
        //set
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        System.out.println("修改前的年份，YEAR: " + cal.get(Calendar.YEAR));
        cal.set(Calendar.YEAR, 2022);
        System.out.println("修改后的年份，YEAR: " + cal.get(Calendar.YEAR));

        //add
        cal.set(2002, Calendar.JANUARY, 12);//代表2002年1月12日
        cal.add(Calendar.MONTH, -1);//这样就将日期设置成了2001年12月12日。
        System.out.println(cal.getTime());

        //roll
        cal.set(2002, Calendar.JANUARY, 12);//代表2002年1月12日
        cal.roll(Calendar.MONTH, -1);//这样就将日期设置成了2002年12月12日。
        System.out.println(cal.getTime());

        //roll()方法处理，只会对相应的字段进行处理，不会智能的对其它字段也进行逻辑上的改变。
        //add()方法会在逻辑上改变其它字段，使结果正确。
    }
}
