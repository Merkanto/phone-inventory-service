package phoneinventoryservice.phoneinventoryservice.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import phoneinventoryservice.phoneinventoryservice.repositories.PhoneInventoryRepository;
import phoneinventoryservice.phoneinventoryservice.web.mappers.PhoneInventoryMapper;
import phoneinventoryservice.phoneinventoryservice.web.model.PhoneInventoryDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PhoneInventoryController {

    private final PhoneInventoryRepository phoneInventoryRepository;
    private final PhoneInventoryMapper phoneInventoryMapper;

    @GetMapping("api/v1/phone/{phoneId}/inventory")
    List<PhoneInventoryDto> listPhonesById(@PathVariable UUID phoneId) {
        log.debug("Finding Inventory for phoneId:" + phoneId);

        return phoneInventoryRepository.findAllByPhoneId(phoneId)
                .stream()
                .map(phoneInventoryMapper::phoneInventoryToPhoneInventoryDto)
                .collect(Collectors.toList());
    }
}
