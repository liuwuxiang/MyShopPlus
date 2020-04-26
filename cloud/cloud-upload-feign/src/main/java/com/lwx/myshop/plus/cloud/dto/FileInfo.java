package com.lwx.myshop.plus.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 功能描述: 文件信息上传路劲参数传输对象
 *
 * @author: 刘武祥
 * @Date: 2020/4/21 0021 11:09
 * @version v1.0.0
 * @see com.lwx.myshop.plus.cloud.dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {
    /**
     * 文件路径
     */
    private String path;
}
