
package phoneinventoryservice.phoneinventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phoneinventoryservice.phoneinventoryservice.domain.PhoneInventory;

import java.util.List;
import java.util.UUID;

public interface PhoneInventoryRepository extends JpaRepository<PhoneInventory, UUID> {

    List<PhoneInventory> findAllByPhoneId(UUID phoneId);
}
