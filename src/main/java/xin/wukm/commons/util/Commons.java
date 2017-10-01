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
import org.apache.commons.lang.SystemUtils;

import java.text.DecimalFormat;

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
}
