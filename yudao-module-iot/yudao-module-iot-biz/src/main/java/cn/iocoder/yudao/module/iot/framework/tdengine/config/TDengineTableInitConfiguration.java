package cn.iocoder.yudao.module.iot.framework.tdengine.config;

import cn.iocoder.yudao.module.iot.service.device.IotDeviceLogDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * TDengine 表初始化的 Configuration
 *
 * @author alwayssuper
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@Order
public class TDengineTableInitConfiguration implements ApplicationRunner {

    private final IotDeviceLogDataService deviceLogService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            // 初始化设备日志表
            deviceLogService.defineDeviceLog();
            // TODO @super：这个日志，是不是不用打，不然重复啦！！！
        } catch (Exception ex) {
            // TODO @super：初始化失败，打印 error 日志，退出系统。。不然跑起来，就初始啦！！！
            // 初始化失败时打印错误日志并退出系统
            log.error("[TDengine] 初始化设备日志表结构失败，系统无法正常运行，即将退出", ex);
            System.exit(1);
        }
    }

}
