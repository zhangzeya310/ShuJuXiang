package zzu.bs.utils;

import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ServiceProxy {

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(T target) {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				(proxy, method, args) -> {
					Connection conn = JDBCUtils.getConnection();
					conn.setAutoCommit(false);
					Object result = null;
					try {
						result = method.invoke(target, args);
						conn.commit();
					} catch (Exception e) {
						e.printStackTrace();
						conn.rollback();
					}finally{
						JDBCUtils.free(null, null, conn);
					}
					return result;
				});

	}
}
