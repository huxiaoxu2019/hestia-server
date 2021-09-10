package com.ihuxu.hestia.server.library.brain;

import com.ihuxu.hestia.server.model.CommonMessageModel;

public abstract class BrainStrategy {
	/**
	 * @deprecated Use {@link #execute(String,CommonMessageModel)} instead
	 */
	public abstract void execute(CommonMessageModel cmm);

	public abstract void execute(String clientId, CommonMessageModel cmm);
}
