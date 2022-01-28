package danielgarashi.DG_SystemManagement.data_entity.json_response;

public abstract class DataBaseObject {
    protected final static boolean ERROR_CLASS = true;
    protected final static boolean NOT_ERROR_CLASS = false;

    public boolean isErrorClass() { return NOT_ERROR_CLASS;}
}
