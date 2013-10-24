package com.callistech.policyserver.common.pyc;

import java.util.Queue;

import org.apache.log4j.Logger;

public abstract class PYCConsumerImplementation implements Runnable {

	protected Logger logger = Logger.getLogger(getClass());
	private Queue<PYCEvent> queue;
	private Boolean continueConsuming = Boolean.TRUE;

	public PYCConsumerImplementation(Queue<PYCEvent> queue) {
		this.queue = queue;
	}

	public void stop() {
		logger.info("Stopping Consumer...");
		continueConsuming = false;
	}

	@Override
	public void run() {
		try {
			PYCEvent event;
			while (continueConsuming) {
				event = queue.poll();
				if (event != null) {
					logger.debug("Event received: " + event.toStringBeauty());
					processEvent(event);
				} else {
					Thread.sleep(10);
				}
			}
			logger.info("Consumer stopped.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected abstract void processEvent(PYCEvent event);
}
