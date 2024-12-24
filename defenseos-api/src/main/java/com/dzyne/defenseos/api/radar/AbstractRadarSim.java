package com.dzyne.defenseos.api.radar;

import com.dzyne.defenseos.detections.radar.Point;
import com.dzyne.defenseos.detections.radar.RadarDetection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public abstract class AbstractRadarSim implements IRadar {
    private static final Random RANDOM = new Random();

    private final List<Consumer<RadarDetection>> radarDetectionConsumers = new CopyOnWriteArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerRadarDetectionConsumer(final Consumer<RadarDetection> radarDetectionConsumer) {
        radarDetectionConsumers.add(radarDetectionConsumer);
    }

    protected final void publishRadarDetection(final RadarDetection radarDetection) {
        System.out.printf("Publishing from %s%n", radarDetection);

        // ArrayList isn't thread safe by default and synchronizing on the list prevents multiple threads
        // to iterate over our list.
        radarDetectionConsumers.forEach(consumer -> consumer.accept(radarDetection));
    }

    protected final Point randomPoint(float originLatitude, float originLongitude, float originAltitude) {
        return Point.builder()
                .latitude(RANDOM.nextFloat(originLatitude - 0.25f, originLatitude + 0.25f))
                .longitude(RANDOM.nextFloat(originLongitude - 0.25f, originLongitude + 0.25f))
                .altitude(originAltitude)
                .build();
    }
}
