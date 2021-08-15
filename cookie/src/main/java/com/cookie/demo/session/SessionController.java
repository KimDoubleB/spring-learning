package com.cookie.demo.session;

import com.cookie.demo.AtomicSequenceGenerator;
import com.cookie.demo.Member;
import com.cookie.demo.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.cookie.demo.Member.KEY;

@Slf4j
@Controller
public class SessionController {

    private final AtomicSequenceGenerator sequenceGenerator;
    private final MemberRepository memberRepository;

    public SessionController(MemberRepository memberRepository) {
        this.sequenceGenerator = new AtomicSequenceGenerator();
        this.memberRepository = memberRepository;
    }

    @GetMapping("/set-session")
    public String setSession(@RequestHeader Map<String, Object> requestHeader,
                             Model model,
                             HttpSession session) {

        log.info("HEADER : {}", requestHeader);
        model.addAttribute("header", requestHeader);


        session.setAttribute(KEY, Member.create(sequenceGenerator.getNext(), "name"));
        session.setMaxInactiveInterval(60 * 10);

        return "none";
    }

    @GetMapping("/get-session")
    public String getSession(@RequestHeader Map<String, Object> requestHeader,
                             Model model,
                             HttpSession session) {

        log.info("HEADER : {}", requestHeader);
        model.addAttribute("header", requestHeader);

        var sessionInfo = session.getAttribute(KEY);
        model.addAttribute("session", sessionInfo);
        return "none";
    }
}
