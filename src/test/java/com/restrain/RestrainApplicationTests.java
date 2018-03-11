package com.restrain;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class RestrainApplicationTests {



	public static void  main(String args[]){
		String[] ary = {"abc", "123", "45"};
		String str1= StringUtils.join(ary, ",");
		System.out.println(str1);
	}

}
