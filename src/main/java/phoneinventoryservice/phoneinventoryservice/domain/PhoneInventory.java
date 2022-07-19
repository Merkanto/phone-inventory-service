
package phoneinventoryservice.phoneinventoryservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PhoneInventory extends BaseEntity {

    @Builder
    public PhoneInventory(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, UUID phoneId,
                          String imei, Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.phoneId = phoneId;
        this.imei = imei;
        this.quantityOnHand = quantityOnHand;
    }

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID phoneId;

    private String imei;
    private Integer quantityOnHand = 0;
}
