package p12;

import com.practice.springboot.p12.SampleWebJspApplication;
import com.practice.springboot.p12.common.properties.EmailSettings;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes=SampleWebJspApplication.class)
public class MailSenderTests {
    @Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private EmailSettings emailSettings;
	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailSettings.getFormEmail());
		message.setTo(emailSettings.getToEmail());
		message.setSubject("台词：");
		message.setText("我想起夕阳下的奔跑。。。"+ DateUtil.newIsoDateFormat().format(new Date()));
		mailSender.send(message);
	}
	@Test
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(emailSettings.getFormEmail());
		helper.setTo(emailSettings.getToEmail());
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body>好像和你一起去兜风。。。</br><img  src=\"cid:doufeng\" ></body></html>", true);
		ClassPathResource fileResource=new ClassPathResource("image/兜风.gif");
		FileSystemResource file = new FileSystemResource(fileResource.getFile());
		helper.addInline("doufeng", file);
		mailSender.send(mimeMessage);
	}
	@Test
	public void sendMimeTemplateMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(emailSettings.getFormEmail());
		helper.setTo(emailSettings.getToEmail());
		helper.setSubject("台词：");
		Map<String, Object> model = new HashedMap();
		model.put("username", "陛下");

		ClassPathResource fileResource=new ClassPathResource("image/兜风.gif");
		FileSystemResource file = new FileSystemResource(fileResource.getFile());
		helper.addAttachment("兜风.gif",file);
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "template.vm", "UTF-8", model);
		helper.setText(text, true);


		mailSender.send(mimeMessage);
	}
}