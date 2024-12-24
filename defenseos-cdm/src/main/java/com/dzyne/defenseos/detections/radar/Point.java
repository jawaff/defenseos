package com.dzyne.defenseos.detections.radar;

import com.google.common.base.Preconditions;
import lombok.Builder;

/**
 * @param latitude  -90 to 90 degrees
 * @param longitude -180 to 180 degrees
 * @param altitude  measured in meters, 10,000,000 being the limit due to the approximate height of Earth's atmosphere.
 */
@Builder
public record Point(Float latitude, Float longitude, Float altitude) {
    public Point {
        Preconditions.checkNotNull(latitude, "Latitude should be non null");
        Preconditions.checkNotNull(longitude, "Longitude should be non null");
        Preconditions.checkNotNull(altitude, "Altitude should be non null");
        Preconditions.checkArgument(
                -90f <= latitude && latitude <= 90f,
                String.format("Invalid latitude value: %.3f", latitude)
        );
        Preconditions.checkArgument(
                -180f <= longitude && longitude <= 180f,
                String.format("Invalid longitude value: %.3f", longitude)
        );
        Preconditions.checkArgument(
                0f <= altitude && altitude <= 10_000_000f,
                String.format("Invalid altitude value: %.2f", altitude)
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Point(Float otherLatitude, Float otherLongitude, Float otherAltitude))) {
            return false;
        } else {
            return Float.floatToIntBits(otherLatitude) == Float.floatToIntBits(this.latitude)
                    && Float.floatToIntBits(otherLongitude) == Float.floatToIntBits(this.longitude)
                    && Float.floatToIntBits(otherAltitude) == Float.floatToIntBits(this.altitude);
        }
    }

    @Override
    public int hashCode() {
        return Float.floatToIntBits(latitude + longitude + altitude);
    }

    @Override
    public String toString() {
        return String.format("%.3f, %.3f, %.2f", this.latitude, this.longitude, this.altitude);
    }
}
