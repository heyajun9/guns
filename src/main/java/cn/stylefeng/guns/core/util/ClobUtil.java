package cn.stylefeng.guns.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * @author Admin
 * @create 2019-09-03 15:40
 * @desc clob转换成String
 **/
public class ClobUtil {
    public static String ClobToString(Clob sc) throws SQLException, IOException {
        String reString = "";
        // 得到流
        Reader is = sc.getCharacterStream();
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
        while (s != null) {
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();

        return reString;
    }

}
