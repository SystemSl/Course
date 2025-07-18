package com.example.course.Config;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class ReviewPK implements Serializable {

    @Column(name = "visitor_id")
    private Long visitorId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewPK)) return false;
        ReviewPK other = (ReviewPK) o;
        return visitorId.equals(other.visitorId) && restaurantId.equals(other.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitorId, restaurantId);
    }
}
