package jschool;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;


public class UtilServiceTest {
    @Autowired
    private UtilService utilService;

    private final String json = "{\"data\": \"value\", \"intData\" : 0}";
    private final String incorrectJson = "{\"data\": \"value\", \"intData\" : }";

    @Test
    public void testParseJsonInput(){
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        JsonNode jsonNode1Wrong = UtilService.parseJsonInput(incorrectJson);
        Assert.assertNotNull(jsonNode);
        Assert.assertNull(jsonNode1Wrong);
        Assert.assertEquals("value",jsonNode.get("data").asText());
        Assert.assertEquals(0,jsonNode.get("intData").asInt());

    }

    @Configuration
    public static class ContextConfiguration {
        @Bean
        public UtilService utilService() {
            return new UtilService();
        }

    }
}
