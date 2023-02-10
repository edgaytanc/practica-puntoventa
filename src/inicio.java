/**
 * Proyecto que simula un punto de venta en modo texto
 * utiliza Arreglos como tablas y las relaciona entre si para procesar la informacion
 */
import java.util.Scanner;
import java.time.*;
public class inicio {
    
    /**
     * estructuras y variables globales
     */
    public static String[][] vendidos = new String[30][2];
    public static String[][] ventas =  new String[30][3];
    public static String[][] productos = new String[20][2];
    public static String[][] cupones =  new String [20][2];
    public static Scanner entrada = new Scanner(System.in);
    public static String opcion = "";
    public static String user = "cajero_9712749";
    public static String password = "ipc1_9712749";
    public static boolean security = true; //se pone en false para que aparesca login
    public static int idProductos = 0;
    public static int idCupones = 0;
    public static String nombreCliente ="";
    public static String nitCliente = "";
    
    public static void main(String[] args){
        
        while (security!=true){
            menuInicial();
        }
        
        while (!opcion.equals("5")) {
            listaOpciones();
            menu();
        }
        
    }
    
    /**
     * Metodo que lista las opciones del menu inicio
     */
    public static void listaOpciones(){
        mensaje("**********************************");
        mensaje("Bienvenido al Sistema");
        mensaje("Puede realizar las siguientes opciones");
        mensaje("1. Agregar nuevo producto");
        mensaje("2. Agregar cupones de descuento");
        mensaje("3. Realizar Venta");
        mensaje("4. Realizar Reporte");
        mensaje("5. Salir");
        mensaje("");
        opcion = obtenerDato("Ingrese el numero de la opcion:");
        mensaje("**********************************");
    }
    
    /**
     * metodo que simula un login
     */
    public static void menuInicial(){
        String usuario = obtenerDato("Ingrese el usuario: ");
        String contrasena = obtenerDato("Ingrese la contraseña");
        
        security = valida(usuario,contrasena);
        
        
        
    }
    
    /**
     * Procesa la opcion del menu y ejecuta los metodos correspondientes
     */
    public static void menu(){
        switch(opcion){
            case "1":
                agregarProducto();
                break;
            case "2":
                agregarCupones();
                break;
            case "3":
                realizarVenta();
                break;
            case "4":
                realizarReporte();
                break;
            default:
                mensaje("Saliendo del sistema");
        }
    }
    
    /**
     * Metodo que se encarga de solicitar los datos del producto para enviarlos
     * a otro metodo
     */
    public static void agregarProducto(){
        mensaje("Ingreso de Productos");
        mensaje("=========================");
        
        if(idProductos==productos.length){
            productos=incrementa(productos);
        }
        mensaje("Ingrese los datos del producto");
        String nombre = obtenerDato("Ingrese el nombre del producto:");
        String precio = obtenerDato("Ingrese el precio del producto:");
        int p =Integer.parseInt(precio);
        if(p>0){
            agregarDatos(nombre,precio);
        }
        else{
            mensaje("el precio no puede ser igual a cero o menor, no se guardaran los datos");
        }
        
        
    }
    
    /**
     * Se encarga de guardar los datos en la tabla producto 
     * @param nombre guarda el nombre del producto
     * @param precio guarda el precio del producto
     */
    public static void agregarDatos(String nombre, String precio){
        if(existeProducto(nombre)==-1){
            productos[idProductos][0] = nombre;
            productos[idProductos][1] = precio;
            
            //mensaje(productos[idProductos][0]+","+productos[idProductos][1]);
            idProductos++;
        }
        else{
            mensaje("ya existe el producto con el nombre "+nombre);
        }
    }
    
    /**
     * Verifica si el producto ya existe en la tabla
     * @param nombre Este es el nombre del producto
     * @return retorna un valor numerico, -1 si no existe el producto o el numero
     * de fila si existe
     */
    public static int existeProducto(String nombre){
        for(int i = 0; i < productos.length;i++){
            if(productos[i][0]!=null){
                if(productos[i][0].equals(nombre)){
                    return i;
                }
            }
            else{
                return -1;
            }
        }
        return -1;
    }
    
    public static void agregarCupones(){
        mensaje("Ingreso de Cupones");
        mensaje("=========================");
        
        if(idCupones==cupones.length){
            cupones=incrementa(cupones);
        }
        mensaje("Ingrese los datos del cupón");
        String codigo = obtenerDato("Ingrese el codigo de descuento:");
        String descuento = obtenerDato("Ingrese el porcentaje de descuento:");
        int d = Integer.parseInt(descuento);
        
        if(codigo.length()==4){
            if(d>0&&d<100){
                agregarDatosCupones(codigo,descuento);
            }
            else{
                mensaje("El descuento debe estar entre 1 y 99");
            }
            
        }
        else{
            mensaje("No se creo el codigo de descuento, el tamano no es correcto");
        }
        
    }
    
    public static void agregarDatosCupones(String codigo, String descuento){
        if(existeCupon(codigo)==-1){
            cupones[idCupones][0] = codigo;
            cupones[idCupones][1] = descuento;
            
            //mensaje(cupones[idCupones][0]+","+cupones[idCupones][1]);
            idCupones++;
        }
        else{
            mensaje("ya existe el cupon con el codigo "+codigo);
        }
    }
    
    public static int existeCupon(String codigo){
        for(int i = 0; i < cupones.length;i++){
            if(cupones[i][0]!=null){
                if(cupones[i][0].equals(codigo)){
                    return i;
                }
            }
            else{
                return -1;
            }
        }
        return -1;
    }
    /**
     * Realiza las ventas de los productos
     */
    public static void realizarVenta(){
        mensaje("Ventas");
        mensaje("=========================");
        solicitarCliente();
        seleccionarProductos();
    }
    /**
     * Se encarga de solicitar los datos del cliente, nombre y NIT
     */
    public static void solicitarCliente(){
        nombreCliente = obtenerDato("Ingrese el nombre del cliente: ");
        nitCliente = obtenerDato("Ingrese el NIT del cliente");
    }
    
    /**
     * despliega en pantalla el listado de productos para escoger en la venta
     */
    public static void listadoProductos(){
        System.out.printf("%10s %10s %10s %s", "Codigo","Nombre","Precio","\n");
        mensaje("=====================================");
        for(int i = 0; i<productos.length; i++){
            if(productos[i][0]!=null){
                //mensaje(String.valueOf(i)+" "+productos[i][0]+" "+productos[i][1]);
                System.out.printf("%10s %10s %10s %s",String.valueOf(i),productos[i][0],productos[i][1],"\n");
            }
            
        }
        mensaje("=====================================/n");
    }
    
    public static void seleccionarProductos(){
        String idproducto = "";
        String cantidad = "";
        String opcion = "";
        while(!opcion.equals("n")){
            listadoProductos();
            idproducto = obtenerDato("Seleccione el codigo del producto: ");
            cantidad = obtenerDato("Indicque la cantidad de producto:");
            mensaje("");
            carrito(idproducto,cantidad);
            opcion = obtenerDato("Desea continuar agregando productos al carrito (s/n): ");
        }
        String vender = obtenerDato("Desea Facturar (s/n):");
        if(vender.equals("s")){
            factura();
        }
        else{
            mensaje("Se cancelo la venta.");
            mensaje("");
        }
    }
    
    /**
     * Se encarga de agregar los productos de la venta al carrito para tenerlos
     * para facturarlos
     * @param idproducto
     * @param cantidad 
     * 
     */
    public static void carrito(String idproducto, String cantidad){
        for(int i = 0; i<ventas.length; i++){
            if(ventas[i][0]==null){
                ventas[i][0]=productos[Integer.parseInt(idproducto)][0];
                ventas[i][1]=cantidad;
                ventas[i][2]=productos[Integer.parseInt(idproducto)][1];
                productoVendido(idproducto,cantidad);
                break;
            }
        }
//        for(int i = 0; i <ventas.length; i++){
//            mensaje(ventas[i][0]);
//        }
    }
    /**
     * Emite la factura cuando se realiza la venta
     */
    public static void factura(){
        String desc = obtenerDato("Tiende codigo de descuento? (s/n)");
        
        if(desc.equals("s")){
            String codDesc = obtenerDato("Ingrese un codigo de descuento valido:");
            mensaje("");
            if(existeCupon(codDesc)!=-1){
                detalleFactura(existeCupon(codDesc));
            }
            else{
                mensaje("Codigo de descuento invalido, no se generara factura");
            }
        }else{
            detalleFactura(-1);
        }
    }
    
    /**
     * Despliega el detalle de la factura junto con su descuento y total a pagar
     * @param codDescuento 
     */
    public static void detalleFactura(int codDescuento){
        int granTotal = 0;
        mensaje("******SUPER-25******");
        mensaje("cajero:"+user);
        mensaje("Cliente: "+nombreCliente+" "+nitCliente);
        mensaje("Fecha: "+LocalDate.now());
        System.out.printf("%10s %10s %10s %10s", "Produto","Precio U", "Cantidad", "Total"+"\n");
        for(int i = 0; i<ventas.length;i++){
            if(ventas[i][0]!=null){
                String total =String.valueOf(Integer.parseInt(ventas[i][1])*Integer.parseInt(ventas[i][2]));
                granTotal = granTotal + Integer.parseInt(total);
                System.out.printf("%10s %10s %10s %10s",ventas[i][0],ventas[i][1],ventas[i][2],total+"\n");
            }
                
        }
        mensaje("-------------------------------------------");
        if(codDescuento!=-1){
            mensaje("SubTotal: "+ String.valueOf(granTotal));
            mensaje("Descuento: "+ cupones[codDescuento][1]+"%");
            granTotal = granTotal -(granTotal*Integer.parseInt(cupones[codDescuento][1]))/100;
        }
        mensaje("Total a Pagar: "+ String.valueOf(granTotal));
        //limpiaArreglo(ventas);
        ventas = new String[30][3];
    }
    
    
    
    
    
    /**
     * imprime el reporte de produtos vendidos
     */
    public static void realizarReporte(){
        mensaje("Reporte de productos mas comprados");
        mensaje("====================================");
        mensaje("");
        System.out.printf("%10s %10s %10s\n","#","Producto","Cantidad");
        mensaje("------------------------------------------------------------\n");
        ordenaDatos();
        for(int i = 0; i<vendidos.length;i++){
            if(vendidos[i][0]!=null){
                System.out.printf("%10s %10s %10s\n", String.valueOf(i),vendidos[i][0],vendidos[i][1]);
            }
        }
    }
    
    
    public static void productoVendido(String idProducto, String Cantidad){
        for(int i = 0; i < vendidos.length;i++){
            if(vendidos[i][0]!=null){
                if(vendidos[i][0].equals(productos[Integer.parseInt(idProducto)][0])){
                    vendidos[i][1]=String.valueOf(Integer.parseInt(vendidos[i][1])+Integer.parseInt(Cantidad));
                    break;
                }
            }
            else{
                vendidos[i][0]=productos[Integer.parseInt(idProducto)][0];
                vendidos[i][1]=Cantidad;
                break;
            }
        }
        
    }
    
    
    
    
    
    public static void mensaje(String msj)
    {
        System.out.println(msj);
    }
    
    
    
    public static String obtenerDato(String msj)
    {
        mensaje(msj);
        return entrada.nextLine();
    }
    
    public static boolean valida(String u, String p){
        if(u.equals(user)){
            if(p.equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public static String[][] incrementa(String[][] last){
        int tamano =  last.length*2;
        String[][] nuevo = new String[tamano][2];
        nuevo = last;
        return nuevo;
        
    }
    
    public static void ordenaDatos(){
        
        if(vendidos[0][0]!=null){
            for (int i = 0; i < vendidos.length; i++) {
                for (int j = 0; j < vendidos.length - 1; j++) {
                    if(vendidos[j][1]==null&&vendidos[j+1][1]==null){
                        continue;
                    }else if(vendidos[j][1]==null){
                        String[] temp = vendidos[j];
                        vendidos[j] = vendidos[j + 1];
                        vendidos[j + 1] = temp;
                    }else if(vendidos[j+1][1]==null){
                        continue;
                    }else if (Integer.parseInt(vendidos[j][1]) < Integer.parseInt(vendidos[j + 1][1])) {
                        String[] temp = vendidos[j];
                        vendidos[j] = vendidos[j + 1];
                        vendidos[j + 1] = temp;
                    }
                    
                }
            }
        }
        
    }
    
    
}
