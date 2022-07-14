package phoneinventoryservice.phoneinventoryservice.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import phoneinventoryservice.phoneinventoryservice.domain.PhoneInventory;
import phoneinventoryservice.phoneinventoryservice.repositories.PhoneInventoryRepository;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PhoneInventoryBootstrap implements CommandLineRunner {
    public static final String PHONE_1_IMEI = "338694371652036";
    public static final String PHONE_2_IMEI = "334015010517204";
    public static final String PHONE_3_IMEI = "863571738204276";
    public static final UUID PHONE_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID PHONE_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID PHONE_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final PhoneInventoryRepository phoneInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (phoneInventoryRepository.count() == 0) {
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        phoneInventoryRepository.save(PhoneInventory
                .builder()
                .phoneId(PHONE_1_UUID)
                .imei(PHONE_1_IMEI)
                .quantityOnHand(50)
                .build());

        phoneInventoryRepository.save(PhoneInventory
                .builder()
                .phoneId(PHONE_2_UUID)
                .imei(PHONE_2_IMEI)
                .quantityOnHand(50)
                .build());

        phoneInventoryRepository.saveAndFlush(PhoneInventory
                .builder()
                .phoneId(PHONE_3_UUID)
                .imei(PHONE_3_IMEI)
                .quantityOnHand(50)
                .build());

        log.debug("Loaded Inventory. Record count: " + phoneInventoryRepository.count());
    }
}
