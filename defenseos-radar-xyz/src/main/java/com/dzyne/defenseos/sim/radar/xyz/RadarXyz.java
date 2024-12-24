package com.dzyne.defenseos.sim.radar.xyz;

import com.dzyne.defenseos.api.radar.AbstractRadarSim;
import com.dzyne.defenseos.detections.radar.RadarDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public final class RadarXyz extends AbstractRadarSim {
    private static final String SOURCE = "RadarXyz";
    private static final float ORIGIN_LATITUDE = -50f;
    private static final float ORIGIN_LONGITUDE = 75f;
    private static final float ORIGIN_ALTITUDE = 1500f;

    public RadarXyz(@Autowired final ScheduledExecutorService sharedScheduledExecutor) {
        sharedScheduledExecutor.scheduleAtFixedRate(
                this::publishNewRadarDetectionEvent,
                0,
                2,
                TimeUnit.SECONDS
        );
    }

    private void publishNewRadarDetectionEvent() {
        publishRadarDetection(
                RadarDetection.builder()
                        .detectionId(UUID.randomUUID())
                        .source(SOURCE)
                        .point(randomPoint(ORIGIN_LATITUDE, ORIGIN_LONGITUDE, ORIGIN_ALTITUDE))
                        .build()
        );
    }
}
