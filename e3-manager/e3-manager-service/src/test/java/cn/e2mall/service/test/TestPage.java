package cn.e2mall.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class TestPage {
	
	@Test
	public void testPage(){
		//读取配置信息
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//获取代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//设置分页
		PageHelper.startPage(1,10);
		//设置查询条件
		TbItemExample example=new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//取结果
		PageInfo<TbItem> page=new PageInfo<>(list);
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		System.out.println(list.size());
	
	}
}
