package lougao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lou_gao
 * @description:
 * @since 2022/7/9 11:58 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
}
