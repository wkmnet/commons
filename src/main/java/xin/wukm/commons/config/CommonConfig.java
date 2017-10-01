/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 上午11:44
 * -------------------------------
 */
package xin.wukm.commons.config;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.config.*;
import com.jfinal.core.ActionReporter;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.VelocityRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import xin.wukm.commons.interceptor.FooterInterceptor;
import xin.wukm.commons.interceptor.LogInterceptor;
import xin.wukm.commons.interceptor.LoginInterceptor;
import xin.wukm.commons.routes.CommonApiRoutes;
import xin.wukm.commons.routes.CommonRoutes;
import xin.wukm.commons.table.Tables;
import xin.wukm.commons.util.Commons;
import xin.wukm.commons.writer.LoggerWriter;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 上午11:44
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class CommonConfig extends JFinalConfig {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        //自定义renderFactory
        //RenderManager.me().setRenderFactory(new MyRenderFactory());
        Properties vp = new Properties();
        //关闭velocity runtime log
        vp.put(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
        //vp.put(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
        VelocityRender.setProperties(vp);
        ActionReporter.setWriter(new LoggerWriter());
        constants.setViewType(ViewType.VELOCITY);
        constants.setBaseUploadPath("/tmp/upload");
        constants.setMaxPostSize(1024 * 1024 * 1024);
        Properties properties = System.getProperties();
        JSONObject property = new JSONObject();
        Enumeration names = properties.propertyNames();
        while (names.hasMoreElements()){
            Object key = names.nextElement();
            property.put(key.toString(),properties.getProperty(key.toString()));
        }
        logger.info("system.property : " + Commons.formatJson(property));

        Map map = System.getenv();

        JSONObject env = new JSONObject();
        env.putAll(map);
        if(env.get("OPENSHIFT_DATA_DIR") != null){
            constants.setBaseUploadPath(env.get("OPENSHIFT_DATA_DIR").toString().concat("upload"));
        }
        logger.info("system.env : " + Commons.formatJson(env));
    }

    @Override
    public void configRoute(Routes me) {
        FooterInterceptor footerInterceptor = new FooterInterceptor();
        CommonRoutes commonRoutes = new CommonRoutes();
        commonRoutes.addInterceptor(footerInterceptor);
        me.add(commonRoutes);
        CommonApiRoutes commonApiRoutes = new CommonApiRoutes();
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        commonApiRoutes.addInterceptor(loginInterceptor);
        me.add(commonApiRoutes);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        initDatabase(me);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LogInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }

    private void initDatabase(Plugins me){
        String host = "172.30.24.57";
        String port = "3306";
        String database = "commons";
        String userName = "commons";
        String password = "commons";
        StringBuilder url = new StringBuilder("jdbc:mysql://");
        url.append(host).append(":").append(port).append("/").append(database).append("?useUnicode=true&characterEncoding=UTF-8");
        logger.info("init database:" + url.toString());
        DruidPlugin druidPlugin = new DruidPlugin(url.toString(),userName,password);
        me.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        new Tables().register(activeRecordPlugin);
        me.add(activeRecordPlugin);
    }
}
