package blog.csdn.net.mchenys.service.impl;

import java.util.List;

import blog.csdn.net.mchenys.dao.CategoryDao;
import blog.csdn.net.mchenys.dao.impl.CategoryDaoImpl;
import blog.csdn.net.mchenys.domain.Category;
import blog.csdn.net.mchenys.service.CategoryService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> getCategorys() throws Exception {
		//创建缓存管理对象
		CacheManager cm = CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache.xml"));
		//根据配置文件中的cache的name获取对应的cache对象
		Cache  cache  =cm.getCache("categoryCache");
		
		//先从Cache中获取数据
		Element element = cache.get("clist");
		List<Category> list = null;
		if(element==null) {
			//表示首次访问,还没有缓存,那么需要查询数据库
			CategoryDao dao = new CategoryDaoImpl();
			list= dao.findAll();
			//获取成功后,再保存到缓存中,以备下次直接从缓存中拿数据
			cache.put(new Element("clist",list));
			System.out.println("从数据库中获取数据");
		}else {
			//从缓存中获取,注意Category必须要实现Serializable接口
			list= (List<Category>) cache.get("clist").getObjectValue();
			System.out.println("从缓存中获取数据");
		}
		
		//返回数据
		return list;
		
	}

}
