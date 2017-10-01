/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:21
 * -------------------------------
 */
package xin.wukm.commons.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:21
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Commons {

    private Commons(){}

    public static String formatJson(Object object){
        StringBuilder builder = new StringBuilder(SystemUtils.LINE_SEPARATOR);
        builder.append(JSONObject.toJSONString(object, SerializerFeature.PrettyFormat));
        return builder.toString();
    }

    public static String formatNumber(Number number){
        DecimalFormat format = new DecimalFormat("#.0");
        return format.format(number);
    }

    public static String md5Hex(String source){
        return DigestUtils.md5Hex(source);
    }

    public static String formatNow(){
        return DateFormatUtils.format(Calendar.getInstance(),Constants.DATE_PATTERN);
    }
}
