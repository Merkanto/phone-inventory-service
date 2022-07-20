package phoneinventoryservice.phoneinventoryservice.services;

import phoneinventoryservice.model.PhoneOrderDto;

public interface AllocationService {

    Boolean allocateOrder(PhoneOrderDto phoneOrderDto);

    void deallocateOrder(PhoneOrderDto phoneOrderDto);
}
