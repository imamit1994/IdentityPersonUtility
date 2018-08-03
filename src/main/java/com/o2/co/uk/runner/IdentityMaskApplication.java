package com.o2.co.uk.runner;


import com.o2.co.uk.controller.DataMaskController;
import com.o2.co.uk.infra.PropertiesManager;
import com.o2.co.uk.util.ArgumentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;

public class IdentityMaskApplication {

	private static final Logger logger = LoggerFactory.getLogger(IdentityMaskApplication.class);
	private ApplicationContext context;

	private IdentityMaskApplication(String propertiesPath) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(PropertiesManager.class).addConstructorArgValue(propertiesPath).getBeanDefinition();
		beanFactory.registerBeanDefinition("propertiesManager", beanDefinition);
		GenericApplicationContext genericApplicationContext = new GenericApplicationContext(beanFactory);
		genericApplicationContext.refresh();
		context = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"}, genericApplicationContext);
	}

	public static void main(String args[]) {
		if (args.length > 0 && ArgumentValidator.isValidFilePath(args[0])) {
			try {
				IdentityMaskApplication identityMaskApplication = new IdentityMaskApplication(args[0]);
				identityMaskApplication.execute();
			} catch (BeansException ex) {
				System.out.println("Context Initialization Failed : CAUSE - " + Arrays.toString(ex.getStackTrace()));
			} catch (Exception e) {
				System.out.println("Failed : " + Arrays.toString(e.getStackTrace()));
			}
		} else {
			System.out.println("Properties file not found at given location");
		}
	}

	public void execute()  {
		DataMaskController dataMaskController = context.getBean("dataMaskController", DataMaskController.class);
		logger.info("Execution Started for Identity to update identityV3 and identityActivationDetailCollection");
		dataMaskController.execute();
		((ConfigurableApplicationContext) context).close();

	}

}
	