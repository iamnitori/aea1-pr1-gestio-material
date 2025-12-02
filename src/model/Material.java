package model;

/**
 * Representa un material de l'inventari.
 */
public class Material {
    private String nom;
    private Categoria categoria;
    private int quantitat;

    public Material(String nom, Categoria categoria, int quantitat) {
        this.nom = nom;
        this.categoria = categoria;
        this.quantitat = quantitat;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public int getQuantitat() { return quantitat; }
    public void setQuantitat(int quantitat) { this.quantitat = quantitat; }

    @Override
    public String toString() {
        return nom + " (" + categoria.getNom() + ") - " + quantitat + " unitats";
    }
}
