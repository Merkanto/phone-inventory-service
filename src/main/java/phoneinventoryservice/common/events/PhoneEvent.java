package phoneinventoryservice.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PhoneEvent implements Serializable {

    static final long serialVersionUID = 5583029845770262338L;

    private PhoneDto phoneDto;
}
