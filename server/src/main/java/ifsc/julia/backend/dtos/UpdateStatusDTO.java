package ifsc.julia.backend.dtos;

import ifsc.julia.backend.models.ReadingStatus;
import lombok.Data;

@Data
public class UpdateStatusDTO {
    private ReadingStatus status;
}
