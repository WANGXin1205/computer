package com.growlithe.computer.common.mail;

import com.growlithe.computer.common.CandyResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/7/29 23:33
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail() {
        String fromEmail = "growlithe1205@163.com";
        String toEmail = "growlithe1205@outlook.com";
        String title = "纪念卡蒂狗";
        String content = "卡蒂狗，我回来了";
        List<String> toEmails = new ArrayList<>();
        toEmails.add(toEmail);

        CandyResult candyResult = mailService.sendSimpleMail(toEmail,title,content);
        Assert.assertTrue(candyResult.isSuccess());

        candyResult = mailService.sendSimpleMail(fromEmail,toEmail,title,content);
        Assert.assertTrue(candyResult.isSuccess());

        candyResult = mailService.sendSimpleMail(fromEmail,toEmails,title,content);
        Assert.assertTrue(candyResult.isSuccess());
    }

    @Test
    public void sendHtmlMail() {
    }

    @Test
    public void sendAttachmentsMail() {
    }

    @Test
    public void sendInlineResourceMail() {
    }
}