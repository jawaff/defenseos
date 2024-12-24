package com.dzyne.defenseos.api.radar;

import com.dzyne.defenseos.detections.radar.Point;
import com.dzyne.defenseos.detections.radar.RadarDetection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public abstract class AbstractRadarSim implements IRadar {
    private static final Random RANDOM = new Random();
    private static final float SHIFT_DISTANCE = 0.25f;

    private final List<Consumer<RadarDetection>> radarDetectionConsumers = new CopyOnWriteArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerRadarDetectionConsumer(final Consumer<RadarDetection> radarDetectionConsumer) {
        radarDetectionConsumers.add(radarDetectionConsumer);
    }

    /**
     * @return Produces some radar specific {@link RadarDetection} object that will be published to the consumers.
     */
    protected abstract RadarDetection produceNewRadarDetection();

    /**
     * A helper function that takes in some origin point and creates a random point that is slightly modified
     * in a random way.
     * @param origin The origin point.
     * @return The randomly shifted point from the origin.
     */
    protected final Point randomPointShift(Point origin) {
        return Point.builder()
                // Per the requirements, the latitude and longitude should randomly shift from the origin by 0.25
                // degrees.
                .latitude(
                        RANDOM.nextFloat(
                                origin.latitude() - SHIFT_DISTANCE,
                                origin.latitude() + SHIFT_DISTANCE
                        )
                )
                .longitude(
                        RANDOM.nextFloat(
                                origin.longitude() - SHIFT_DISTANCE,
                                origin.longitude() + SHIFT_DISTANCE
                        )
                )
                // Per the requirements, the altitude can remain constant.
                .altitude(origin.altitude())
                .build();
    }

    /**
     * Publishes a new, partially randomly generated {@link RadarDetection} event to the registered consumers.
     */
    protected final void publishRadarDetection() {
        final var radarDetection = this.produceNewRadarDetection();
        System.out.printf("Publishing from %s%n", radarDetection);

        // ArrayList isn't thread safe by default and synchronizing on the list prevents multiple threads
        // from iterating over our list. CopyOnWriteArrayList is a bit faster in comparison and uses snapshots of the
        // list for iteration.
        radarDetectionConsumers.forEach(consumer -> consumer.accept(radarDetection));
    }
}
