package app.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Run {
	public static void main(String[] args) throws Exception {
		
		Class<?> c = Class.forName("app.test.Book");
		
		Book book = (Book) c.newInstance();
		
		book.setId(1);
		System.out.println(book.getId());
		
//		Field[] fields = c.getDeclaredFields();
		
//		for(Field f:fields){
//			System.out.println(f.getName());
//		}
//		
//		Method[] methods = c.getMethods();
//		
//		for(Method m:methods){
//			System.out.println(m.getName());
//			System.out.println(m.getReturnType());
//		}
		
		Method m = c.getMethod("getId", null);
		System.out.println(m.getName());
		Book o = (Book) c.newInstance();
		o.setId(2);
		System.out.println(m.invoke(o));
	}
}
