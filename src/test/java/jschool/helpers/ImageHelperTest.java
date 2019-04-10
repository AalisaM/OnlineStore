package jschool.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import jschool.service.impl.UtilService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ImageHelperTest {

    private final String productFileName = "";
    private final MultipartFile multipartFile = new MockMultipartFile("file", "orig", null, "bar".getBytes());

    @Test
    public void testUploadFileHandler(){
        ImageHelper.uploadFileHandler(multipartFile,productFileName);
    }

    @Configuration
    public static class ContextConfiguration {
    }
}
