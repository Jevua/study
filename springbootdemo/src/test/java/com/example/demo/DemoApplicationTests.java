package com.example.demo;


import com.mail.intf.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	MailService mailService;

	@Autowired
	TemplateEngine templateEngine;

	@Test
	public void contextLoads() {
		//静态资源
		String resId = "resId";
		String content = "<html>\n" +
				"<body>\n" +
				"有图片<img src=\"cid:"+resId+"\">\n" +
				"</body>\n" +
				"</html>";
		mailService.sendInLineResourceMail("1178280528@qq.com", "静态资源邮件主题", content, "D:\\大纲大纲 .png", resId);
	}


	@Test
	public void sendSimpleMail() {
		//简单邮件
		mailService.sendSimpleMail("1178280528@qq.com", "这是邮件主题", "这是测试信息的内容");
	}

	@Test
	public void sendHtmlMail() {

	}

	@Test
	public void sendAttachMentsMail() {
		//附件邮件
		mailService.sendAttachMentsMail("1178280528@qq.com", "附件邮件主题", "有附件，请查收", "D:\\汉英.txt");
	}

	@Test
	public void sendInLineResourceMail() {
		//富文本邮件
		String content = "<html>\n" +
				"<body>\n" +
				"<h1>这是一封html邮件<h1>\n" +
				"</body>\n" +
				"</html>";
		mailService.sendHtmlMail("1178280528@qq.com", "富文本邮件主题", content);
	}

	@Test
	public void sendTemplateMail() {
		Context context = new Context();
		context.setVariable("id", "jevua");
		String emailContent = templateEngine.process("emailTemplate", context);
		mailService.sendHtmlMail("1178280528@qq.com", "模板邮件主题", emailContent);
	}

}
