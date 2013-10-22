package com.callistech.policyserver.common.pyc;

import java.util.Queue;
import org.apache.log4j.Logger;

public class PYCProducerImplementation implements Runnable {

	private PYCProducerConsumerImplementation producerConsumer;
	private Queue<PYCEvent> queue;
	private Logger logger = null;
	private Boolean running = Boolean.TRUE;
	private final int INTERVAL_CALCULING_RATE = 1000000000 * 1;// seconds
	private final int INTERVAL_RECALCULING_CONSUMERS = 10;// seconds
	private final int QUEUE_SIZE = 10000;
	private final int QUEUE_THRESHOLD = 8000;

	public PYCProducerImplementation(PYCProducerConsumerImplementation producerConsumer, Queue<PYCEvent> queue, String loggerRate) {
		this.producerConsumer = producerConsumer;
		this.queue = queue;
		logger = Logger.getLogger(loggerRate);
	}

	public void produce(PYCEvent event) {
		try {
			if (running && queue.size() < QUEUE_SIZE) {
				this.queue.offer(event);
				++count;
			}
		} catch (Exception e) {
			logger.error("ERROR produce. RDR: " + event, e);
		}
	}

	@Override
	public void run() {
		try {
			while (running) {
				countRate();
				Thread.sleep(10);
			}
		} catch (Exception ex) {
			logger.error("ERROR PYCProducerImplementation en run.", ex);
		}
	}

	private long startPeriod = 0;
	private int count = 0;
	private int countPeak = 0;
	private long lastSize = 0;
	private int lastRate = 0;
	private int count2 = 0;

	private void countRate() {
		if (startPeriod == 0) {
			startPeriod = System.nanoTime();
		}
		long diferenciaPeriod = System.nanoTime() - startPeriod;
		if (diferenciaPeriod > INTERVAL_CALCULING_RATE) {
			if (countPeak < count) {
				countPeak = count;
			}
			lastSize = queue.size();
			lastRate = count;
			logger.debug("Rate: " + count + ". Peak: " + countPeak + ". Queue size: " + lastSize);
			count = 0;
			startPeriod = System.nanoTime();
			count2++;

			if (lastSize > QUEUE_THRESHOLD) {
				logger.warn("PYCProducer queue is filling up. Size: " + lastSize + ". Queue MAX size: " + QUEUE_SIZE);
			}
		}
		if (count2 >= (INTERVAL_RECALCULING_CONSUMERS)) {
			recalculateConsumers();
			count2 = 0;
		}
	}

	private void recalculateConsumers() {
		if (lastSize < 100) {
			producerConsumer.deleteConsumer();
		} else {
			if (lastSize > lastRate) {
				producerConsumer.addConsumer();
			}
		}
	}

	public void stop() {
		running = false;
	}
}
