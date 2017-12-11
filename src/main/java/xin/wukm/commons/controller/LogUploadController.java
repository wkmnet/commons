/**
 * Create with IntelliJ IDEA
 * Project name : console
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-12-8
 * Time : 下午3:21
 * ---------------------------------
 */
package xin.wukm.commons.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import com.jfinal.upload.UploadFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : console
 * Package name : xin.wukm.common.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-12-8
 * Time : 下午3:21
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LogUploadController extends ViewController {

    private String dir = "/home/appuser/upload";

    public void upload(){
        logger.info("start");
        UploadFile file = getFile();
        logger.info("upload");
        String name = getPara("name");
        logger.info("name:" + name);
        Map<String,Object> r = new HashMap<>();
        r.put("success",true);
        Map<String, String[]> map = getParaMap();
        logger.info(JSONObject.toJSONString(map));
        logger.info(JSONObject.toJSONString(getRequest().getParameterMap()));
        logger.info("file:" + file.getFile().getName());
        logger.info("file:" + file.getFile().getAbsolutePath());
        File f = new File(dir);
        if(!f.exists()) {
            f.mkdir();
        }
        File tmp = file.getFile();
        try {
            FileUtils.copyFile(tmp, new File(f, tmp.getName()));
            renderJson(r);
        } catch (IOException e){
            logger.error(e.getMessage());
            r.put("success", false);
            r.put("message", e.getMessage());
        }
        renderJson(r);
    }


    public void list(){
        File f = new File(dir);
        if(!f.exists()) {
            setAttr("list", Lists.newArrayList());
            renderVelocity("/files.html");
            return;
        }
        File[] files = f.listFiles();
        if(files == null || files.length == 0){
            setAttr("list", Lists.newArrayList());
            renderVelocity("/files.html");
            return;
        }
        String host = getHeader("host");
        String[] hosts = host.split(":");
        StringBuilder url = new StringBuilder();
        if(hosts[1].equals("443")){
            url.append("https://");
        } else {
            url.append("http://");
        }
        url.append(host);
        url.append("/android/log/detail?file=");
        List<String> fs = Lists.newArrayList();
        for(File i : files){
            fs.add(url.toString().concat(i.getName()));
        }
        setAttr("list", fs);
        renderVelocity("/files.html");
        return;
    }

    public void detail(){
        String name = getPara("file");
        File f = new File(dir);
        if(!f.exists()) {
            renderText("not-found");
            return;
        }
        File file = new File(f, name);
        if(!file.exists()){
            renderText("not-found");
            return;
        }

        renderFile(file);
    }
}
