package service;

import model.Material;
import model.Prestec;
import model.Categoria;
import repository.InventariRepository;
import repository.PrestecRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servei que gestiona l'inventari i categories.
 */
public class InventariService {
    private InventariRepository repo;
    private List<Categoria> categories;
    private PrestecService prestecService;

    public InventariService(InventariRepository repo) {
        this.repo = repo;
        this.categories = new ArrayList<>();
        this.prestecService = new PrestecService(new PrestecRepository());
    }

    /** Afegeix una nova categoria */
    public void afegirCategoria(String nom) {
        if (categories.stream().noneMatch(c -> c.getNom().equalsIgnoreCase(nom))) {
            categories.add(new Categoria(nom));
        }
    }

    public List<Categoria> getCategories() {
        return categories;
    }

    /** Busca una categoria per nom */
    public Categoria getCategoriaByName(String nom) {
        return categories.stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    /** Afegeix un nou material a l'inventari */
    public void afegirMaterial(String nom, Categoria categoria, int quantitat) {
        if (categoria != null) {
            Material m = new Material(nom, categoria, quantitat);
            repo.afegirMaterial(m);
        } else {
            System.out.println("Categoria no vàlida. Material no afegit.");
        }
    }

    /** @return llista de materials */
    public List<Material> getMaterials() {
        return repo.getMaterials();
    }

    /** Filtra materials per categoria */
    public List<Material> getMaterialsByCategoria(Categoria categoria) {
        return repo.getMaterials().stream()
                .filter(m -> m.getCategoria().equals(categoria))
                .collect(Collectors.toList());
    }
    
    /** Busca un material pel nom */
    public Material getMaterialByName(String nom) {
        return repo.getMaterials().stream()
                .filter(m -> m.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }
    
    /** Presta material */
    public void prestarMaterial(Material material, int quantitat, String professor) {
        prestecService.prestar(material, quantitat, professor);
    }

    /** Retorna tots els préstecs registrats */
    public List<Prestec> getPrestecs() {
        return prestecService.getPrestecs();
    }


}
