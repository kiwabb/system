package com.jackmouse.basicsystem.utils;

import com.jackmouse.basicsystem.vo.SysUserVO;
import com.jackmouse.common.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName LoginUserUtils
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/25 10:29
 * @Version 1.0
 **/
public class LoginUserUtils {
    public static Long getLoginUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest contextRequest = requestAttributes.getRequest();
        String userId = contextRequest.getHeader(SecurityConstants.USER_ID_HEADER);
        return Long.parseLong(userId);
    }
}
