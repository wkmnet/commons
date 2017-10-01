/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:35
 * -------------------------------
 */
package xin.wukm.commons.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableBiMap;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.Restful;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import xin.wukm.commons.util.Commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

/**
 * 代码有编辑器 IntelliJ IDEA 完成
 * Project name : commons
 * Package name : xin.wukm.commons.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-10-1
 * Time : 下午12:35
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@Before(Restful.class)
public class ServerController extends ApiBaseController {

    public void index(){
        Map<String,Object> result = new HashedMap();
        linuxCpu(result);
        linuxMemory(result);
        vmMember(result);
        vmCpu(result);
        logger.info("system value : " + JSONObject.toJSONString(result));
        renderJson(result);
    }

    private void vmCpu(Map<String,Object> result){
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String command= "top -b -n 1 | grep " + pid + " | awk '{print $9}'";
        String cpu = command(command);
        if(StringUtils.isBlank(cpu)){
            //出错
        }
        if(cpu.indexOf("%") > 0){
            cpu = cpu.substring(0,cpu.indexOf("%"));
        }
        result.put("cpu", NumberUtils.toFloat(cpu));
    }

    private void vmMember(Map<String,Object> result){
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        result.put("freeMemory", freeMemory);
        result.put("totalMemory", totalMemory);
        double memory = 100 * freeMemory * 1.0 / totalMemory;
        result.put("memory", NumberUtils.toFloat(Commons.formatNumber(memory)));
    }


    private void linuxCpu(Map<String,Object> result){
        String command = "top -b -n 1 | grep Cpu |awk  '{print $2}'";
        String cpu = command(command);
        if(StringUtils.isBlank(cpu)){
            //出错
        }
        if(cpu.indexOf("%") > 0){
            cpu = cpu.substring(0,cpu.indexOf("%"));
        }
        result.put("linuxCpu",NumberUtils.toFloat(cpu));
    }

    private void linuxMemory(Map<String,Object> result){
        String totalCommand = "free | grep Mem | awk  '{print $2}'";
        long total = NumberUtils.toLong(command(totalCommand));
        String usedCommand = "free | grep Mem | awk  '{print $3}'";
        long used = NumberUtils.toLong(command(usedCommand));
        result.put("totalMemory",total);
        result.put("usedMemory",used);
        result.put("linuxMemory", NumberUtils.toFloat(Commons.formatNumber(used * 100.0 / total)));
        return;
    }

    protected void close(Process process, InputStream is,
                         InputStreamReader isr, BufferedReader brStat){
        try {
            brStat.close();
            isr.close();
            is.close();
            process.destroy();
        } catch (IOException e){
            logger.error("IOException : ", e);
        }
    }

    private String command(String command){
        Process process = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader brStat = null;
        try {
            String[] cmd = {"/bin/sh", "-c", command};
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            is = process.getInputStream();
            isr = new InputStreamReader(is);
            brStat = new BufferedReader(isr);
            String line = brStat.readLine();
            logger.info(command + ":" + line);
            logger.info("process :" + process.exitValue());
            if(!StringUtils.isBlank(line.trim())){
                return line.trim();
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException : ", e);
        } catch (IOException e) {
            logger.error("IOException : ", e);
        } finally {
            logger.info("close resources ...");
            close(process,is,isr,brStat);
        }
        return "";
    }

    public void show() {
        int type = getParaToInt();
        switch (type) {
            case 0:
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
                String date = DateFormatUtils.format(c, "yyyy/MM/dd HH:mm:ss");
                renderJson(ImmutableBiMap.of("currentTime", date));
                return;
            case 1:
                date = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
                renderJson(ImmutableBiMap.of("currentTime", date));
                return;
            default:
                date = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
                renderJson(ImmutableBiMap.of("currentTime", date));
        }
    }

}
