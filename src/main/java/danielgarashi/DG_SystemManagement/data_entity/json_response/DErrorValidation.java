package danielgarashi.DG_SystemManagement.data_entity.json_response;

import java.util.StringTokenizer;

public class DErrorValidation extends DataBaseObject {
    public static void getError(String e, DErrorValidation dErrorValidation){
        StringTokenizer tokenizer = new StringTokenizer(e, "#");
        String errorStr = tokenizer.nextToken();

        if (tokenizer.hasMoreTokens()) {
            String[] errorFields = tokenizer.nextToken().split("_");
            dErrorValidation.setFields(errorFields);
        }

        dErrorValidation.setError(errorStr);
    }

    private String error;
    private String[] fields;

    public DErrorValidation() {
    }

    public DErrorValidation(String error) {
        this.error = error;
        this.fields = null;
    }

    public DErrorValidation(String error, String[] fields) {
        this.error = error;
        this.fields = fields;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    @Override
    public boolean isErrorClass() {
        return ERROR_CLASS;
    }
}

