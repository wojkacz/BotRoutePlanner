public class TypeH extends Module {

    public TypeH(int n){
        super(n);
    }

    @Override
    public Double getItemTime(int n) {
        return ((3 * n) + 4.0);
    }

    @Override
    public Double getDriveTime() {
        return 0.5;
    }
}
