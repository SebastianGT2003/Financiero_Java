public class deuda {

    public final int id;

    public final String fecha;
    public final int valor;
    public final String descripcion;
    public final int intereses;

    public deuda(int id, int valor, String fecha, String descripcion, int intereses){
        this.id=id;
        this.valor=valor;
        this.fecha=fecha;
        this.descripcion=descripcion;
        this.intereses=intereses;


    }
    @Override
    public String toString(){
        return "ID: "+ this.id +  "\nValor: "+ this.valor+" \nFecha: "+this.fecha+"\nInteres: "+this.intereses +"\nDescripcion: "+this.descripcion+"\n";
    }
}

