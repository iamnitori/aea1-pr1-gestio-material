package service;

import model.Material;
import model.Prestec;
import repository.PrestecRepository;

/** Servei que gestiona els préstecs de materials. */
public class PrestecService {

    private PrestecRepository repo;

    public PrestecService(PrestecRepository repo) {
        this.repo = repo;
    }

    /** Registra un préstec i actualitza stock */
    public void prestar(Material material, int quantitat, String professor) {
        material.setQuantitat(material.getQuantitat() - quantitat);
        Prestec p = new Prestec(material, quantitat, professor);
        repo.guardarPrestec(p);
    }

    public java.util.List<Prestec> getPrestecs() {
        return repo.getPrestecs();
    }
}
