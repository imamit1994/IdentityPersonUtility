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
import java.util.Scanner;

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
				
				Scanner sc=new Scanner(System.in);
				String Choice;
				do {
				System.out.println("Please Enter your choice | 1 | 2 | 3 |");
				System.out.println("1) Enter | 1 | to delete Data in identity Db for Collection identityV3 and identityActivationdetails");
				System.out.println("2) Enter | 2 | to delete data in Person Db for Collection Asset");
				System.out.println("3) Enter | 3 | to exit from utility");
					Choice = sc.nextLine();
					switch (Choice) {
					case "1":
						System.out.println("Data deletion Started in identity Db for Collection identityV3 and identityActivationdetails");
						identityMaskApplication.executeIdentityDb();
						System.out.println("Data deletion completed in identity Db for Collection identityV3 and identityActivationdetails");
						break;
					case "2":
						System.out.println("Data deletion Started in person Db for Collection Asset");
						do {
							System.out.println("Please choose your assetType for deletion in person DB");
							System.out.println("1) Enter | 1 | for SFDC assetType");
							System.out.println("2) Enter | 2 | for ESUITE assetType");
							System.out.println("3) Enter | 3 | for O2DRIVE assetType");
							System.out.println("4) Enter | 4 | to exit from Asset Deletion and return back to utility");
							Choice = sc.nextLine();
							switch (Choice) {
							case "1":
								System.out.println("Processing for SFDC");
								identityMaskApplication.excutePersonDb("SFDC");
								break;
							case "2":
								System.out.println("Processing for ESUITE");
								identityMaskApplication.excutePersonDb("ESUITE");
								break;
							case "3":
								System.out.println("Processing for O2DRIVE");
								identityMaskApplication.excutePersonDb("O2DRIVE");
								break;
							case "4":
								System.out.println("Exiting from Asset deletion");
								Choice="4";
								break;
							default:
								System.out.println("Please enter Correct Choice");
							}
						} while (Choice != "4");
						System.out.println("Data deletion completed in person Db for Collection Asset");
						break;
					case "3":
						identityMaskApplication.closeApplicationContext();
						System.out.println("Utility has been closed");
						System.exit(0);
						break;
					default:
						System.out.println("You have Entered Incorrect choice");
					}
				}while(Choice!="3");
			} catch (BeansException ex) {
				System.out.println("Context Initialization Failed : CAUSE - " + Arrays.toString(ex.getStackTrace()));
			} catch (Exception e) {
				System.out.println("Failed : " + Arrays.toString(e.getStackTrace()));
			}
		} else {
			System.out.println("Properties file not found at given location");
		}
	}

	private void excutePersonDb(String assetType) {
		DataMaskController dataMaskController = context.getBean("dataMaskController", DataMaskController.class);
		logger.info("Execution Started for Asset collection in Person Db");
		dataMaskController.executePersonDb(assetType);
	}

	public void executeIdentityDb()  {
		DataMaskController dataMaskController = context.getBean("dataMaskController", DataMaskController.class);
		logger.info("Execution Started for Identity Db");
		dataMaskController.executeIdentityDb();	
	}
	
	public void closeApplicationContext() {
		((ConfigurableApplicationContext) context).close();
	}
}
	