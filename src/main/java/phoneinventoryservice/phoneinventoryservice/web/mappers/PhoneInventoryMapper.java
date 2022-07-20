package phoneinventoryservice.phoneinventoryservice.web.mappers;

import org.mapstruct.Mapper;
import phoneinventoryservice.phoneinventoryservice.domain.PhoneInventory;
import phoneinventoryservice.model.PhoneInventoryDto;

@Mapper(uses = {DateMapper.class})
public interface PhoneInventoryMapper {

    PhoneInventory phoneInventoryDtoToPhoneInventory(PhoneInventoryDto phoneInventoryDto);

    PhoneInventoryDto phoneInventoryToPhoneInventoryDto(PhoneInventory phoneInventory);
}
