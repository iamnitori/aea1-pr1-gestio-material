package model;

import java.time.LocalDate;

/**
 * Representa un préstec d'un material.
 */
public class Prestec {
    private Material material;
    private int quantitat;
    private String professor;
    private LocalDate dataPrestec;

    /**
     * Constructor del préstec.
     * @param material Material prestat
     * @param quantitat Quantitat prestada
     * @param professor Professor que pren el material
     */
    public Prestec(Material material, int quantitat, String professor) {
        this.material = material;
        this.quantitat = quantitat;
        this.professor = professor;
        this.dataPrestec = LocalDate.now();
    }

    /** @return material prestat */
    public Material getMaterial() { return material; }

    /** @return quantitat prestada */
    public int getQuantitat() { return quantitat; }

    /** @return professor que pren el material */
    public String getProfessor() { return professor; }

    /** @return data del préstec */
    public LocalDate getDataPrestec() { return dataPrestec; }

    @Override
    public String toString() {
        return quantitat + " unitats de " + material.getNom() + " per " + professor + " el " + dataPrestec;
    }
}
