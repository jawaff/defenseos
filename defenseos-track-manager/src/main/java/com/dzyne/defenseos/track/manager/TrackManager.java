package com.dzyne.defenseos.track.manager;

import com.dzyne.defenseos.api.radar.IRadar;
import com.dzyne.defenseos.detections.radar.RadarDetection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TrackManager {
    /**
     * The track manager takes in the available radar services and registers a consumer with them so that
     * we can receive radar detection events.
     * @param radarServiceList Spring provides to us dependency injection to automagically inject services that
     * implement the {@link IRadar} interface.
     */
    public TrackManager(final List<IRadar> radarServiceList) {
        for (IRadar radar: radarServiceList) {
            radar.registerRadarDetectionConsumer(this::consumeRadarDetection);
        }
    }

    private void consumeRadarDetection(final RadarDetection radarDetection) {
        System.out.printf("Receiving from %s%n", radarDetection);
    }
}
