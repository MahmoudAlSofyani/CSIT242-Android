package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Brand {

    private List<Supplier> supplier = null;

    public Brand() {
    }
    public Brand(List<Supplier> supplier) {
        super();
        this.supplier = supplier;
    }

    public List<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<Supplier> supplier) {
        this.supplier = supplier;
    }
}
