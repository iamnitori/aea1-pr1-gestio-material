package model;

/**
 * Representa una categoria de materials.
 */
public class Categoria {
    private String nom;

    /**
     * Constructor de la classe Categoria.
     * @param nom Nom de la categoria
     */
    public Categoria(String nom) {
        this.nom = nom;
    }

    /** @return el nom de la categoria */
    public String getNom() { return nom; }

    /** @param nom assigna un nou nom a la categoria */
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Categoria)) return false;
        Categoria c = (Categoria) obj;
        return this.nom.equalsIgnoreCase(c.nom);
    }

    @Override
    public int hashCode() {
        return nom.toLowerCase().hashCode();
    }
}
