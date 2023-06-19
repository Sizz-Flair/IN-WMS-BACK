package com.wms.inwms.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * packageName    : com.wms.inwms.util
 * fileName       : SecurityUserUtil
 * author         : akfur
 * date           : 2023-06-15
 */
public class SecurityUserUtil {
    public static UserDetails getUserDetailInfo() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
