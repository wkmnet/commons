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

import com.google.common.base.Strings;
import xin.wukm.commons.model.AdminUser;
import xin.wukm.commons.util.Commons;
import xin.wukm.commons.util.Constants;

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

    public void login(){
        String email = getPara("email");
        String password = getPara("password");
        logger.info("email:" + email + ",password" + password);
        if(Strings.isNullOrEmpty(email) || Strings.isNullOrEmpty(password)){
            setAttr("errorMessage","用户名或密码为空");
            renderVelocity("/auth.html");
            return;
        }
        logger.info("check user:" + email);
        AdminUser user = AdminUser.ADMIN_USER.findFirst("select * from admin_user where email=?", email);
        if(user == null || user.getLong("id") == null){
            setAttr("errorMessage","用户名不存在");
            renderVelocity("/auth.html");
            return;
        }
        if(!user.getStr("password").equals(Commons.md5Hex(password))){
            setAttr("errorMessage","用户名或密码错误");
            renderVelocity("/auth.html");
            return;
        }
        String current = Commons.formatNow();
        new AdminUser().set("id",user.getLong("id")).set("login_time",current).update();
        setCookie(Constants.USER_COOKIE_KEY,
                user.getLong("id").toString().concat(Constants.COOKIE_SEPARATOR).concat(Commons.md5Hex(email.concat(current))),
                Constants.COOKIE_TIMEOUT);
        redirect("/");
    }

    public void logout(){
        removeCookie(Constants.USER_COOKIE_KEY);
        renderVelocity("/auth.html");
    }

    public void auth(){
        renderVelocity("/auth.html");
    }

}
