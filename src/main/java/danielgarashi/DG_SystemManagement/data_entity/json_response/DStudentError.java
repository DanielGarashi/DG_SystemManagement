package danielgarashi.DG_SystemManagement.data_entity.json_response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DStudentError extends DataBaseObject{
    private String error;

    public DStudentError() {
    }

    @Override
    public boolean isErrorClass() {
        return DataBaseObject.ERROR_CLASS;
    }
}
