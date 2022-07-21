package phoneinventoryservice.phoneinventoryservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import phoneinventoryservice.model.events.DeallocateOrderRequest;
import phoneinventoryservice.phoneinventoryservice.config.JmsConfig;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request){
        allocationService.deallocateOrder(request.getPhoneOrderDto());
    }
}
