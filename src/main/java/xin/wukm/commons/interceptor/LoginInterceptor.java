/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午3:23
 * -------------------------------
 */
package xin.wukm.commons.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableBiMap;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import xin.wukm.commons.model.AdminUser;
import xin.wukm.commons.util.Commons;
import xin.wukm.commons.util.Constants;

import java.util.Date;
import java.util.Enumeration;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午3:23
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LoginInterceptor implements Interceptor {
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        Enumeration<String> names =  controller.getAttrNames();
        JSONObject element = new JSONObject();
        while ( names.hasMoreElements() ){
            String key = names.nextElement();
            element.put(key,controller.getAttr(key));
        }
        logger.info("request : " + Commons.formatJson(element));
        String value = controller.getCookie(Constants.USER_COOKIE_KEY);
        logger.info("find-cookie:" + value);
        if(Strings.isNullOrEmpty(value)){
            reject(controller);
            return;
        }
        String[] cs = StringUtils.split(value,Constants.COOKIE_SEPARATOR);
        if(cs.length != 2){
            reject(controller);
            return;
        }
        logger.info("cs0:" + cs[0] + "cs1" + cs[1]);
        AdminUser user = AdminUser.ADMIN_USER.findById(cs[0]);
        if(user == null || user.getLong("id") == null){
            logger.info("user-not-exist:" + cs[0]);
            reject(controller);
            return;
        }
        String loginTime = formatDate(user.getDate("login_time"));
        if(Commons.md5Hex(user.getStr("email").concat(loginTime)).equals(cs[1])){
            invocation.invoke();
        } else {
            logger.info("check-cookie-fail:cookie" + cs[1] +";check-" + Commons.md5Hex(user.getStr("email")));
            reject(controller);
        }
    }

    private String formatDate(Date date){
        return DateFormatUtils.format(date,Constants.DATE_PATTERN);
    }

    private void reject(Controller controller){
        controller.getResponse().setStatus(HttpStatus.UNAUTHORIZED_401);
        controller.renderJson(ImmutableBiMap.of("success",false,"code","AUTH_FAIL","message","鉴权异常"));
    }
}
