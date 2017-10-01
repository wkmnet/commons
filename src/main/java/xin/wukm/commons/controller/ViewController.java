/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:06
 * -------------------------------
 */
package xin.wukm.commons.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:06
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class ViewController extends Controller {

    protected Logger logger = Logger.getLogger(this.getClass());

    public void index(){
        renderVelocity("index.html");
    }
}
