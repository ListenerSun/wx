package com.sqt.edu.student.utils;

import com.sqt.edu.student.dto.request.SubjectDTO;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-19 18:56
 */
public final class StudentCommonUtils {

    /**将 [{"code":"2","name":"数学"},{"code":"4","name":"物理"}] 格式 转换成:  数学,语文,英语  格式
     * @param subjectList
     * @return
     */
    public static String resolveSubjects(List<SubjectDTO> subjectList) {
        if (subjectList == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < subjectList.size(); i++) {
            if (i == subjectList.size() - 1) {
                sb.append(subjectList.get(i).getName());
            } else {
                sb.append(subjectList.get(i).getName())
                        .append(",");
            }
        }
        return sb.toString();
    }
}
