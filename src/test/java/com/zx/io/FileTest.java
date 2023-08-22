package com.zx.io;

import com.github.jsonzou.jmockdata.JMockData;
import com.zx.model.User;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @program: myStudy
 * @description: 文件读取
 * @author: zhou  xun
 * @create: 2023-05-28 15:10
 */
public class FileTest {
    //    Java IO 类型分为字节流和字符流。
//    字节流：
//    InputStream 和 OutputStream：用于读写字节数据，是所有字节输入输出流的父类。
//    FileInputStream 和 FileOutputStream：用于读写文件中的字节数据。
//    BufferedInputStream 和 BufferedOutputStream：与 FileInputStream 和 FileOutputStream 搭配使用，加快读写速度。
//    DataInputStream 和 DataOutputStream：用于读写 Java 基本数据类型并以二进制形式存储。
//    ObjectInputStream 和 ObjectOutputStream：用于读写 Java 对象并以二进制形式存储。
//    字符流：
//    Reader 和 Writer：用于读写字符数据，是所有字符输入输出流的父类。
//    FileReader 和 FileWriter：用于读写文件中的字符数据。
//    BufferedReader 和 BufferedWriter：与 FileReader 和 FileWriter 搭配使用，加快读写速度。
//    InputStreamReader 和 OutputStreamWriter：将字节流转换为字符流，可以指定字符编码方式。
    private final String path = System.getProperty("user.dir") +
            File.separator + "src" + File.separator + "test" + File.separator + "resources"
            + File.separator;

    /**
     * BufferedReader从字符输入流中读取文本，缓冲字符，以便高效读取字符、数组和行。
     *
     * @author: zhou  xun
     * @since: 2023-05-28
     */
    @Test
    public void bufferedReaderReadFile() {
        File file = new File(path + "myFile.txt");
        //try-with-resources 是一种 Java 编程语言结构，可以在代码块结束时自动关闭打开的资源（如文件、数据库连接等）
        //try(BufferedReader br = new BufferedReader(new FileReader("filename.txt"))) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(file.toPath())))) {
            System.out.println("文件路径:" + file.toPath());
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * BufferedWriter可以在字符流中高效写入文本，缓冲字符
     *
     * @author: zhou  xun
     * @since: 2023-05-28
     */
    @Test
    public void bufferedWriterWriterFile() {
        File file = new File(path + "myFile.txt");
//try-with-resources 是一种 Java 编程语言结构，可以在代码块结束时自动关闭打开的资源（如文件、数据库连接等）
        //这里的true表示追加而不是覆盖
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            System.out.println("文件路径:" + file.toPath());
            bw.write("想着你说过 要一起看夜景..");
            //换行
            bw.newLine();
//在Java中，java.io.BufferedWriter#flush()方法用于刷新缓冲区并将任何未写入的数据强制写入底层流。如果在关闭流之前使用此方法，则可以确保所有数据都已写入文件或套接字，并且不会丢失数据。
//如果您没有手动调用 flush() 方法，则在缓冲区填满或流关闭时将自动调用该方法。因此，如果您的应用程序需要立即将数据写入文件或套接字，则应使用 flush() 方法来强制写入缓冲区。
// 简而言之，flush() 方法是为了确保数据写入底层流而提供的一种手段，以避免数据丢失。
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//Java 缓冲字符是指使用缓冲区来读取或写入字符数据，以提高I/O性能的技术。一般使用 BufferedReader 和 BufferedWriter 类来实现缓冲字符操作，它们可以分别用于读取和写入字符数据。
// 通过缓冲字符操作，可以有效减少磁盘或网络I/O的次数，从而提高程序的运行效率。

//    Java对象输入输出流可以通过实现Serializable接口来进行序列化和反序列化操作。
//    在序列化时，使用ObjectOutputStream将Java对象转换为字节序列并输出到文件或网络中；
//    在反序列化时，则使用ObjectInputStream从输入流中读取字节序列并转换为Java对象。需要注意的是，被序列化的类必须实现Serializable接口，并且所有包含的属性也必须是可序列化的。

    /**
     * 序列化测试
     *
     * @author: zhou  xun
     * @since: 2023-05-28
     */
    @Test
    public void serializationTest() {
        //mock User对象
        User user = JMockData.mock(User.class);
        //使用对象输出流将该对象序列化到文件“User.txt”中
        try (FileOutputStream fileOut = new FileOutputStream(path + "User.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化测试
     *
     * @author: zhou  xun
     * @since: 2023-05-28
     */
    @Test
    public void deserializationTest() {
        User user = null;
        try (FileInputStream fileIn = new FileInputStream(path + "User.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            user = (User) in.readObject();
            System.out.println(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 DataOutputStream 写入二进制数据
     *
     * @author: zhou  xun
     * @since: 2023-05-28
     */
    @Test
    public void dataOutputStreamTest() {
        //try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"))) {
        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(path + "data.bin")))) {
            dos.writeInt(42);
            dos.writeDouble(3.14);
            dos.writeUTF("Hello, World!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 DataInputStream 读取二进制数据
     *
     * @author: zhou  xun
     * @since: 2023-05-28
     */
    @Test
    public void dataInputStreamTest() {
        //try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(path + "data.bin")))) {
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            String stringValue = dis.readUTF();
            System.out.println(intValue); // 42
            System.out.println(doubleValue); // 3.14
            System.out.println(stringValue); // Hello, World!
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件名解析
     *
     * @author: zhou  xun
     * @since: 2023-07-17
     */
    @Test
    public void fileNameParseTest() {
        String filePath = "private/8181384fa7ba11e990fe7cd30b8a9fe/pay/hisBi1/wechat_service/1510052711/1507138501_1510052711_All_20230301_20230331.csv";
        String fullFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length() - 4);
        // String[] fileSplit = fileName.split("_");
        //  Arrays.stream(fileSplit).forEach(System.out::println);
        String fileSuffix = fullFileName.substring(fullFileName.length() - 3);
        System.out.println("文件后缀" + fileSuffix);
        System.out.println("文件名" + fileName);
    }
}

