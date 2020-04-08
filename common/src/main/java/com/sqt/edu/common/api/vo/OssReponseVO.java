package com.sqt.edu.common.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-08 16:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OssReponseVO implements Serializable {

    private static final long serialVersionUID = -8561157416400099287L;
    /**
     * 完整路径
     */
    private String allPath;
    /**
     * 文件相对路径
     */
    private String filePath;
    /**
     * 文件大小 单位: kb
     */
    private Long fileSize;

}
