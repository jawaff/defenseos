package com.dzyne.defenseos.detections.radar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RadarDetectionTest {
    @Test
    public void invalidValuesBuilderTest() {
        Assertions.assertThatThrownBy(() -> RadarDetection.builder().build())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Detection Id should be non null");
        Assertions.assertThatThrownBy(
                () -> RadarDetection.builder().detectionId(UUID.randomUUID()).build()
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Source should be non null");
        Assertions.assertThatThrownBy(
                () -> RadarDetection.builder().detectionId(UUID.randomUUID()).source("   ").build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Source should not be blank");
        Assertions.assertThatThrownBy(
                        () -> RadarDetection.builder().detectionId(UUID.randomUUID()).source("Source").build()
                )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Point should be non null");
    }

    @Test
    public void validValuesBuilderTest() {
        final var source = "SomeSource";
        final var detectionId = UUID.randomUUID();
        final var latitude = 5.23f;
        final var longitude = 23.1444f;
        final var altitude = 133.33f;
        final var point = Point.builder().latitude(latitude).longitude(longitude).altitude(altitude).build();
        final var radarDetection = RadarDetection.builder()
                .source(source)
                .detectionId(detectionId)
                .point(point)
                .build();

        Assertions.assertThat(radarDetection.source()).isEqualTo(source);
        Assertions.assertThat(radarDetection.detectionId()).isEqualTo(detectionId);
        Assertions.assertThat(radarDetection.point()).isEqualTo(point);
    }

    @Test
    public void toStringTest() {
        final var source = "SomeSource";
        final var detectionId = UUID.randomUUID();
        final var latitude = 5.23f;
        final var longitude = 23.1444f;
        final var altitude = 133.33f;
        final var point = Point.builder().latitude(latitude).longitude(longitude).altitude(altitude).build();
        final var radarDetection = RadarDetection.builder()
                .source(source)
                .detectionId(detectionId)
                .point(point)
                .build();

        Assertions.assertThat(radarDetection.toString()).isEqualTo("SomeSource:5.230, 23.144, 133.33");
    }
}
