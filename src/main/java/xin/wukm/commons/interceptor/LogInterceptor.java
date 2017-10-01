/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : common
 * Package name : xin.wukm.common.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-7-2
 * Time : 下午5:59
 * -------------------------------
 */
package xin.wukm.commons.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.render.FileRender;
import com.jfinal.render.JsonRender;
import com.jfinal.render.Render;
import com.jfinal.render.VelocityRender;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : common
 * Package name : xin.wukm.common.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-7-2
 * Time : 下午5:59
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LogInterceptor implements Interceptor {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void intercept(Invocation inv) {
        HttpServletRequest request = inv.getController().getRequest();
        Enumeration<String> headers = request.getHeaderNames();
        JSONObject header = new JSONObject();
        while (headers.hasMoreElements()){
            String key = headers.nextElement();
            header.put(key, request.getHeader(key));
        }
        logger.info("request-header:" + header.toJSONString());
        inv.invoke();
        HttpServletResponse response = inv.getController().getResponse();
        Collection<String> hs = response.getHeaderNames();
        JSONObject h = new JSONObject();
        Iterator<String> keys = hs.iterator();
        while (keys.hasNext()){
            String key = keys.next();
            h.put(key, response.getHeader(key));
        }
        logger.info("response-header:" + h.toJSONString());
        Render render = inv.getController().getRender();
        if(render instanceof VelocityRender){
            logger.info("view : " + inv.getViewPath());
        } else if(render instanceof JsonRender) {
            JsonRender r = (JsonRender) render;
            logger.info("response : " + r.getJsonText());
        }  else if(render instanceof FileRender) {
            FileRender r = (FileRender) render;
            logger.info("response : file " + r.getClass().getName());
        }
    }
}
