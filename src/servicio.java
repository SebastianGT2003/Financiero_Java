public class servicio {
    public final int valor;
    public final String nombre;
    public final int id;


    public servicio(int valor,String nombre, int id){
        this.valor=valor;
        this.nombre=nombre;
        this.id=id;

    }

    @Override
    public String toString(){
        return "Id "+this.id+"\nValor: "+this.valor+"\nNombre: "+this.nombre;
    }
}
