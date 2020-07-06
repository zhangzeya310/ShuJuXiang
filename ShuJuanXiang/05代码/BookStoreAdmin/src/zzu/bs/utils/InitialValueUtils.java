package zzu.bs.utils;

import java.util.ArrayList;
import java.util.List;

public class InitialValueUtils {

	public static List<String> getInitialPublishs(){
		List<String> list = new ArrayList<String>();
		list.add("清华大学出版社");
		list.add("电子工业出版社");
		list.add("机械工业出版社");
		list.add("人民邮电出版社");
		list.add("北京大学出版社");
		list.add("其他");
		return list;
	}
	
	public static List<String> getInitialPrices(){
		List<String> list = new ArrayList<String>();
		list.add("0-20");
		list.add("20-30");
		list.add("30-50");
		list.add("50-60");
		list.add("50-70");
		list.add("70-1000");
		return list;
	}

	public static List<String> getInitialDiscounts() {
		List<String> list = new ArrayList<String>();
		list.add("0-2");
		list.add("2-4");
		list.add("4-6");
		list.add("6-8");
		list.add("8-10");
		return list;
	}
}
