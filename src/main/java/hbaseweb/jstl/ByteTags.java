/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbaseweb.jstl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author vahan
 */
public class ByteTags extends TagSupport {
//    public static boolean containsIgnoreCase(String input, String substring) {

    public static String byteAsUUID(byte[] bytes) {

        int aa = bytes.length;
        long msb = 0;
        long lsb = 0;
//        return Integer.toString(aa);
        if (aa == 16) {
            for (int i = 0; i < 8; i++) {
                msb = (msb << 8) | (bytes[i] & 0xff);
            }
            for (int i = 8; i < 16; i++) {
                lsb = (lsb << 8) | (bytes[i] & 0xff);
            }
            UUID uuid = new UUID(msb, lsb);
            return uuid.toString();
        }
        return "";
    }

    public static String bytesToIntStr(byte[] bytes) {

        int aa = bytes.length;
        if (aa == 4) {
            int vv = Bytes.toInt(bytes);
            String a = Integer.toString(vv);
            return a;
        }

        if (aa == 8) {
            double vv = Bytes.toDouble(bytes);
            String a = Double.toString(vv);
            if (vv == (long) vv) {
                return String.format("%d", (long) vv);
            } else {
                return String.format("%s", vv);
            }
//            return a;
        }

        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toString(v));
            sb.append(" ");
        }
        return sb.toString().toUpperCase();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
            sb.append(" ");
        }
        return sb.toString().toUpperCase();
    }

    public static String byteAsString(byte[] bytevalue) {

        return new String(bytevalue);
    }

    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        return SKIP_PAGE;
    }
}
