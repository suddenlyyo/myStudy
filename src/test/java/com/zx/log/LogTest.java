package com.zx.log;


import org.junit.jupiter.api.*;

import java.util.Objects;
import java.util.logging.*;

/**
 * @program: myStudy
 * @description: 日志测试
 * @author: zhou  xun
 * @create: 2022-09-21 10:24
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  //设置排序模式
public class LogTest {
    private static final Logger logger = Logger.getLogger(LogTest.class.getName());

    @BeforeEach
    public void setLevelTest() {
        //只有Level.FINE和它之上的级别被设置为日志记录
        //如果一个日志记录器的级别被设置为null，那么它的级别将从其父继承，以此类推
        logger.setLevel(Level.FINE);
        //已通过设置的日志级别的所有日志请求都将转发到LogRecord。
    }

    @BeforeEach
    public void setFilterTest() {
        //Filter filter= logger.getFilter();
        logger.setFilter(record -> true);
    }

//    @BeforeEach
//    public void getLogManagerTest() {
//        LogManager manager = new LogManager();
//    }

    @BeforeEach
    public void setFormatterTest() {
        ConsoleHandler consoleHandler = new ConsoleHandler();

        consoleHandler.setLevel(Objects.isNull(logger.getLevel()) ? Level.ALL : logger.getLevel());
//
//        consoleHandler.setFormatter(
//                new Formatter() {
//                    @Override
//                    public String format(LogRecord record) {
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
//                        //日期 {类名} [方法名] 日志内容+参数 换行符
//                        StringBuilder s = new StringBuilder(100);
//
//                        //日期
//                        s.append(dateFormat.format(new Date()));
//                        //类名
//                        s.append("{");
//                        try {
//                            s.append(Class.forName(record.getSourceClassName()).getSimpleName());
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        s.append("}");
//                        //方法名
//                        s.append(" [");
//                        s.append(record.getSourceMethodName());
//                        //日志内容
//                        s.append(",");
//                        s.append(record.getMessage());
//
//                        Object[] params = record.getParameters();
//                        if (params != null) {
//                            s.append("-");
//                            //参数
//                            s.append(Arrays.toString(record.getParameters()));
//                        }
//                        s.append("] ");
//                        s.append("\n");
//
//                        return s.toString();
//
//                    }
//                }
//        );
        logger.addHandler(consoleHandler);
    }

    @Test
    @Order(1)   //设置排序值
    public void log() {
        //记录一条日志消息
        logger.log(Level.INFO, "这是INFO日志级别的消息");
        logger.warning("这是WARNING日志级别的消息");

    }
    @Test
    @Order(2)   //设置排序值
    public void testA(){
        System.out.println("执行方法A");
    }

}
