/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : common
 * Package name : xin.wukm.common.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-8-26
 * Time : 下午10:10
 * -------------------------------
 */
package xin.wukm.commons.util;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : common
 * Package name : xin.wukm.common.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-8-26
 * Time : 下午10:10
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class HttpUtil {

    private static Logger log = Logger.getLogger(HttpUtil.class);

    private HttpUtil(){

    }

    public static String get(HttpUrl url){
        Request request = new Request.Builder().get().url(url).build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                return response.body().string();
            }
        } catch (IOException e) {
            log.error("IOException:" + e.getMessage());
        }
        return null;
    }
}
