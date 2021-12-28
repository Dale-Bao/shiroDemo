package org.dale.shiroDemo.mapper;

import org.dale.shiroDemo.model.OmpUser;

/**
 * @author Administrator on 2021/12/24.
 * Description:
 */
public interface OmpUserMapper {
    OmpUser getUserByName(String username);
}
