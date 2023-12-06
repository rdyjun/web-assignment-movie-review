package com.dongyang.moviewreviewweb.moviereviewer.admin;

import com.dongyang.moviewreviewweb.moviereviewer.log.Log;
import com.dongyang.moviewreviewweb.moviereviewer.log.LogDAO;
import com.dongyang.moviewreviewweb.moviereviewer.member.entity.MemberFace;
import com.dongyang.moviewreviewweb.moviereviewer.member.service.MemberService;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Report;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.ReportFace;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReportServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class adminController {
    private final MemberService memberService;
    private final LogDAO logDAO;
    private final ReportServiceImpl reportService;
    @RequestMapping("/admin")
    public String admin (Model model, HttpSession httpSession) {
        if (!httpSession.getAttribute("userId").equals("admin"))
            return "redirect:/";
        List<MemberFace> blackList = memberService.getBlackListMemberList();
        List<MemberFace> whiteList = memberService.getNoneBlackListMemberList();
        List<Log> logList = logDAO.getAllLog();
        List<Report> reportList = reportService.getAllReport();
        List<ReportFace> reportFaceList = reportService.getAllReportComment(reportList);
        Collections.reverse(logList);
        model.addAttribute("blackList", blackList);
        model.addAttribute("whiteList", whiteList);
        model.addAttribute("logList", logList);
        model.addAttribute("report", reportList);
        model.addAttribute("reportFace", reportFaceList);
        Log log = new Log("어드민 페이지 접속", (String) httpSession.getAttribute("userId"));
        logDAO.create(log);
        return "/admin";
    }
    @PostMapping("/blockMember")
    public String blockMember (String memberId) {
        memberService.reverseMemberStatus(memberId);
        Log log = new Log("회원 블락", memberId);
        logDAO.create(log);
        return "redirect:/admin";
    }
    @PostMapping("/unblockMember")
    public String unblockMember (String memberId) {
        memberService.reverseMemberStatus(memberId);
        Log log = new Log("회원 언블락", memberId);
        logDAO.create(log);
        return "redirect:/admin";
    }
}
