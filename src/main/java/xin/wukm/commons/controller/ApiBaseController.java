/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:28
 * -------------------------------
 */
package xin.wukm.commons.controller;

import com.google.common.collect.Maps;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:28
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ApiBaseController extends Controller {

    protected Logger logger = Logger.getLogger(this.getClass());

    public void index(){
        renderJson(ok());
    }

    public Map<String,Object> ok(Object data){
        Map<String,Object> result = Maps.newHashMap();
        result.put("success", true);
        result.put("code", "success");
        result.put("message", "成功");
        if(data == null){
            return result;
        } else {
            result.put("data",data);
            return result;
        }
    }

    public Map<String,Object> ok(){
        return ok(null);
    }

    public Map<String,Object> fail(String code, String message, Object data){
        Map<String,Object> result = Maps.newHashMap();
        result.put("success", false);
        result.put("code", code);
        result.put("message", message);
        if(data == null){
            return result;
        } else {
            result.put("data",data);
            return result;
        }
    }

    public Map<String,Object> fail(String code, String message){
        return fail(code,message,null);
    }

    protected String body(){
        String body = HttpKit.readData(getRequest());
        if(StringUtils.isBlank(body)){
            logger.info("request body : " + body);
        }
        return body;
    }
}
