import model.Categoria;
import model.Material;
import repository.InventariRepository;
import service.InventariService;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal amb menú del sistema de gestió d'inventari.
 * Permet afegir categories, afegir materials, llistar categories i materials,
 * i filtrar materials per categoria.
 */
public class Main {
    public static void main(String[] args) {
        InventariRepository repo = new InventariRepository();
        InventariService service = new InventariService(repo);
        Scanner sc = new Scanner(System.in);

        int opcio = -1;

        do {
            mostrarMenu();
            String entrada = sc.nextLine();

            try {
                opcio = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Si us plau, introdueix un número vàlid!");
                continue;
            }

            switch (opcio) {
                case 1:
                    afegirCategoria(service, sc);
                    break;
                case 2:
                    afegirMaterial(service, sc);
                    break;
                case 3:
                    llistarCategories(service);
                    break;
                case 4:
                    llistarMaterials(service);
                    break;
                case 5:
                    filtrarMaterialsPerCategoria(service, sc);
                    break;
                case 0:
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida!");
            }

        } while (opcio != 0);

        sc.close();
    }

    /** Mostra el menú principal */
    private static void mostrarMenu() {
        System.out.println("\n=== Menú Sistema Inventari ===");
        System.out.println("1. Afegir categoria");
        System.out.println("2. Afegir material");
        System.out.println("3. Llistar categories");
        System.out.println("4. Llistar materials");
        System.out.println("5. Llistar materials per categoria");
        System.out.println("0. Sortir");
        System.out.print("Tria una opció: ");
    }

    /** Afegeix una nova categoria */
    private static void afegirCategoria(InventariService service, Scanner sc) {
        System.out.print("Nom nova categoria: ");
        String nomCat = sc.nextLine().trim();
        if (nomCat.isEmpty()) {
            System.out.println("Nom de categoria no pot estar buit!");
            return;
        }
        service.afegirCategoria(nomCat);
        System.out.println("Categoria afegida!");
    }

    /** Afegeix un nou material a una categoria existent */
    private static void afegirMaterial(InventariService service, Scanner sc) {
        if (service.getCategories().isEmpty()) {
            System.out.println("Primer has d'afegir categories!");
            return;
        }

        System.out.print("Nom material: ");
        String nomMat = sc.nextLine().trim();
        if (nomMat.isEmpty()) {
            System.out.println("Nom del material no pot estar buit!");
            return;
        }

        System.out.println("Categories existents:");
        service.getCategories().forEach(c -> System.out.println("- " + c.getNom()));
        System.out.print("Tria categoria: ");
        String catNom = sc.nextLine().trim();
        Categoria cat = service.getCategoriaByName(catNom);
        if (cat == null) {
            System.out.println("Categoria no existeix. Material no afegit.");
            return;
        }

        System.out.print("Quantitat: ");
        int quantitat;
        try {
            quantitat = Integer.parseInt(sc.nextLine());
            if (quantitat <= 0) {
                System.out.println("La quantitat ha de ser major que zero!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Quantitat no vàlida. Material no afegit.");
            return;
        }

        service.afegirMaterial(nomMat, cat, quantitat);
        System.out.println("Material afegit!");
    }

    /** Llista totes les categories */
    private static void llistarCategories(InventariService service) {
        List<Categoria> categories = service.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No hi ha categories.");
            return;
        }
        System.out.println("Categories:");
        categories.forEach(c -> System.out.println("- " + c.getNom()));
    }

    /** Llista tots els materials */
    private static void llistarMaterials(InventariService service) {
        List<Material> materials = service.getMaterials();
        if (materials.isEmpty()) {
            System.out.println("No hi ha materials.");
            return;
        }
        System.out.println("Materials:");
        materials.forEach(System.out::println);
    }

    /** Filtra i mostra materials per categoria */
    private static void filtrarMaterialsPerCategoria(InventariService service, Scanner sc) {
        if (service.getCategories().isEmpty()) {
            System.out.println("No hi ha categories.");
            return;
        }

        System.out.println("Categories:");
        service.getCategories().forEach(c -> System.out.println("- " + c.getNom()));
        System.out.print("Tria categoria: ");
        String filtNom = sc.nextLine().trim();
        Categoria filtCat = service.getCategoriaByName(filtNom);
        if (filtCat == null) {
            System.out.println("Categoria no existeix.");
            return;
        }

        List<Material> matCat = service.getMaterialsByCategoria(filtCat);
        if (matCat.isEmpty()) {
            System.out.println("No hi ha materials en aquesta categoria.");
            return;
        }

        System.out.println("Materials de la categoria " + filtCat.getNom() + ":");
        matCat.forEach(System.out::println);
    }
}
