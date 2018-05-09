package com.jevuaxu.client;

import com.jevuaxu.context.StrategyContext;
import com.jevuaxu.intf.ConcreteStrategyA;
import com.jevuaxu.intf.ConcreteStrategyB;

/** 
 * 
 * @author 徐泽华
 * @version 1.0 
 * @since 2018年5月9日 上午11:17:12 
 */

public class StrategyClient {
	
	public static void main(String[] args) {
		StrategyContext strategyCcontext = new StrategyContext(new ConcreteStrategyA());
		strategyCcontext.calc();
		
		strategyCcontext = new StrategyContext(new ConcreteStrategyB());
		strategyCcontext.calc();
		
    }
}
