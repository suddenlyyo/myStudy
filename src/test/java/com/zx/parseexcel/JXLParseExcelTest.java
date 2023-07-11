package com.zx.parseexcel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: myStudy
 * @description: JXL解析Excel测试
 * @author: zhou  xun
 * @create: 2023-07-11 10:16
 */
public class JXLParseExcelTest {
    public static void main(String[] args) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(System.getProperty("user.dir") +
                File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "账单导入模板.xls"))) {
            //获取工作簿对象
            Workbook wb = Workbook.getWorkbook(inputStream);
            // 获取第一个工作表对象
            Sheet sheet = wb.getSheet(0);
            // 获取行数
            int numRows = sheet.getRows();
            // 获取列数
            int numCols = sheet.getColumns();

            List<String> title = Stream.of("收退标志", "发起支付申请的订单号", "客户姓名", "收款商户", "支付金额", "手续费", "摘要", "创建时间", "支付流水号", "完成时间")
                    .collect(Collectors.toList());
            for (int i = 0; i < numRows; i++) {
                Cell[] cells = sheet.getRow(i);
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = cells[j];
                    if(!StringUtils.isEmpty(cell.getContents())) {
                        System.out.println(cell.getContents());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
