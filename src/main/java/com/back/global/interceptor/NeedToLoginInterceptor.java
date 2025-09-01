package com.back.global.interceptor;

import com.back.global.Rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class NeedToLoginInterceptor  implements HandlerInterceptor {
    private final Rq rq;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("NeedToLoginInterceptor::preHandle 실행됨");

        rq.increaseCount();
        log.debug("""
                rq: %s, rq.count: %s
                """.formatted(rq, rq.getCount()));

        HttpSession session = request.getSession();
        Integer loginedMemerId =  (Integer) session.getAttribute("loginedMemerId");

        // TODO: 임시처리
        boolean isLogined = loginedMemerId != null;

        // 비회원 접근 경우
        if (isLogined == false) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().append("로그인 후 이용바랍니다.");

            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
