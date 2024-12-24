package com.dzyne.defenseos.detections.radar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PointTest {
    private static Stream<Arguments> validValues() {
        return Stream.of(
                Arguments.of(0f, 0f, 0f),
                Arguments.of(-90f, -180f, 0f),
                Arguments.of(90f, 180f, 10_000_000f)
        );
    }

    @Test
    public void invalidValuesBuilderTest() {
        Assertions.assertThatThrownBy(() -> Point.builder().build())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Latitude should be non null");
        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(0f).build()
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Longitude should be non null");
        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(0f).longitude(0f).build()
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Altitude should be non null");

        Assertions.assertThatThrownBy(
                 () -> Point.builder().latitude(-90.001f).longitude(0f).altitude(0f).build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid latitude value: -90.001");
        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(90.001f).longitude(0f).altitude(0f).build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid latitude value: 90.001");

        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(-90f).longitude(-180.001f).altitude(0f).build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid longitude value: -180.001");
        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(90f).longitude(180.001f).altitude(0f).build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid longitude value: 180.001");

        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(-90f).longitude(-180f).altitude(-0.01f).build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid altitude value: -0.01");
        Assertions.assertThatThrownBy(
                () -> Point.builder().latitude(90f).longitude(180f).altitude(10_000_001f).build()
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid altitude value: 10000001.00");
    }

    @ParameterizedTest
    @MethodSource("validValues")
    public void validValuesBuilderTest(float latitude, float longitude, float altitude) {
        final var point = Point.builder().latitude(latitude).longitude(longitude).altitude(altitude).build();
        Assertions.assertThat(point.latitude()).isEqualTo(latitude);
        Assertions.assertThat(point.longitude()).isEqualTo(longitude);
        Assertions.assertThat(point.altitude()).isEqualTo(altitude);
    }

    @Test
    public void toStringTest() {
        final var latitude = 5f;
        final var longitude = 23.1444f;
        final var altitude = 133.3f;
        final var point = Point.builder().latitude(latitude).longitude(longitude).altitude(altitude).build();

        Assertions.assertThat(point.toString()).isEqualTo("5.000, 23.144, 133.30");
    }
}
