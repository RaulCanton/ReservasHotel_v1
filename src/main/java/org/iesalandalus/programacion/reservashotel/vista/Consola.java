package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private Consola(){

    }

    public static void mostrarMenu(){
        for( Opcion opcion:Opcion.values()){
            System.out.println(opcion);
        }
    }

    public static int elegirOpcion() {
        int ordinalOpcion;
        do {
            System.out.println("Elige una opci�n: ");
            ordinalOpcion = Entrada.entero();

        } while (!Opcion.esOrdinalValido(ordinalOpcion));
        return ordinalOpcion;
    }

    public static Huesped leerHuesped() {
        Huesped huesped = null;
        String nombre;
        String dni;
        String telefono;
        String correo;

        String fechaNacimiento;

        do {
            System.out.print("Introduce el nombre del hu�sped: ");
            nombre = Entrada.cadena();
        } while (nombre.equals(""));
        do {
            System.out.print("Introduce el dni del cliente: ");
            dni = Entrada.cadena();
        } while (dni.equals(""));
        do {
            System.out.print("Introduce el tel�fono del hu�sped: ");
            telefono = Entrada.cadena();
        }while (telefono == null || telefono.equals(""));
        do {
            System.out.print("Introduce el correo del hu�sped: ");
            correo = Entrada.cadena();
        }while (correo == null || correo.equals(""));
        do {
            System.out.print("Introduce la fecha de nacimiento del hu�sped: ");
            fechaNacimiento = Entrada.cadena();
        }while (fechaNacimiento == null || fechaNacimiento.equals(""));

        LocalDate formatoDia = LocalDate.parse(fechaNacimiento);
        return  new Huesped(nombre,dni,telefono,correo,formatoDia);

    }
    public static Huesped leerClientePorDni()throws OperationNotSupportedException {
        String dni;

        LocalDate formatoDia = LocalDate.parse("23/07/1980");

        do {
            System.out.print("Introduce el dni del cliente: ");
            dni = Entrada.cadena();
        } while (dni.equals(""));

        return new Huesped("Pepito Perez Perez",dni,"900101010","loquesea@gmail.com",formatoDia);

        /*
        int i;
        i= Huespedes.(huesped1);
        if (i==-1)
            throw new OperationNotSupportedException("El dni buscado no es de un cliente.");
        else
        {
            return coleccionHuesped[i];
        }*/
    }

    public static LocalDate leerFecha(String mensaje) {
        mensaje= "";
        boolean diaCorrecto = false;
      //  DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            System.out.print("Introduce el d�a (aaaa/mm/dd): ");
            mensaje = Entrada.cadena();
            try {
                LocalDate.parse(mensaje);
                diaCorrecto = true;
            } catch (DateTimeParseException e) {
                diaCorrecto = false;
            }
        } while (!diaCorrecto);
        return LocalDate.parse(mensaje);
    }
    public static Habitacion leerHabitacion(){

        int planta;
        int puerta;
        double precio;
        TipoHabitacion tipoHabitacion;

        do {
            System.out.print("Introduce el n�mero de planta. ");
            planta = Entrada.entero();
        }while (planta <0 || planta>3);
        do {
            System.out.print("Introduce el n�mero de puerta. ");
            puerta = Entrada.entero();
        }while (puerta <0 || puerta>14);
        do {
            System.out.print("Introduce el precio de la habitaci�n. ");
            precio = Entrada.entero();
        }while (precio <40 || precio>150);
        String identificador=(String.format("%d%d",planta,puerta));
        return new Habitacion(planta,puerta,precio,leerTipoHabitacion(),identificador);

    }
    public static Habitacion leerHabitacionPorIdentificador(){
        int planta;
        int puerta;
        double precio;
        TipoHabitacion tipoHabitacion;
        do {
            System.out.print("Introduce el n�mero de planta. ");
            planta = Entrada.entero();
        }while (planta <0 || planta>3);
        do {
            System.out.print("Introduce el n�mero de puerta. ");
            puerta = Entrada.entero();
        }while (puerta <0 || puerta>14);
        String identificador=(String.format("%d%d",planta,puerta));
        return new Habitacion(planta,puerta,40,TipoHabitacion.SIMPLE,identificador);

    }

    public static TipoHabitacion leerTipoHabitacion(){
        int tipoHabi;
        do {
            System.out.print("Introduce el tipo de habitaci�n 1.-SUITE 2.-SIMPLE 3.-DOBLE 4.-TRIPLE .");
            tipoHabi = Entrada.entero();
        } while (tipoHabi < 1 || tipoHabi > 4);
        return TipoHabitacion.values()[tipoHabi];

    }

    public static Regimen leerRegimen(){
        int tipoRegi;
        do {
            System.out.print("Introduce el tipo de habitaci�n 1.-SOLO ALOJAMIENTO " +
                    "2.-ALOJAMIENTO DESAYUNO 3.-MEDIA_PENSION 4.-PENSION_COMPLETA .");
            tipoRegi = Entrada.entero();
        } while (tipoRegi < 1 || tipoRegi > 4);
        return Regimen.values()[tipoRegi];

    }
    public static Reserva leerReserva(){
        int numeroPersonas;
        String fechaIn;
        String fechaFin;

        System.out.print("Introduce la fecha de checkIn. ");
        fechaIn = Entrada.cadena();
        LocalDate fechaInicioReserva = LocalDate.parse(fechaIn);

        System.out.print("Introduce la fecha de checkOut. ");
        fechaFin = Entrada.cadena();
        LocalDate fechaFinReserva = LocalDate.parse(fechaFin);

        System.out.print("Introduce el n�mero de personas. ");
        numeroPersonas = Entrada.entero();

        return new Reserva(leerHuesped(),leerHabitacion(),leerRegimen(),fechaInicioReserva,fechaFinReserva,numeroPersonas);

    }

}
