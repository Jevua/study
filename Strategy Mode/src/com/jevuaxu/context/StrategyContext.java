package com.jevuaxu.context;

import com.jevuaxu.intf.Strategy;

/** 
 * 
 * @author 徐泽华
 * @version 1.0 
 * @since 2018年5月9日 上午11:15:15 
 */

public class StrategyContext {
	
	Strategy strategy;
	
	public StrategyContext(Strategy strategy) {
	    this.strategy = strategy;
	}
	
    public void calc(){
        strategy.calc();
    }
	

}
