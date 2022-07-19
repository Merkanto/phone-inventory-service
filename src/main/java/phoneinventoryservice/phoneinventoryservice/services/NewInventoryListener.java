package phoneinventoryservice.phoneinventoryservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import phoneinventoryservice.common.events.NewInventoryEvent;
import phoneinventoryservice.phoneinventoryservice.config.JmsConfig;
import phoneinventoryservice.phoneinventoryservice.domain.PhoneInventory;
import phoneinventoryservice.phoneinventoryservice.repositories.PhoneInventoryRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListener {

    private final PhoneInventoryRepository phoneInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {

        log.debug("Got Inventory: " + event.toString());

        phoneInventoryRepository.save(PhoneInventory.builder()
                        .phoneId(event.getPhoneDto().getId())
                        .imei(event.getPhoneDto().getImei())
                        .quantityOnHand(event.getPhoneDto().getQuantityOnHand())
                        .build());
    }
}
