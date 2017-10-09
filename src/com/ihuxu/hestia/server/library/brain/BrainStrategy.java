package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.model.CommonMessageModel;

public abstract class BrainStrategy {
	public abstract void execute(CommonMessageModel cmm);
}
