/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.sistemas.akka05;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

/**
 *
 * @author Hasler
 */
public class Sumador extends UntypedAbstractActor {

    class SUMAR {

        int ini;
        int fin;
        double vec[];
    };

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Sumar) {
            Sumar suma = (Sumar) message;
            double total = realizarSuma(suma);
            ActorRef jefe = this.getSender();
            jefe.tell(
                    new Double(total),
                    this.getSelf()
            );
        }
    }

    private double realizarSuma(Sumar suma) {
        double total = 0;

        for (int i = suma.ini; i < suma.fin; i++) {
            total += suma.vec[i];

        }
        System.out.println("Suma parcial del segemento es " + total);
        return total;
    }

}
