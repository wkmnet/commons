/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午3:09
 * -------------------------------
 */
package xin.wukm.commons.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import com.jfinal.plugin.activerecord.Page;
import xin.wukm.commons.model.AdminUser;
import xin.wukm.commons.util.Commons;

import java.util.Map;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午3:09
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@Before(Restful.class)
public class UserController extends ApiBaseController {

    public void index(){
        Integer page = getParaToInt("page");
        String email = getPara("email");
        logger.info("email:" + email);
        Page<AdminUser> list = AdminUser.ADMIN_USER.paginate(page,20,"select * ","from admin_user ");
        renderJson(ok(list));
    }

    public void show(){
        String id = getPara(0);
        logger.info("id:" + id);
        AdminUser user = AdminUser.ADMIN_USER.findById(id);
        renderJson(ok(user));
    }

    public void save(){
        String body = body();
        logger.info("request:" + body);
        Map m = JSONObject.parseObject(body, Map.class);
        m.put("password", Commons.md5Hex(m.get("password").toString()));
        m.put("create_time",Commons.formatNow());
        m.put("login_time",Commons.formatNow());
        new AdminUser()._setAttrs(m).save();
        renderJson(ok());
    }

    public void update(){
        String id = getPara(0);
        String body = body();
        logger.info("id:" + id);
        logger.info("request:" + body);
        Map m = JSONObject.parseObject(body, Map.class);
        if(m.get("password") != null){
            m.put("password", Commons.md5Hex(m.get("password").toString()));
        }
        new AdminUser().set("id",id)._setAttrs(m).update();
        renderJson(ok());
    }

    public void delete(){
        String id = getPara(0);
        logger.info("id:" + id);
        new AdminUser().deleteById(id);
        renderJson(ok());
    }

}
