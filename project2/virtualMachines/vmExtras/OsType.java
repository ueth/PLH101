package project2.virtualMachines.vmExtras;

public enum OsType {
    WINDOWS ("Windows"),
    FEDORA ("Fedora"),
    UBUNTU ("Ubuntu");

    private String name;

    OsType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
