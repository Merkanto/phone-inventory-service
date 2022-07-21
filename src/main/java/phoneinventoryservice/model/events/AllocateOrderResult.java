package phoneinventoryservice.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import phoneinventoryservice.model.PhoneOrderDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderResult {
    private PhoneOrderDto phoneOrderDto;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
}
