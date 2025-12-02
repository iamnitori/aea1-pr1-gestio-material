package repository;

import model.Material;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositori que guarda els materials.
 */
public class InventariRepository {
    private List<Material> materials = new ArrayList<>();

    public void afegirMaterial(Material m) {
        materials.add(m);
    }

    public List<Material> getMaterials() {
        return materials;
    }
}
