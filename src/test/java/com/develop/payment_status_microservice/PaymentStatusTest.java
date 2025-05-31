package com.develop.payment_status_microservice;

import com.develop.payment_status_microservice.domain.models.PaymentStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentStatusTest {

    @Test
    void testGettersAndSetters() {
        PaymentStatus status = new PaymentStatus();
        status.setId(1);
        status.setName("PAID");

        assertThat(status.getId()).isEqualTo(1);
        assertThat(status.getName()).isEqualTo("PAID");
    }

    @Test
    void testEqualsAndHashCode() {
        PaymentStatus status1 = new PaymentStatus();
        status1.setId(1);
        status1.setName("PAID");

        PaymentStatus status2 = new PaymentStatus();
        status2.setId(1);
        status2.setName("PAID");

        assertThat(status1).isEqualTo(status2);
        assertThat(status1.hashCode()).isEqualTo(status2.hashCode());
    }

    @Test
    void testToString() {
        PaymentStatus status = new PaymentStatus();
        status.setId(1);
        status.setName("PAID");

        String toString = status.toString();
        assertThat(toString).contains("id=1", "name=PAID");
    }
}
