/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.table
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午2:53
 * -------------------------------
 */
package xin.wukm.commons.table;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import xin.wukm.commons.model.AdminUser;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.table
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午2:53
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Tables {

    public void register(ActiveRecordPlugin plugin){
        plugin.addMapping("admin_user", AdminUser.class);
    }
}
