package com.cookie.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static com.cookie.demo.Member.KEY;

@Slf4j
@Controller
public class CookieController {

    private final AtomicSequenceGenerator sequenceGenerator;
    private final MemberRepository memberRepository;

    public CookieController(MemberRepository memberRepository) {
        this.sequenceGenerator = new AtomicSequenceGenerator();
        this.memberRepository = memberRepository;
    }

    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {

        // Get cookie from http request
        var cookies = request.getCookies();

        // has member cookie ?
        var hasCookie = cookies != null && Arrays.stream(cookies)
                .anyMatch(cookie -> cookie.getName().equals(KEY));
        model.addAttribute("hasCookie", hasCookie);

        if (hasCookie) {
            log.info("HAS COOKIE !");
            hasCookieLogic(model, cookies);
        } else {    // means No member cookie
            log.info("NO COOKIE !");
            noCookieLogic(response);
        }

        return "index";
    }


    @GetMapping("/home/other")
    public String getHomePageUseCookieParam(HttpServletResponse response,
                                            Model model,
                                            @CookieValue(value = KEY, required = true) Cookie memberCookie) {
        // must have cookie
        // If don't have cookie? => Exception
        return "none";
    }

    private void noCookieLogic(HttpServletResponse response) {
        var permanentExampleCookie = new Cookie("Permanent-Cookie-key", "Permanent-Cookie-value");
        permanentExampleCookie.setMaxAge(3600); // seconds = cookie expired time
        permanentExampleCookie.setPath("/");
        response.addCookie(permanentExampleCookie);

        var sessionExampleCookie = new Cookie("Session-Cookie-key", "Session-Cookie-value");
        sessionExampleCookie.setPath("/");
        sessionExampleCookie.setHttpOnly(true); // protect XSS attack
        response.addCookie(sessionExampleCookie);

        // example Member
        var memberId = sequenceGenerator.getNext();
        var newMember = Member.create(memberId, "MEMBER " + memberId);
        memberRepository.save(newMember);
        var memberIdentityCookie = new Cookie(KEY, newMember.getMemberId().toString());
        memberIdentityCookie.setMaxAge(3600);
        memberIdentityCookie.setPath("/");
        memberIdentityCookie.setHttpOnly(true);
        response.addCookie(memberIdentityCookie);
    }

    private void hasCookieLogic(Model model, Cookie[] cookies) {
        Arrays.stream(cookies)
                .forEach(cookie -> {
                            log.info("cookie : {}-{}\n", cookie.getName(), cookie.getValue());
                            if (isMemberInfoCookie(cookie)) {
                                // extract member from cookie
                                var memberId = Long.valueOf(cookie.getValue());
                                var member = memberRepository.findById(memberId);
                                model.addAttribute(cookie.getName(), member);
                            } else {
                                model.addAttribute(cookie.getName(), cookie.getValue());
                            }
                        }
                );
    }

    public boolean isMemberInfoCookie(Cookie cookie) {
        return !cookie.getName().equals(KEY);
    }

}
