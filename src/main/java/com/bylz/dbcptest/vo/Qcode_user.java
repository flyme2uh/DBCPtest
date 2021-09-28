package com.bylz.dbcptest.vo;

/**
 * @ClassName: Qcode_user
 * @Description:
 * @Author chenzhipeng
 * @Date 2021/9/18
 * @Version 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Qcode_user {
    private Long id;
    private String name;
    private String pwd;
}
