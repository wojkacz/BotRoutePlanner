public class TypeS extends Module {

    public TypeS(int n) {
        super(n);
    }

    @Override
    public Double getItemTime(int n) {
        return (n + 1.0);
    }

    @Override
    public Double getDriveTime() {
        return 2.0;
    }
}
