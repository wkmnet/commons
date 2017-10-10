/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午7:27
 * ---------------------------------
 */
package xin.wukm.commons.interceptor;

import com.google.common.collect.ImmutableBiMap;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.apache.log4j.Logger;

/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午7:27
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class ExceptionInterceptor implements Interceptor {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void intercept(Invocation inv) {
        try {
            inv.invoke();
        } catch (Exception e){
            logger.error("system-exception:" + e.getMessage());
            logger.error("system-exception:" + e);
            inv.getController().renderJson(ImmutableBiMap.of("success",false,"code","500","message",e));
        }
    }
}
