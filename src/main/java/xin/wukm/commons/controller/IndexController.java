/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:09
 * -------------------------------
 */
package xin.wukm.commons.controller;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:09
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class IndexController extends ViewController {

    public void index(){
        logger.info("to home page");
        renderVelocity("index.html");
    }

}
