package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem getItemById(long itemId) {
//		根据id查询
//		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		//设置查询条件
		TbItemExample example=new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			TbItem item = list.get(0);
			
			return item;
		}
		return null;
	}
	@Override
	public DataGridResult getDataGridResult(int page, int rows) {
		//设置分页条件
		PageHelper.startPage(page, rows);
		TbItemExample example=new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> info=new PageInfo<>(list);
		long total = info.getTotal();
		DataGridResult result=new DataGridResult();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

}
