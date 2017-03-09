package yp.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import yp.dao.BaseDaoI;

public class testHibernate {
	
	@Test
	public void testInsert(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-hibernate.xml"});
		BaseDaoI userDao = (BaseDaoI) ac.getBean("tAdminDAO");
		//userDao.addUser();
		System.out.println("测试完成！");
	}
}
