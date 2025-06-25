package com.example.TMF687.model;

import jakarta.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class ValidFor {

    private Instant startDateTime;
    private Instant endDateTime;

    public ValidFor() {}

    public ValidFor(Instant startDateTime, Instant endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Instant getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Instant startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }
}
