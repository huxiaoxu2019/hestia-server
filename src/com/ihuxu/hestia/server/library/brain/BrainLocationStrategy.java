package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.model.CommonMessageModel;

public class BrainLocationStrategy extends BrainStrategy {
	public void execute(CommonMessageModel cmm) {
		System.out.println("[BrainLocationStrategy]execute -> dispose the cmd:" + cmm.getCmd().toString());
	}
}
