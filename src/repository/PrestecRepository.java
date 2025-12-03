package repository;

import model.Prestec;
import java.util.ArrayList;
import java.util.List;

/** Repositori que guarda els préstecs. */
public class PrestecRepository {

    private List<Prestec> prestecs = new ArrayList<>();

    public void guardarPrestec(Prestec p) {
        prestecs.add(p);
    }

    public List<Prestec> getPrestecs() {
        return prestecs;
    }
}
