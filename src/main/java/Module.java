public abstract class Module {
    protected String[] items;

    public Module(int n){ items = new String[n]; }
    public String[] getItems() { return items; }
    public abstract Double getItemTime(int n);
    public abstract Double getDriveTime();
}
