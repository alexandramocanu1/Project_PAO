package model;

public class TypeEvent {
    private String typeId;
    private String typeName;

    public TypeEvent(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void displayTypeInfo() {
        System.out.println("Type Event Details:");
        System.out.println("Type ID: " + typeId);
        System.out.println("Type Name: " + typeName);
    }

}
