package cn.iocoder.yudao.module.iot.service.ota;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.iot.controller.admin.ota.vo.upgrade.record.IotOtaUpgradeRecordPageReqVO;
import cn.iocoder.yudao.module.iot.dal.dataobject.ota.IotOtaUpgradeRecordDO;
import cn.iocoder.yudao.module.iot.service.ota.bo.IotOtaUpgradeRecordCreateReqBO;
import cn.iocoder.yudao.module.iot.service.ota.bo.IotOtaUpgradeRecordUpdateReqBO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * IotOtaUpgradeRecordService 接口定义了与物联网设备OTA升级记录相关的操作。
 * 该接口提供了创建、更新、查询、统计和重试升级记录的功能。
 */
public interface IotOtaUpgradeRecordService {

    // TODO @createOtaUpgradeRecordBatch 哈，需要补充方法里，缺少 Ota 关键字的

    /**
     * 批量创建物联网OTA升级记录
     * <p>
     * 该函数用于处理一组物联网OTA升级记录的创建请求，并将这些记录批量保存到系统中。
     *
     * @param createList 包含多个物联网OTA升级记录创建请求的列表，每个请求对象都经过校验（@Valid注解确保）
     *                 列表中的每个元素都是IotOtaUpgradeRecordCreateReqBO类型的对象，表示一个独立的升级记录创建请求。
     */
    void createUpgradeRecordBatch(@Valid List<IotOtaUpgradeRecordCreateReqBO> createList);

    // TODO @li：尽量避免写比较大的通用 update。而是根据场景提供，这样才能收敛
    /**
     * 更新现有的 OTA 升级记录
     *
     * @param updateReqBO 包含更新升级记录所需信息的请求对象，必须经过验证。
     */
    void updateUpgradeRecord(@Valid IotOtaUpgradeRecordUpdateReqBO updateReqBO);

    /**
     * 获取OTA升级记录的数量统计。
     *
     * @return 返回一个 Map，其中键为状态码，值为对应状态的升级记录数量
     */
    Map<Integer, Long> getOtaUpgradeRecordCount(@Valid IotOtaUpgradeRecordPageReqVO pageReqVO);

    /**
     * 获取 OTA 升级记录的统计信息。
     *
     * @return 返回一个 Map，其中键为状态码，值为对应状态的升级记录统计信息
     */
    Map<Integer, Long> getOtaUpgradeRecordStatistics(Long firmwareId);

    /**
     * 重试指定的OTA升级记录。
     *
     * @param id 需要重试的升级记录的ID。
     */
    void retryUpgradeRecord(Long id);

    /**
     * 获取指定ID的OTA升级记录的详细信息。
     *
     * @param id 需要查询的升级记录的ID。
     * @return 返回包含升级记录详细信息的响应对象。
     */
    IotOtaUpgradeRecordDO getUpgradeRecord(Long id);

    /**
     * 分页查询OTA升级记录。
     *
     * @param pageReqVO 包含分页查询条件的请求对象，必须经过验证。
     * @return 返回包含分页查询结果的响应对象。
     */
    PageResult<IotOtaUpgradeRecordDO> getUpgradeRecordPage(@Valid IotOtaUpgradeRecordPageReqVO pageReqVO);

    /**
     * 根据任务ID取消升级记录。
     * <p>
     * 该函数用于根据给定的任务ID，取消与该任务相关的升级记录。通常用于在任务执行失败或用户手动取消时，
     * 清理或标记相关的升级记录为取消状态。
     *
     * @param taskId 要取消升级记录的任务ID。该ID唯一标识一个任务，通常由任务管理系统生成。
     */
    void cancelUpgradeRecordByTaskId(Long taskId);

    /**
     * 根据升级状态获取升级记录列表
     *
     * @param state 升级状态，用于筛选符合条件的升级记录
     * @return 返回符合指定状态的升级记录列表，列表中的元素为 {@link IotOtaUpgradeRecordDO} 对象
     */
    List<IotOtaUpgradeRecordDO> getUpgradeRecordListByState(Integer state);

    /**
     * 更新升级记录的状态。
     * <p>
     * 该函数用于批量更新指定升级记录的状态。通过传入的ID列表和状态值，将对应的升级记录的状态更新为指定的值。
     *
     * @param ids    需要更新状态的升级记录的ID列表。列表中的每个元素代表一个升级记录的ID。
     * @param status 要更新的状态值。该值应为有效的状态标识符，通常为整数类型。
     */
    void updateUpgradeRecordStatus(List<Long> ids, Integer status);

    /**
     * 根据任务ID获取升级记录列表
     * <p>
     * 该函数通过给定的任务ID，查询并返回与该任务相关的所有升级记录。
     *
     * @param taskId 任务ID，用于指定需要查询的任务
     * @return 返回一个包含升级记录的列表，列表中的每个元素为IotOtaUpgradeRecordDO对象
     */
    List<IotOtaUpgradeRecordDO> getUpgradeRecordListByTaskId(Long taskId);

}
