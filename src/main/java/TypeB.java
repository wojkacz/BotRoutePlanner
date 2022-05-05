public class TypeB extends Module {

    public TypeB(int n) {
        super(n);
    }

    @Override
    public Double getItemTime(int n) {
        return ((2 * n) + 2.0);
    }

    @Override
    public Double getDriveTime() {
        return 1.0;
    }
}
