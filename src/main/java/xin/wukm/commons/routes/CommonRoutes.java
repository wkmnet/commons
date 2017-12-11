/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.routes
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 上午11:58
 * -------------------------------
 */
package xin.wukm.commons.routes;


import com.jfinal.config.Routes;
import xin.wukm.commons.controller.ErrorController;
import xin.wukm.commons.controller.IndexController;
import xin.wukm.commons.controller.LogUploadController;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.routes
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 上午11:58
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class CommonRoutes extends Routes {
    @Override
    public void config() {
        add("/", IndexController.class);
        add("/system/error", ErrorController.class);
        add("/android/log", LogUploadController.class);
    }
}
