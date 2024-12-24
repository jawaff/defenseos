package com.dzyne.defenseos.detections.radar;

import com.google.common.base.Preconditions;
import lombok.Builder;

import java.util.UUID;

/**
 * The RadarDetection data class is our main common data model. It has an auto-generated builder created by
 * Lombok's annotation processor and otherwise relies on the immutable design of Java records. To make sure the
 * contained variables are immutable we aren't defining setters.
 *
 * @param detectionId Assumed to be unique across all sources
 */
@Builder
public record RadarDetection(UUID detectionId, String source, Point point) {
    public RadarDetection {
        Preconditions.checkNotNull(detectionId, "Detection Id should be non null");
        Preconditions.checkNotNull(source, "Source should be non null");
        Preconditions.checkArgument(!source.isBlank(), "Source should not be blank");
        Preconditions.checkNotNull(point, "Point should be non null");
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RadarDetection other)) {
            return false;
        } else {
            return other.detectionId.equals(this.detectionId);
        }
    }

    @Override
    public int hashCode() {
        return this.detectionId.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.source, this.point.toString());
    }
}
