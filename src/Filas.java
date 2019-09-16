public class Filas {
    private String origen,destino;
    private Double peso;

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Fila{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                '}';
    }
}
