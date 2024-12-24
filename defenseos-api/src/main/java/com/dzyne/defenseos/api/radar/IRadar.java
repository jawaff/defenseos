package com.dzyne.defenseos.api.radar;

import com.dzyne.defenseos.detections.radar.RadarDetection;

import java.util.function.Consumer;

public interface IRadar {
    /**
     * Registers a consumer on our radar so that our radars are able to know who to publish radar detection events to.
     * This strategy allows for our consumers to not have direct knowledge of every radar implementation and allows
     * the radars to have no knowledge of the consumers.
     * <br>
     * I do now see a way to connect Kafka or RabbitMQ to Spring so that we can use them for the producer-consumer
     * strategy. This is what I went with off the top of my head because it's simple, but obviously this only works
     * within a single process.
     *
     * @param radarDetectionConsumer A consumer function that will take in a radar detection event.
     */
    void registerRadarDetectionConsumer(final Consumer<RadarDetection> radarDetectionConsumer);
}
