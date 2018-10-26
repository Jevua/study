package com.mail.controller;

import com.mail.intf.MailService;
import com.mail.po.MailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MailService mailService;

    @RequestMapping("/sendSimpleMail")
    public MailResult sendSimpleMail(String to, String subject, String content) {
        MailResult result = new MailResult();
        try {
            mailService.sendSimpleMail(to, subject, content);
        }
        catch (Exception e) {
            result.setRetCode("11");
            result.setRetMsg("发送失败：" + e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return result;
    }

}
