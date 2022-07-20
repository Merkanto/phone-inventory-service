package phoneinventoryservice.phoneinventoryservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phoneinventoryservice.model.PhoneOrderDto;
import phoneinventoryservice.model.PhoneOrderLineDto;
import phoneinventoryservice.phoneinventoryservice.domain.PhoneInventory;
import phoneinventoryservice.phoneinventoryservice.repositories.PhoneInventoryRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService {

    private final PhoneInventoryRepository phoneInventoryRepository;

    @Override
    public Boolean allocateOrder(PhoneOrderDto phoneOrderDto) {
        log.debug("Allocating OrderId: " + phoneOrderDto.getId());

        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        phoneOrderDto.getPhoneOrderLines().forEach(phoneOrderLine -> {
            if ((((phoneOrderLine.getOrderQuantity() != null ? phoneOrderLine.getOrderQuantity() : 0)
                    - (phoneOrderLine.getQuantityAllocated() != null ? phoneOrderLine.getQuantityAllocated() : 0)) > 0)) {
                allocateBeerOrderLine(phoneOrderLine);
            }
            totalOrdered.set(totalOrdered.get() + phoneOrderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() + (phoneOrderLine.getQuantityAllocated() != null ? phoneOrderLine.getQuantityAllocated() : 0));
        });

        log.debug("Total Ordered: " + totalOrdered.get() + " Total Allocated: " + totalAllocated.get());

        return totalOrdered.get() == totalAllocated.get();
    }

    private void allocateBeerOrderLine(PhoneOrderLineDto phoneOrderLine) {
        List<PhoneInventory> phoneInventoryList = phoneInventoryRepository.findAllByImei(phoneOrderLine.getImei());

        phoneInventoryList.forEach(phoneInventory -> {
            int inventory = (phoneInventory.getQuantityOnHand() == null) ? 0 : phoneInventory.getQuantityOnHand();
            int orderQty = (phoneOrderLine.getOrderQuantity() == null) ? 0 : phoneOrderLine.getOrderQuantity();
            int allocatedQty = (phoneOrderLine.getQuantityAllocated() == null) ? 0 : phoneOrderLine.getQuantityAllocated();
            int qtyToAllocate = orderQty - allocatedQty;

            if (inventory >= qtyToAllocate) { // full allocation
                inventory = inventory - qtyToAllocate;
                phoneOrderLine.setQuantityAllocated(orderQty);
                phoneInventory.setQuantityOnHand(inventory);

                phoneInventoryRepository.save(phoneInventory);
            } else if (inventory > 0) { //partial allocation
                phoneOrderLine.setQuantityAllocated(allocatedQty + inventory);
                phoneInventory.setQuantityOnHand(0);

            }

            if (phoneInventory.getQuantityOnHand() == 0) {
                phoneInventoryRepository.delete(phoneInventory);
            }
        });

    }

    @Override
    public void deallocateOrder(PhoneOrderDto phoneOrderDto) {
        phoneOrderDto.getPhoneOrderLines().forEach(phoneOrderLineDto -> {
            PhoneInventory phoneInventory = PhoneInventory.builder()
                    .phoneId(phoneOrderLineDto.getPhoneId())
                    .imei(phoneOrderLineDto.getImei())
                    .quantityOnHand(phoneOrderLineDto.getQuantityAllocated())
                    .build();

            PhoneInventory savedInventory = phoneInventoryRepository.save(phoneInventory);

            log.debug("Saved Inventory for phone imei: " + savedInventory.getImei() + " inventory id: " + savedInventory.getId());
        });
    }
}
