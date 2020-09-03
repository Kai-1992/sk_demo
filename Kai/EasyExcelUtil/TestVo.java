package com.hikvision.portal.points.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author shaokai
 */
@Data
public class TestVo {
    @ExcelProperty(value = "Project Number", index = 0)
    private String projectNumber;
    @ExcelProperty(value = "Status", index = 1)
    private String status;
    @ExcelProperty(value = "Protal Record ID", index = 2)
    private Long id;
    @ExcelProperty(value = "Comments", index = 3)
    private String comment;
}
