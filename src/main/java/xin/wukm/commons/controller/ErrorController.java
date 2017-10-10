/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午7:15
 * ---------------------------------
 */
package xin.wukm.commons.controller;

import com.google.common.collect.ImmutableBiMap;

/**
 * Create with IntelliJ IDEA
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-10
 * Time : 下午7:15
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class ErrorController extends ViewController {
    public void index(){
        logger.error("error");
        renderJson(ImmutableBiMap.of("success",false,"code","500","message","服务器错误"));
    }
}
