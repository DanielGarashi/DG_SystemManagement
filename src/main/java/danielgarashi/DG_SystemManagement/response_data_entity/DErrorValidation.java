package danielgarashi.DG_SystemManagement.response_data_entity;

import java.util.StringTokenizer;

public class DErrorValidation extends DataBaseObject {
    public static DErrorValidation getError(String e){
        StringTokenizer tokenizer = new StringTokenizer(e, "#");
        String errorStr = tokenizer.nextToken();

        if (tokenizer.hasMoreTokens()) {
            String[] errorFields = tokenizer.nextToken().split("_");
            return new DErrorValidation(errorStr, errorFields);
        }

        return new DErrorValidation(errorStr);
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

