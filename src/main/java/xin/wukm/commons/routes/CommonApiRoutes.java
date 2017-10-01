/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.routes
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:41
 * -------------------------------
 */
package xin.wukm.commons.routes;

import com.jfinal.config.Routes;
import xin.wukm.commons.controller.ServerController;
import xin.wukm.commons.controller.UserController;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.routes
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:41
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class CommonApiRoutes extends Routes {
    @Override
    public void config() {
        add("/api/monitor", ServerController.class);
        add("/api/user", UserController.class);
    }
}
