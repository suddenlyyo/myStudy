package com.zx.java8;


import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.zone.ZoneRules;
import java.util.Date;
import java.util.Locale;

/**
 * @program: myStudy
 * @description: Java 8 Date API 测试
 * @author: zhou  xun
 * @create: 2023-06-03 10:15
 */
public class DateAPITest {

    //region    Java8 Date API的新特性包括：
//    LocalDate、LocalTime和LocalDateTime类，用于处理日期和时间，支持时区和日历系统。
//    Instant类，表示一个瞬时点，可以用于计算时间间隔。
//    Duration和Period类，分别用于计算时间间隔和周期。
//    DateTimeFormatter类，用于格式化和解析日期和时间字符串。
//    新的时区支持，如ZoneId和ZoneOffset类。
//
//    应用示例：
//
//    使用LocalDate类来表示日期，例如：LocalDate.now()获取当前日期。
//    使用LocalDateTime类来表示日期和时间，例如：LocalDateTime.of(2021, 7, 12, 10, 30)表示2021年7月12日10点30分。
//    使用Instant类来获取当前的瞬时点，例如：Instant.now()。
//    使用Duration类来计算两个时间点之间的差值，例如：Duration.between(start, end)。
//    使用DateTimeFormatter类来格式化和解析日期和时间字符串，例如：DateTimeFormatter.ISO_DATE_TIME.format(dateTime)。
//    使用ZoneId和ZoneOffset类来表示时区信息，例如：ZoneId.of("Asia/Shanghai")表示亚洲上海时区。
//endregion
    //region LocalDate测试
    @Test
    public void LocalDateTest() {
        LocalDate now = LocalDate.now();
        System.out.println("创建一个表示当前日期的LocalDate对象->" + now);
        LocalDate dateOfBirth = LocalDate.of(1995, 5, 5);
        System.out.println("创建一个表示指定日期的LocalDate对象->" + dateOfBirth);
        System.out.println("获取LocalDate," + now + "对象的年、月、日:");
        System.out.println("获取LocalDate," + now + "对象的年份->" + now.getYear());
        System.out.println("获取LocalDate," + now + "对象的月份大写的英文名->" + now.getMonth());
        System.out.println("获取LocalDate," + now + "对象的月份数值->" + now.getMonthValue());
        System.out.println("获取LocalDate," + now + "对象的日期数值->" + now.getDayOfMonth());
        //增加或减少LocalDate对象的天数
        LocalDate newDate = now.plusDays(7);
        LocalDate previousDate = now.minusDays(7);
        //比较两个LocalDate对象的大小
        boolean isAfter = now.isAfter(dateOfBirth);
        boolean isBefore = now.isBefore(dateOfBirth);
        boolean isEqual = now.isEqual(dateOfBirth);
        //解析字符串为LocalDate对象
        String dateString = "2022-01-01";
        LocalDate newYear = LocalDate.parse(dateString);
    }

    @Test
    public void getBeginTimeTest() {
        String date = "2022-04-01";
        String beginTime = getBeginTime(date);
        System.out.println(beginTime);
    }

    @Test
    public void getEndTimeTest() {
        String date = "2022-04-01";
        String beginTime = getEndTime(date);
        System.out.println(beginTime);
    }

    private String getBeginTime(String date) {
        LocalDate parse = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 获取当天的开始时间和结束时间
        LocalDateTime startTime = parse.atStartOfDay();
        return startTime.format(formatter);
    }

    private String getEndTime(String date) {
        LocalDate parse = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 获取当天的开始时间和结束时间
        LocalDateTime endTime = parse.atTime(LocalTime.MAX);
        return endTime.format(formatter);
    }

    //endregion
    //region LocalTime测试
    @Test
    public void LocalTimeTest() {
        // 创建当前时间的LocalTime对象
        LocalTime now = LocalTime.now();
        //通过指定小时、分钟和秒来创建LocalTime对象
        LocalTime time = LocalTime.of(10, 30, 0);
        //获取LocalTime对象的小时数、分钟数和秒数
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        //将LocalTime对象转换为字符串表示形式
        String timeStr = time.toString();
        // 比较两个LocalTime对象的先后顺序
        LocalTime time1 = LocalTime.of(10, 0, 0);
        LocalTime time2 = LocalTime.of(11, 0, 0);
        //比较两个LocalDate对象的大小
        boolean isAfter = time1.isAfter(time2);
        boolean isBefore = time1.isBefore(time2);
        boolean isEqual = time1.equals(time2);
    }

    //endregion
    //region LocalDateTime测试
    @Test
    public void localDateTimeTest() {
        //获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        //创建指定的日期时间
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 0, 0);
        //格式化输出日期时间
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(formattedDateTime);
        //转换成时间戳
        //因为Java中的时间戳（Unix时间戳）是从1970年1月1日00:00:00 UTC开始计算的，而中国使用的是东八区时间（UTC+8），
        //所以需要将LocalDateTime转换为UTC+8时区的时间戳，即加上8小时的秒数。
        long timestamp = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        //增加或减少时间
        LocalDateTime newDateTime = dateTime.plusDays(1);
        LocalDateTime newDateTime2 = dateTime.minusHours(2);
        //比较两个日期时间的大小
        LocalDateTime otherDateTime = LocalDateTime.of(2022, 1, 2, 0, 0);
        boolean isBefore = dateTime.isBefore(otherDateTime);
        boolean isAfter = dateTime.isAfter(otherDateTime);
    }

    //endregion
    //region  Clock测试
    // Java中的Clock类提供了一种简单的方式来获取当前时间，并允许您对时间进行操作。以下是该类的用法：
    //    导入java.time.Clock包。
    //    创建一个Clock对象，可以使用系统时钟或指定的时区。
    //    使用Clock对象的方法来获取当前时间或日期，例如：instant()、millis()和getZone()等。
    //    您还可以使用withZone()方法来更改时区。
    @Test
    public void clockTest() {
        // 创建默认系统时钟
        Clock clock = Clock.systemDefaultZone();
        // 获取当前时间戳
        Instant instant = clock.instant();
        // 获取当前毫秒数
        long millis = clock.millis();
        // 获取时区
        ZoneId zone = clock.getZone();
        // 创建指定时区的时钟
        Clock clock2 = Clock.system(ZoneId.of("Asia/Shanghai"));
        // 更改时区
        Clock newClock = clock.withZone(ZoneId.of("America/New_York"));
    }

    //endregion
    //region Duration测试
    @Test
    public void durationTest() {
        // 创建Duration对象：
        // 创建一个持续时间为60秒的Duration对象
        Duration duration = Duration.ofSeconds(60);
        //计算两个时间之间的差值：
        LocalDateTime startDateTime = LocalDateTime.of(2021, 8, 1, 12, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2021, 8, 1, 12, 1);
        // 计算从startDateTime到endDateTime的持续时间
        Duration between = Duration.between(startDateTime, endDateTime);
        // 获取持续时间的各个部分：
        long seconds = duration.getSeconds(); // 获取持续时间的秒数
        long minutes = duration.toMinutes(); // 获取持续时间的分钟数
        long hours = duration.toHours(); // 获取持续时间的小时数
        long days = duration.toDays(); // 获取持续时间的天数
        // 计算Instant（表示时间线上的某个时刻）之间的差值
        Instant start = Instant.now();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
    }

    //endregion
    //region ZoneId测试
    @Test
    public void zoneIdTest() {
        //创建一个代表北京时间的ZoneId实例
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        // 使用系统默认时区或 UTC 时区来创建 ZoneId 对象：
        ZoneId systemDefaultZone = ZoneId.systemDefault();
        ZoneId utcZone = ZoneId.of("UTC");
        //将日期和时间转换为特定时区的日期和时间
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        //返回此 Locale 的语言环境的可读名称。
        String displayName = ZoneId.of("Asia/Shanghai").getDisplayName(TextStyle.FULL, Locale.US);
        //返回系统默认时区的规则，返回此 RuleBasedCollator 的排序规则，以字符串形式表示。
        ZoneRules rules = ZoneId.systemDefault().getRules();
        //返回字符串的规范化形式，使用 Unicode 标准的 NFC 规范。
        ZoneId normalized = ZoneId.of("Asia/Shanghai").normalized();

    }

    //endregion
    //region ZoneOffset测试
    //ZoneOffset是Java 8中的一个类，用于表示一个时区相对于UTC（协调世界时）的时间偏移量。它可以通过静态工厂方法ofHours和ofTotalSeconds创建，
    // 并支持将其与Java日期时间API一起使用，例如LocalDateTime、ZonedDateTime等。ZoneOffset还提供了许多有用的方法，
    // 例如getTotalSeconds（获取此时区偏移量的秒数）、getId（获取此时区偏移量的字符串表示形式）等。
    @Test
    public void zoneOffsetTest() {
        // 创建一个固定偏移量为 +8 小时的 ZoneOffset 实例
        ZoneOffset offset = ZoneOffset.of("+08:00");
        // 另外，也可以通过静态方法 ZoneOffset.UTC 获取表示零偏移量的实例。
        // 使用 ZoneOffset 可以将本地时间转换为 UTC 时间或者相反，例如：
        // 将本地时间转换为 UTC 时间
        OffsetDateTime localTime = OffsetDateTime.now();
        OffsetDateTime utcTime = localTime.withOffsetSameInstant(ZoneOffset.UTC);
        // 将 UTC 时间转换为本地时间
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime offsetDateTime = utcTime.withOffsetSameInstant(ZoneOffset.ofHours(8));
    }
    //endregion

    /**
     * 日期时间格式化
     *
     * @param dateTimeStr 需要格式化的年月日时分秒字符串
     * @param pattern     匹配的格式
     * @return
     * @author: zhou  xun
     * @since: 2023-04-25
     */
    public static Date dateTimeStrToDateTime(String dateTimeStr, String pattern) {
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 日期格式化
     *
     * @param dateStr 需要格式化的年月日时分秒字符串
     * @param pattern 匹配的格式
     * @return
     * @author: zhou  xun
     * @since: 2023-09-15
     */
    public static Date dateStrToDateTime(String dateStr, String pattern) {
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDate.parse(dateStr, formatter).atStartOfDay();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
}
