import java.time.LocalDate;

public class transaccion {

    public final int id;
    public final LocalDate fecha;
    public final int valor;
    public final String descripcion;

    public transaccion(int id, LocalDate fecha, int valor, String descripcion){
        this.id=id;
        this.fecha=fecha;
        this.valor=valor;
        this.descripcion=descripcion;


    }
    @Override
    public String toString(){
        return "Fecha: "+this.fecha+"\nValor: "+this.valor+"\nDescripcion: "+this.descripcion+"\n";
    }
}
