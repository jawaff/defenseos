package com.dzyne.defenseos.sim.radar.xyz;

import com.dzyne.defenseos.api.radar.AbstractRadarSim;
import com.dzyne.defenseos.detections.radar.Point;
import com.dzyne.defenseos.detections.radar.RadarDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public final class RadarXyz extends AbstractRadarSim {
    private static final String SOURCE = "RadarXyz";
    private static final Point ORIGIN = new Point(15f, -30f, 1000f);

    public RadarXyz(@Autowired final ScheduledExecutorService sharedScheduledExecutor) {
        sharedScheduledExecutor.scheduleAtFixedRate(
                this::publishRadarDetection,
                0,
                2,
                TimeUnit.SECONDS
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RadarDetection produceNewRadarDetection() {
        return RadarDetection.builder()
                .detectionId(UUID.randomUUID())
                .source(SOURCE)
                .point(randomPointShift(ORIGIN))
                .build();
    }
}
