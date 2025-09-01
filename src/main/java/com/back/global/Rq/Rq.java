package com.back.global.Rq;

import com.back.domain.member.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;

    @Getter
    private int count;

    @Getter
    @Setter
    private String name;

    public int increaseCount() {
        return count++;
    }

    public String getCurrentUrl() {
        String url = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            return url + "?" + queryString;
        }

        return url;
    }

    public String getLoginedMemberName() {
        return (String) session.getAttribute("loginedMemberName");
    }

    public void setLoginDone(Member member) {
        session.setAttribute("loginedMemerId", member.getId());
        session.setAttribute("loginedMemberUsername", member.getUsername());
        session.setAttribute("loginedMemberName", member.getName());
        session.setAttribute("loginedMemberEmail", member.getEmail());
    }

    public void setLogoutDone() {
        session.removeAttribute("loginedMemerId");
        session.removeAttribute("loginedMemberUsername");
        session.removeAttribute("loginedMemberName");
        session.removeAttribute("loginedMemberEmail");
    }
}
