/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午1:52
 * -------------------------------
 */
package xin.wukm.commons.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import java.util.Calendar;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午1:52
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class FooterInterceptor implements Interceptor {

    private Logger logger = Logger.getLogger(this.getClass());

    private String pattern = "yyyy-MM-dd";

    @Override
    public void intercept(Invocation inv) {
        String date = DateFormatUtils.format(Calendar.getInstance(),pattern);
        logger.info(date);
        inv.getController().setAttr("currentDate",date);
        inv.invoke();
    }
}
