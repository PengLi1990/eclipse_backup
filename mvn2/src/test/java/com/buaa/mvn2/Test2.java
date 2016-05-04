package com.buaa.mvn2;

import org.junit.Test;

import junit.framework.Assert;

public class Test2 {
	@Test
	public void TestMvn2(){
		Hello h = new Hello();
		String ret = h.say("who");
		Assert.assertEquals("hello who", ret);
	}

}
