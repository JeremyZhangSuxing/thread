/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud.domain.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author suxing.zhang
 * @since 2019/4/28
 */
@Data
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -9202109574544652243L;
    private long total;
    private List<T> list;
    private int pageNum;
    private int pages;
    private int size;

}

