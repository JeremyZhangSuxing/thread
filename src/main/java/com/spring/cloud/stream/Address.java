package com.spring.cloud.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang.suxing
 * @date 2020/6/14 21:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Country country;
}
