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

    /**
     * Our consumer function that we register with the available radar services. When the radar services publish
     * a new {@link RadarDetection} event, then this function will be called and passed that event.
     * @param radarDetection The radar detection event being published by one of the radar services.
     */
    private void consumeRadarDetection(final RadarDetection radarDetection) {
        System.out.printf("Receiving from %s%n", radarDetection);
    }
}
