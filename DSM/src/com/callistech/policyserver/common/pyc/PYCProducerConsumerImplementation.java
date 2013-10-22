package com.callistech.policyserver.common.pyc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

public abstract class PYCProducerConsumerImplementation {

	private Logger logger = Logger.getLogger(getClass());
	private ExecutorService threadPool;
	private LinkedList<PYCConsumerImplementation> listConsumers = new LinkedList<PYCConsumerImplementation>();
	private int MAX_CONSUMER_COUNT = 10;
	private int INIT_CONSUMER_COUNT = 2;
	private int MIN_CONSUMER_COUNT = 1;
	private int consumerCount = 0;
	private Queue<PYCEvent> queue = new ConcurrentLinkedQueue<PYCEvent>();
	private PYCProducerImplementation producer;

	public PYCProducerConsumerImplementation(String loggerRate) {
		try {
			threadPool = Executors.newCachedThreadPool();
			producer = new PYCProducerImplementation(this, queue, loggerRate);
		} catch (Exception ex) {
			logger.error("ERROR en ManagerRDRProducerConsumer.", ex);
		}
	}

	public void start() {
		try {
			threadPool.execute(producer);
			for (int i = 0; i < INIT_CONSUMER_COUNT; i++) {
				addConsumer();
			}
		} catch (Exception ex) {
			logger.error("ERROR en init.", ex);
		}
	}

	public void addConsumer() {
		try {
			if (consumerCount < MAX_CONSUMER_COUNT) {
				PYCConsumerImplementation consumer = generateConsumer();
				threadPool.execute(consumer);
				listConsumers.add(consumer);
				consumerCount++;
				logger.debug("Adding Consumer. Consumer count: " + consumerCount);
			}
		} catch (Exception ex) {
			logger.error("ERROR en addConsumer.", ex);
		}
	}

	protected abstract PYCConsumerImplementation generateConsumer();

	public void addEvent(PYCEvent event) {
		producer.produce(event);
	}

	public void deleteConsumer() {
		try {
			if (consumerCount > MIN_CONSUMER_COUNT) {
				PYCConsumerImplementation consumer = listConsumers.poll();
				consumer.stop();
				consumerCount--;
				logger.debug("Deleting Consumer. Consumer count: " + consumerCount);
			}
		} catch (Exception ex) {
			logger.error("ERROR en deleteConsumer.", ex);
		}
	}

	private void deleteAllConsumers() {
		PYCConsumerImplementation consumer;
		while (!listConsumers.isEmpty()) {
			consumer = listConsumers.poll();
			consumer.stop();
			consumerCount--;
			logger.debug("Deleting Consumer. Consumer count: " + consumerCount);
		}
	}

	public Queue<PYCEvent> getQueue() {
		return queue;
	}

	public PYCProducerImplementation getProducer() {
		return producer;
	}

	public void stop() {
		try {
			logger.debug("Stopping ProducerConsumer...");
			producer.stop();
			deleteAllConsumers();
			logger.debug("Stopped ProducerConsumer.");
		} catch (Exception ex) {
			logger.error("ERROR en stop.", ex);
		}
	}
}
