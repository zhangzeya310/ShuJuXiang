package zzu.bs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitPage {

	public static <T> Map<String,Object> split(String listSql,String countSql,Integer currPage,Integer pageSize,Class<T> cla,Object... params) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		int count = JDBCUtils.queryForInt(countSql,params);
		Integer totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;//总页数
		map.put("totalPage", totalPage);//设置总页数
		Object[] paramArr=new Object[params.length+2];
		int index=0;
		for(Object o:params){
			paramArr[index++]=o;			
		}
		Integer start = (currPage-1)*pageSize+1;
		Integer offset = pageSize;
		paramArr[index++]=start-1;
		paramArr[index++]=offset;
		for(Object b:paramArr){
			System.out.print(b+"=");
		}System.out.println(paramArr.length+"@");
		List<T> list = JDBCUtils.queryForList(listSql, cla, paramArr);
		map.put("list", list);
		return map;
	}
}
