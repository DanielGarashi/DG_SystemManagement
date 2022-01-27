package danielgarashi.DG_SystemManagement.response_data_entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DStudentError extends DataBaseObject{
    private String error;

    @Override
    public boolean isErrorClass() {
        return DataBaseObject.ERROR_CLASS;
    }
}
