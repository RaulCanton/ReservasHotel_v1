package org.iesalandalus.programacion.reservashotel.Modelo.negocio;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;


import javax.naming.OperationNotSupportedException;

public class Huespedes {


    private int capacidad;
    private int tamano;
    private Huesped [] coleccionHuesped;


    public Huespedes (int capacidad) {
        if (capacidad <=0){
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad=capacidad;
        coleccionHuesped = new Huesped[capacidad];
        tamano =0;


    }

    public Huesped[] get(){

       return copiaProfundaHuesped();
    }
    private Huesped[] copiaProfundaHuesped(){

        Huesped[] copiaHuesped = new Huesped[capacidad];
        for (int i =0; !tamanoSuperado(i);i++){
            copiaHuesped[i] =new Huesped(coleccionHuesped[i]);
        }
        return copiaHuesped;
    }
    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }
    public void insertar (Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }
        int indice=buscarIndice(huesped);
        if (capacidadSuperada(indice)){
            throw new OperationNotSupportedException("ERROR: No se aceptan m�s hu�spedes.");
        }
        if (tamanoSuperado(indice)){
            coleccionHuesped[indice]= new Huesped(huesped);
            tamano++;
        }else {
            throw new OperationNotSupportedException("ERROR:Y existe un hu�sped con esos datos.");
        }
    }



    private int buscarIndice (Huesped huesped) {

            int indice=-1;
            boolean huespedEncontrado = false;
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar el indice de un hu�sped nulo.");
        }
          while (!tamanoSuperado(indice) && !huespedEncontrado) {
            if (coleccionHuesped[indice].equals(huesped)) {
                huespedEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }
    private boolean tamanoSuperado(int indice){
        return indice>=tamano;

    }

    private boolean capacidadSuperada(int indice){

            return indice>=capacidad;

    }
    public Huesped buscar (Huesped huesped){

        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");
        }

        int indice=buscarIndice(huesped);
        if (tamanoSuperado(indice)){
            return null;
        }else{
            return new Huesped(coleccionHuesped[indice]);
        }

    }

    public void borrar (Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }


        int indice = buscarIndice(huesped);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR:No existe ning�n hu�sped con ese nombre.");
        } else {
            despalzarUnaPosicionHaciaLaIzquierda(indice);
        }
    }


    private void despalzarUnaPosicionHaciaLaIzquierda(int indice){
        int i;

        for (i = indice; !tamanoSuperado(i); i++){

            coleccionHuesped[i] = coleccionHuesped[i+1];
        }
        coleccionHuesped[i]=null;
        tamano--;

    }


}
